package com.mitlab.ci;

import com.mitlab.ci.gitlab.GitlabUtil;
import com.mitlab.ci.gitlab.issue.IssueRequest;
import com.mitlab.ci.gitlab.issue.IssueResponse;
import com.mitlab.ci.gitlab.user.GitlabUser;
import com.mitlab.ci.zbox.*;
import com.mitlab.ci.zbox.bug.ZboxBug;
import com.mitlab.ci.zbox.bug.ZboxBugDetails;
import com.mitlab.ci.zbox.task.ZboxTask;
import com.mitlab.ci.zbox.task.ZboxTaskDetails;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import org.h2.jdbcx.JdbcConnectionPool;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ZboxServlet extends HttpServlet {
    private final Logger logger = Logger.getLogger(this.getClass().getName());
    private ThreadLocal<byte[]> dataBuffer = new ThreadLocal<byte[]>() {
        @Override
        protected byte[] initialValue() {
            return new byte[1024];
        }
    };
    private volatile ZboxSession session;
    private CacheManager manager = CacheManager.newInstance();
    private JdbcConnectionPool h2Pool;
    private long zboxReloginTime;
    private long zboxLastAccessTime;

    @Override
    public void destroy() {
        ZboxUtil.getInstance().logout(session);
        session = null;
        if (h2Pool != null) {
            h2Pool.dispose();
            h2Pool = null;
        }
    }

    private void close(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                throw new ZboxException(e.getMessage(), e);
            }
        }
    }

    private void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new ZboxException(e.getMessage(), e);
            }
        }
    }

    private void close(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new ZboxException(e.getMessage(), e);
            }
        }
    }

    @Override
    public void init() throws ServletException {
        zboxReloginTime = Long.parseLong(this.getInitParameter("zboxReloginTime")) * 1000;
        ZboxUtil.getInstance().setAccessUrl(this.getInitParameter("zboxUrl"));
        GitlabUtil.getInstance().setAccessUrl(this.getInitParameter("gitlabUrl"));
        this.session = ZboxUtil.getInstance().getZboxSession();
        ZboxUtil.getInstance().login(getInitParameter("zboxUser"), getInitParameter("zboxPassword"), session);
        zboxLastAccessTime = System.currentTimeMillis();
        Ehcache cache = manager.getEhcache("issueCache");
        cache.put(new Element("zboxSession", this.session));
        if (logger.isLoggable(Level.INFO)) {
            logger.info(this.session.toString());
        }
        h2Pool = JdbcConnectionPool.create("jdbc:h2:~/test", "sa", "sa");
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = h2Pool.getConnection();
            stmt = conn.prepareStatement("create cached table if not exists t_issue(zid varchar(32) primary key, gid varchar(32), giid varchar(32), project varchar(128))");
            stmt.execute();
        } catch (SQLException e) {
            throw new ZboxException("init memdb error", e);
        } finally {
            close(stmt);
            close(conn);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        InputStream ins = null;
        try {
            ins = request.getInputStream();
            byte[] data = dataBuffer.get();
            int len = ins.read(data);
            ZboxNotify notify = ZboxUtil.newObjectMapper().readValue(data, 0, len, ZboxNotify.class);
            if (logger.isLoggable(Level.INFO)) {
                logger.info("zbox notify:" + notify);
            }
            if (System.currentTimeMillis() - zboxReloginTime > zboxLastAccessTime) {
                if (logger.isLoggable(Level.INFO)) {
                    logger.info("logout zbox for session[" + this.session + "]");
                }
                ZboxUtil.getInstance().logout(session);
                this.session = ZboxUtil.getInstance().getZboxSession();
                if (logger.isLoggable(Level.INFO)) {
                    logger.info("new zbox session[" + this.session + "]");
                }
                ZboxUtil.getInstance().login(getInitParameter("zboxUser"), getInitParameter("zboxPassword"), session);
                if (logger.isLoggable(Level.INFO)) {
                    logger.info("logout zbox for session[" + this.session + "]");
                }
                zboxLastAccessTime = System.currentTimeMillis();
                if (logger.isLoggable(Level.INFO)) {
                    logger.info(this.session.toString());
                }
            } else {
                if (logger.isLoggable(Level.INFO)) {
                    logger.info("refresh zbox alive time for session[" + this.session + "]");
                    zboxLastAccessTime = System.currentTimeMillis();
                }
            }
            if ("task".equals(notify.getObjectType())) {
                onTaskReceive(notify);
            } else if ("bug".equals(notify.getObjectType())) {
                ZboxBug bug = ZboxUtil.getInstance().getBug(notify.getObjectId(), session);
                IssueRequest issueRequest = new IssueRequest();
                GitlabUser gitlabUser = GitlabUtil.getInstance().getUserDetails(bug.getBug().getAssignedTo(), getInitParameter("gitlabToken"));
                if (gitlabUser != null) {
                    issueRequest.setAssigneeIds(new Long[] {gitlabUser.getId()});
                }
                Ehcache cache = manager.getEhcache("issueCache");
                ZboxBugDetails bugDetails = bug.getBug();
                String cacheId = "Bug-" + bugDetails.getId();
                issueRequest.setId(cacheId);
                String title = bugDetails.getTitle();
                issueRequest.setTitle(title);
                issueRequest.setDescription(bugDetails.getSteps());
                setIssueStatus(notify, issueRequest);
                Element element = cache.get(cacheId);
                String project = getInitParameter(bug.getBug().getProjectName());
                boolean foundIssueMapping = false;
                if (element != null) {
                    IssueResponse cachedIssue = (IssueResponse) element.getObjectValue();
                    issueRequest.setId(cachedIssue.getId());
                    issueRequest.setIssueIid(cachedIssue.getIid());
                    project = Long.toString(cachedIssue.getProjectId());
                    foundIssueMapping = true;
                    if (logger.isLoggable(Level.INFO)) {
                        logger.info("hit cache:" + cachedIssue);
                    }
                } else {
                    Connection conn = null;
                    PreparedStatement stmt = null;
                    ResultSet rs = null;
                    try {
                        conn = h2Pool.getConnection();
                        stmt = conn.prepareStatement("select gid, giid, project from t_issue where zid = ?");
                        stmt.setString(1, cacheId);
                        rs = stmt.executeQuery();
                        if (rs.next()) {
                            issueRequest.setId(rs.getString(1));
                            issueRequest.setIssueIid(rs.getLong(2));
                            project = rs.getString(3);
                            foundIssueMapping = true;
                            if (logger.isLoggable(Level.INFO)) {
                                logger.info("hit db:{issueId:" + issueRequest.getId() + ", issueIid:" + issueRequest.getIssueIid() + ", project:" + project + ",zid:" + cacheId + "}");
                            }
                        }
                    } catch (SQLException e) {
                        throw new ZboxException("query issue mapping error", e);
                    } finally {
                        close(rs);
                        close(stmt);
                        close(conn);
                    }
                }
                IssueResponse issueResponse = GitlabUtil.getInstance().createIssue(project, issueRequest, getInitParameter("gitlabToken"));
                if (element == null) {
                    element = new Element(cacheId, issueResponse);
                    cache.put(element);
                    if (logger.isLoggable(Level.INFO)) {
                        logger.info("save cache:" + issueResponse);
                    }
                    if (!foundIssueMapping) {
                        setIssueMapping2DB(issueRequest, cacheId, project, issueResponse);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ins != null) {
                ins.close();
            }
        }
        response.getWriter().print("OK");
    }

    private void setIssueStatus(ZboxNotify notify, IssueRequest issueRequest) {
        String gitlabAction = this.getInitParameter("zboxAction:" + notify.getAction());
        if (gitlabAction != null && !"".equals(gitlabAction.trim())) {
            String[] statusLabelArray = gitlabAction.split(",");
            if (!"".equals(statusLabelArray[0])) {
                issueRequest.setStateEvent(statusLabelArray[0]);
            }
            if (statusLabelArray.length > 1 && !"".equals(statusLabelArray[1])) {
                issueRequest.setLabels(statusLabelArray[1]);
            }
        }
    }

    private void onTaskReceive(ZboxNotify notify) {
        ZboxTask task = ZboxUtil.getInstance().getTask(notify.getObjectId(), session);
        IssueRequest issueRequest = new IssueRequest();
        GitlabUser gitlabUser = GitlabUtil.getInstance().getUserDetails(task.getTask().getAssignedTo(), getInitParameter("gitlabToken"));
        if (gitlabUser != null) {
            issueRequest.setAssigneeIds(new Long[] {gitlabUser.getId()});
        }
        Ehcache cache = manager.getEhcache("issueCache");
        ZboxTaskDetails taskDetails = task.getTask();
        String cacheId = "Task-" + taskDetails.getId();
        issueRequest.setId(cacheId);
        String title = task.getTitle();
        title = title.substring(title.indexOf(" "), title.lastIndexOf("/"));
        issueRequest.setTitle(title);
        issueRequest.setDescription(taskDetails.getDesc());
        setIssueStatus(notify, issueRequest);
        Element element = cache.get(cacheId);
        String project = getInitParameter(task.getProject().getName());
        boolean foundIssueMapping = false;
        if (element != null) {
            IssueResponse cachedIssue = (IssueResponse) element.getObjectValue();
            issueRequest.setId(cachedIssue.getId());
            issueRequest.setIssueIid(cachedIssue.getIid());
            project = Long.toString(cachedIssue.getProjectId());
            foundIssueMapping = true;
            if (logger.isLoggable(Level.INFO)) {
                logger.info("hit cache:" + cachedIssue);
            }
        } else {
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            try {
                conn = h2Pool.getConnection();
                stmt = conn.prepareStatement("select gid, giid, project from t_issue where zid = ?");
                stmt.setString(1, cacheId);
                rs = stmt.executeQuery();
                if (rs.next()) {
                    issueRequest.setId(rs.getString(1));
                    issueRequest.setIssueIid(rs.getLong(2));
                    project = rs.getString(3);
                    foundIssueMapping = true;
                    if (logger.isLoggable(Level.INFO)) {
                        logger.info("hit db:{issueId:" + issueRequest.getId() + ", issueIid:" + issueRequest.getIssueIid() + ", project:" + project + ",zid:" + cacheId + "}");
                    }
                }
            } catch (SQLException e) {
                throw new ZboxException("query issue mapping error", e);
            } finally {
                close(rs);
                close(stmt);
                close(conn);
            }
        }
        IssueResponse issueResponse = GitlabUtil.getInstance().createIssue(project, issueRequest, getInitParameter("gitlabToken"));
        if (element == null) {
            element = new Element(cacheId, issueResponse);
            cache.put(element);
            if (logger.isLoggable(Level.INFO)) {
                logger.info("cave cache:" + issueResponse);
            }
            if (!foundIssueMapping) {
                setIssueMapping2DB(issueRequest, cacheId, project, issueResponse);
            }
        }
    }

    private void setIssueMapping2DB(IssueRequest issueRequest, String cacheId, String project, IssueResponse issueResponse) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = h2Pool.getConnection();
            stmt = conn.prepareStatement("insert into t_issue(zid, gid , giid, project) values(?, ?, ?, ?)");
            stmt.setString(1, cacheId);
            stmt.setString(2, issueResponse.getId());
            stmt.setString(3, Long.toString(issueResponse.getIid()));
            stmt.setString(4, Long.toString(issueResponse.getProjectId()));
            stmt.executeUpdate();
            if (logger.isLoggable(Level.INFO)) {
                logger.info("save db:{issueId:" + issueRequest.getId() + ", issueIid:" + issueRequest.getIssueIid() + ", project:" + project + ",zid:" + cacheId + "}");
            }
        } catch (SQLException e) {
            throw new ZboxException("save issue mapping error", e);
        } finally {
            close(stmt);
            close(conn);
        }
    }
}
