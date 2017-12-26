package com.mitlab.ci.zbox;

import com.mitlab.ci.AbstractMitlabUtil;
import com.mitlab.ci.manager.dao.SettingDao;
import com.mitlab.ci.zbox.bug.ZboxBug;
import com.mitlab.ci.zbox.bug.ZboxBugResult;
import com.mitlab.ci.zbox.task.ZboxTask;
import com.mitlab.ci.zbox.task.ZboxTaskResult;

import java.util.*;

public final class ZboxUtil extends AbstractMitlabUtil {
    //private static final ZboxUtil ZBOX_UTIL = new ZboxUtil("http://192.168.60.50:26080/zentao");
    protected ZboxUtil(String accessUrl) {
        super(accessUrl);
    }

    public static final ZboxUtil getInstance() {
    	SettingDao setting = new SettingDao();
    	String zboxUrl = setting.getSettingInfo().getZboxUrl();
    	setting.closeConn();
    	return new ZboxUtil(zboxUrl);
    }

    public ZboxSession getZboxSession() {
        Map<String, Object> urlParams = new HashMap<String, Object>();
        Map<String, Object> bodyParams = new HashMap<String, Object>();
        ZboxSessionResult zur = this.proxyPost(bodyParams, urlParams, ZboxSessionResult.class,"/api-getSessionID.json");
        return zur.getSession();
    }

    public static void main(String[] args) {
        ZboxSession session = ZboxUtil.getInstance().getZboxSession();
        //System.out.println(ZboxUtil.getInstance().login("admin", "Cwk199432", session).getIp());
        //System.out.println(ZboxUtil.getInstance().getTask("4", session));
        //System.out.println(ZboxUtil.getInstance().getProjects(session).toString());
        //ZboxUtil.getInstance().logout(session);
    }

    public ZboxUser login(String username, String password, ZboxSession session) {
        Map<String, Object> urlParams = new HashMap<String, Object>();
        urlParams.put(session.getSessionName(), session.getSessionID());
        Map<String, Object> bodyParams = new HashMap<String, Object>();
        bodyParams.put("account", username);
        bodyParams.put("password", password);
        ZboxUserResult zur = this.proxyPost(bodyParams, urlParams, ZboxUserResult.class, "/user-login.json");
        return zur.getUser();
    }

    public ZboxTask getTask(String taskId, ZboxSession session) {
        Map<String, Object> bodyParams = new HashMap<String, Object>();
        Map<String, Object> urlParams = new HashMap<String, Object>();
        urlParams.put(session.getSessionName(), session.getSessionID());
        ZboxTaskResult zur = this.proxyPost(bodyParams, urlParams, ZboxTaskResult.class, "/task-view-" + taskId + ".json");
        return zur.getTask();
    }

    public ZboxBug getBug(String bugId, ZboxSession session) {
        Map<String, Object> bodyParams = new HashMap<String, Object>();
        Map<String, Object> urlParams = new HashMap<String, Object>();
        urlParams.put(session.getSessionName(), session.getSessionID());
        ZboxBugResult zur = this.proxyPost(bodyParams, urlParams, ZboxBugResult.class, "/bug-view-" + bugId + ".json");
        return zur.getBug();
    }

    public ZboxResult logout(ZboxSession session) {
        Map<String, Object> bodyParams = new HashMap<String, Object>();
        bodyParams.put(session.getSessionName(), session.getSessionID());
        Map<String, Object> urlParams = new HashMap<String, Object>();
        return this.proxyPost(bodyParams, urlParams, ZboxResult.class, "/user-logout.json");
    }
    
    public String getProjectsJson(ZboxSession session , String jsonPath){
    	 Map<String, Object> bodyParams = new HashMap<String, Object>();
         Map<String, Object> urlParams = new HashMap<String, Object>();
         urlParams.put(session.getSessionName(), session.getSessionID());
         return this.getJsonString(bodyParams, urlParams,null, jsonPath);
    }
}
