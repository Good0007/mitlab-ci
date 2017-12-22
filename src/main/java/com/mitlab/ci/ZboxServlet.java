package com.mitlab.ci;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mitlab.ci.gitlab.GitlabUtil;
import com.mitlab.ci.gitlab.issue.IssueRequest;
import com.mitlab.ci.gitlab.issue.IssueResponse;
import com.mitlab.ci.gitlab.user.GitlabUser;
import com.mitlab.ci.manager.IssueMappingEntity;
import com.mitlab.ci.manager.SettingEntity;
import com.mitlab.ci.manager.dao.ActionMappingDao;
import com.mitlab.ci.manager.dao.InitDao;
import com.mitlab.ci.manager.dao.IssueMappingDao;
import com.mitlab.ci.manager.dao.SettingDao;
import com.mitlab.ci.zbox.ZboxNotify;
import com.mitlab.ci.zbox.ZboxSession;
import com.mitlab.ci.zbox.ZboxUtil;
import com.mitlab.ci.zbox.bug.ZboxBug;
import com.mitlab.ci.zbox.bug.ZboxBugDetails;
import com.mitlab.ci.zbox.task.ZboxTask;
import com.mitlab.ci.zbox.task.ZboxTaskDetails;

public class ZboxServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Logger logger = Logger.getLogger(this.getClass().getName());
    private ThreadLocal<byte[]> dataBuffer = new ThreadLocal<byte[]>() {
        @Override
        protected byte[] initialValue() {
            return new byte[1024];
        }
    };
    private volatile ZboxSession session;
    private static InitDao initDao = null;
    private static IssueMappingDao issueDao = null;
    private static SettingDao settingDao = null;
    private static ActionMappingDao actionMappingDao = null;
    private long zboxReloginTime;
    private long zboxLastAccessTime;

    @Override
    public void destroy() {
        ZboxUtil.getInstance().logout(session);
        session = null;
        initDao.closeConn();
        initDao = null;
    }

  

    @Override
    public void init() throws ServletException {
    	initDao = new InitDao();
    	issueDao = new IssueMappingDao();
    	settingDao = new SettingDao();
    	actionMappingDao = new ActionMappingDao();
    	if(!initDao.initDataTable()){
    		logger.info("初始化数据表错误！");
    	}
    	
        if(!this.loginSession()){
        	logger.info("登录失败：初始化setting错误！");
        }
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String isUpdateSession = request.getParameter("m");
        if(isUpdateSession!=null){
        	loginSession();
        	logger.info(".......... Relogin Session ..........");
        	response.getWriter().print("0000");
        	return;
        }
        
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
                if(this.session.getSettingInfo()==null){
                	//重新登录
                	this.loginSession();
                }
                ZboxUtil.getInstance().login(this.session.getSettingInfo().getZboxUser(), 
                		this.session.getSettingInfo().getZboxPassword(), session);
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
                String gitlabToken = this.session.getSettingInfo().getGitlabToken();
                GitlabUser gitlabUser = GitlabUtil.getInstance().getUserDetails(bug.getBug().getAssignedTo(), gitlabToken);
                if (gitlabUser != null) {
                    issueRequest.setAssigneeIds(new Long[] {gitlabUser.getId()});
                }
                ZboxBugDetails bugDetails = bug.getBug();
                String cacheId = "Bug-" + bugDetails.getId();
                issueRequest.setId(cacheId);
                String title = bugDetails.getTitle();
                issueRequest.setTitle(title);
                issueRequest.setDescription(bugDetails.getSteps());
                setIssueStatus(notify, issueRequest);
                //String project = getInitParameter(bug.getBug().getProjectName());
                String project = this.session.getSettingInfo().getGitProject();
                boolean foundIssueMapping = false;
                IssueMappingEntity issue =  issueDao.findIssueMapping(cacheId);
                if(issue!=null){
                	foundIssueMapping = true;
                	issueRequest.setId(issue.getGid());
                    issueRequest.setIssueIid(issue.getGiid());
                    project = issue.getProject();
                	if (logger.isLoggable(Level.INFO)) {
                        logger.info("hit db:{issueId:" + issueRequest.getId() + ", issueIid:" + issueRequest.getIssueIid() + ", project:" + project + ",zid:" + cacheId + "}");
                    }
                }
                IssueResponse issueResponse = GitlabUtil.getInstance().createIssue(project, issueRequest, gitlabToken);
                if(issueResponse!=null){
                	if (!foundIssueMapping) {
                        issueDao.setIssueMapping2DB(issueRequest, cacheId, project, issueResponse);
                    }
                }else{
                	logger.info("ERROR：请检查GitlabProject名称是否配置正确！");
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
        //String gitlabAction = this.getInitParameter("zboxAction:" + notify.getAction());
        String gitlabAction = actionMappingDao.geGitlabActionByZboxAction("zboxAction:" + notify.getAction());
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
        GitlabUser gitlabUser = GitlabUtil.getInstance().getUserDetails(task.getTask().getAssignedTo(), this.session.getSettingInfo().getGitlabToken());
        if (gitlabUser != null) {
            issueRequest.setAssigneeIds(new Long[] {gitlabUser.getId()});
        }
        ZboxTaskDetails taskDetails = task.getTask();
        String cacheId = "Task-" + taskDetails.getId();
        issueRequest.setId(cacheId);
        String title = task.getTitle();
        title = title.substring(title.indexOf(" "), title.lastIndexOf("/"));
        issueRequest.setTitle(title);
        issueRequest.setDescription(taskDetails.getDesc());
        setIssueStatus(notify, issueRequest);
        //@TODO 项目配置需要更新
        //String project = getInitParameter(task.getProject().getName());
        String project = this.session.getSettingInfo().getGitProject();
        boolean foundIssueMapping = false;
        IssueMappingEntity issue =  issueDao.findIssueMapping(cacheId);
        if(issue!=null){
        	foundIssueMapping = true;
        	issueRequest.setId(issue.getGid());
            issueRequest.setIssueIid(issue.getGiid());
            project = issue.getProject();
        	if (logger.isLoggable(Level.INFO)) {
                logger.info("hit db:{issueId:" + issueRequest.getId() + ", issueIid:" + issueRequest.getIssueIid() + ", project:" + project + ",zid:" + cacheId + "}");
            }
        }
        String gitlabToken = this.session.getSettingInfo().getGitlabToken();
        IssueResponse issueResponse = GitlabUtil.getInstance().createIssue(project, issueRequest, gitlabToken);
        if(issueResponse!=null){
        	 if (!foundIssueMapping) {
                 issueDao.setIssueMapping2DB(issueRequest, cacheId, project, issueResponse);
             }
        }else{
        	logger.info("ERROR：请检查GitlabProject名称是否配置正确！");
        }
       
    }

    
    private boolean loginSession(){
    	SettingEntity settingInfo = settingDao.getSettingInfo();
    	if(settingInfo == null ) return false;
    	ZboxUtil.getInstance().setAccessUrl(settingInfo.getZboxUrl());
        GitlabUtil.getInstance().setAccessUrl(settingInfo.getGitlabUrl());
        this.session = ZboxUtil.getInstance().getZboxSession();
        this.session.setSettingInfo(settingInfo);
        ZboxUtil.getInstance().login(settingInfo.getZboxUser(), settingInfo.getZboxPassword() , session);
        if (logger.isLoggable(Level.INFO)) {
            logger.info("登录SESSION:"+this.session.toString());
        }
        return true;
    }
    
    
}
