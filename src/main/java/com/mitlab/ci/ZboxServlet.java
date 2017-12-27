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
import com.mitlab.ci.manager.ActionMappingEntity;
import com.mitlab.ci.manager.IssueMappingEntity;
import com.mitlab.ci.manager.SettingEntity;
import com.mitlab.ci.manager.dao.ActionMappingDao;
import com.mitlab.ci.manager.dao.InitDao;
import com.mitlab.ci.manager.dao.IssueMappingDao;
import com.mitlab.ci.manager.dao.ProjectDao;
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
    private static ProjectDao projectDao = null;
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
    	projectDao = new ProjectDao();
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
        String method = request.getParameter("m");
        //请求刷新session
        if(method!=null && "updateSession".equals(method)){
        	loginSession();
        	logger.info(".......... Relogin Session ..........");
        	response.getWriter().print("0000");
        	return;
        }else if("getProjects".equals(method)){
        	//获取禅道项目列表
        	String projectJson = ZboxUtil.getInstance().getProjectsJson( this.session, "/project.json");
        	response.getWriter().print(projectJson);
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
            	onBugReceive(notify);
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

    private void setIssueStatus(ZboxNotify notify, IssueRequest issueRequest , String project) {
        ActionMappingEntity action = actionMappingDao.getActionByZboxAction("zboxAction:" + notify.getAction() , project);
        if(action!=null){
        	//添加状态信息
        	if (action.getGitlabAction() != null && !"".equals(action.getGitlabAction())) {
        		issueRequest.setStateEvent(action.getGitlabAction());
        	}
        	//添加label信息
        	 if (action.getGitlabLabel() != null  && !"".equals(action.getGitlabLabel())) {
                 issueRequest.setLabels(action.getGitlabLabel());
             }
        }
    }

    private void onTaskReceive(ZboxNotify notify) {
        ZboxTask task = ZboxUtil.getInstance().getTask(notify.getObjectId(), session);
        IssueRequest issueRequest = new IssueRequest();
        //当前指派信息
        String assignToUser = task.getTask().getAssignedTo();
        ZboxTaskDetails taskDetails = task.getTask();
        String cacheId = "Task-" + taskDetails.getId();
        String zboxProject = task.getProject().getName();
        String gitlabProject = projectDao.findGitlabProjectByZboxProject(zboxProject);
        if(gitlabProject==null){
        	logger.info("onTaskReceive - ERROR：请检查GitlabProject名称是否配置正确！");
        	return ;
        }
        boolean foundIssueMapping = false;
        IssueMappingEntity issue =  issueDao.findIssueMapping(cacheId , gitlabProject);
        if(issue!=null){
        	foundIssueMapping = true;
        	if("closed".equals(assignToUser)){
        		task.getTask().setAssignedTo(issue.getAssignTo());
        	}else{
        		issueDao.updateAssignTo(cacheId, gitlabProject, assignToUser);
        	}
        	issueRequest.setId(issue.getGid());
            issueRequest.setIssueIid(issue.getGiid());
            //gitlabProject = issue.getProject();
        	if (logger.isLoggable(Level.INFO)) {
                logger.info("onTaskReceive - issueMapping : " +issue.toString());
            }
        }
        GitlabUser gitlabUser = GitlabUtil.getInstance().getUserDetails(task.getTask().getAssignedTo(), this.session.getSettingInfo().getGitlabToken());
        if (gitlabUser != null) {
            issueRequest.setAssigneeIds(new Long[] {gitlabUser.getId()});
        }
        issueRequest.setId(cacheId);
        String title = task.getTitle();
        title = title.substring(title.indexOf(" "), title.lastIndexOf("/"));
        issueRequest.setTitle(title);
        issueRequest.setDescription(taskDetails.getDesc());
        setIssueStatus(notify, issueRequest , zboxProject);
        String gitlabToken = this.session.getSettingInfo().getGitlabToken();
        IssueResponse issueResponse = GitlabUtil.getInstance().createIssue(gitlabProject, issueRequest, gitlabToken);
        if(issueResponse!=null){
        	 if (!foundIssueMapping) {
                 issueDao.setIssueMapping2DB(issueRequest, cacheId, gitlabProject,assignToUser, issueResponse);
             }
        }else{
        	logger.info("ERROR：请检查GitlabProject名称是否配置正确！");
        }
       
    }
    
    private void onBugReceive(ZboxNotify notify) {
    	ZboxBug bug = ZboxUtil.getInstance().getBug(notify.getObjectId(), session);
        IssueRequest issueRequest = new IssueRequest();
        String gitlabToken = this.session.getSettingInfo().getGitlabToken();
        //当前指派信息
        String assignToUser = bug.getBug().getAssignedTo();
        ZboxBugDetails bugDetails = bug.getBug();
        String cacheId = "Bug-" + bugDetails.getId();
        String zboxProject = bug.getBug().getProjectName();
        String gitlabProject = projectDao.findGitlabProjectByZboxProject(zboxProject);
        if(gitlabProject==null){
        	logger.info("ERROR：请检查禅道项目名称是否配置正确！");
        	return ;
        }
        boolean foundIssueMapping = false;
        IssueMappingEntity issue =  issueDao.findIssueMapping(cacheId , gitlabProject);
    	if(issue!=null){
	        if("closed".equals(assignToUser)){
	        	//查询当前指派信息
	        	bug.getBug().setAssignedTo(issue.getAssignTo());
	    	}else{
	    		//更新指派信息
	    		issueDao.updateAssignTo(cacheId, gitlabProject, assignToUser);
	    	}
	        foundIssueMapping = true;
        	issueRequest.setId(issue.getGid());
            issueRequest.setIssueIid(issue.getGiid());
        	if (logger.isLoggable(Level.INFO)) {
        		logger.info("onBugReceive - issueMapping : " +issue.toString());
            }
    	}
        GitlabUser gitlabUser = GitlabUtil.getInstance().getUserDetails(bug.getBug().getAssignedTo(), gitlabToken);
        if (gitlabUser != null) {
            issueRequest.setAssigneeIds(new Long[] {gitlabUser.getId()});
        }
        issueRequest.setId(cacheId);
        String title = bugDetails.getTitle();
        issueRequest.setTitle(title);
        issueRequest.setDescription(bugDetails.getSteps());
        //设置issue 状态 和label
        setIssueStatus(notify, issueRequest ,zboxProject);
        //IssueMappingEntity issue =  issueDao.findIssueMapping(cacheId , gitlabProject);
        IssueResponse issueResponse = GitlabUtil.getInstance().createIssue(gitlabProject, issueRequest, gitlabToken);
        if(issueResponse!=null){
        	if (!foundIssueMapping) {
                issueDao.setIssueMapping2DB(issueRequest, cacheId, gitlabProject,assignToUser,issueResponse);
            }
        }else{
        	logger.info("ERROR：请检查禅道项目名称是否配置正确！");
        }
    }

    
    private boolean loginSession(){
    	try {
    		SettingEntity settingInfo = settingDao.getSettingInfo();
        	if(settingInfo == null ){
        		logger.info("登录失败，基础配置表错误：没有数据！");
        		return false;
        	} 
        	if("".equals(settingInfo.getZboxUrl()) || "".equals(settingInfo.getZboxUser())){
        		logger.info("初次部署，请在配置界面手动填写基础配置！");
        		return true;
        	}
        	ZboxUtil.getInstance().setAccessUrl(settingInfo.getZboxUrl());
            GitlabUtil.getInstance().setAccessUrl(settingInfo.getGitlabUrl());
            this.session = ZboxUtil.getInstance().getZboxSession();
            this.session.setSettingInfo(settingInfo);
            ZboxUtil.getInstance().login(settingInfo.getZboxUser(), settingInfo.getZboxPassword() , session);
            if (logger.isLoggable(Level.INFO)) {
                logger.info("登录SESSION:"+this.session.toString());
            }
		} catch (Exception e) {
			logger.info("登录失败，请检查基础配置是否正确:"+e.getMessage());
			return false;
		}
        return true;
    }
    
    
}
