package com.mitlab.ci.zbox;

import java.util.HashMap;
import java.util.Map;

import com.mitlab.ci.AbstractMitlabUtil;
import com.mitlab.ci.manager.dao.SettingDao;
import com.mitlab.ci.zbox.bug.ZboxBug;
import com.mitlab.ci.zbox.bug.ZboxBugResult;
import com.mitlab.ci.zbox.product.ZboxProductResult;
import com.mitlab.ci.zbox.product.plan.ZboxProductplanResult;
import com.mitlab.ci.zbox.project.ZboxProjectResult;
import com.mitlab.ci.zbox.project.ZboxProjectsResult;
import com.mitlab.ci.zbox.task.ZboxTask;
import com.mitlab.ci.zbox.task.ZboxTaskResult;

public final class ZboxUtil extends AbstractMitlabUtil {
    //private static final ZboxUtil ZBOX_UTIL = new ZboxUtil("http://192.168.60.50:26080/zentao");
    protected ZboxUtil(String accessUrl) {
        super(accessUrl);
    }

    public static final ZboxUtil getInstance(String zboxUrl) {
    	if("".equals(zboxUrl)){
    		SettingDao setting = new SettingDao();
        	zboxUrl = setting.getSettingInfo().getZboxUrl();
        	setting.closeConn();
    	}
    	return new ZboxUtil(zboxUrl);
    }

    public ZboxSession getZboxSession() {
        Map<String, Object> urlParams = new HashMap<String, Object>();
        Map<String, Object> bodyParams = new HashMap<String, Object>();
        ZboxSessionResult zur = this.proxyPost(bodyParams, urlParams, ZboxSessionResult.class,"/api-getSessionID.json");
        return zur.getSession();
    }

    public static void main(String[] args) {
        ZboxSession session = ZboxUtil.getInstance("").getZboxSession();
        ZboxUtil.getInstance("").login("admin", "Cwk199432", session).getIp();
        ZboxProjectsResult zx = ZboxUtil.getInstance("").getAllZboxProjects(session);
        //ZboxProjectResult zs = ZboxUtil.getInstance().getProjectByPid("1" , session);
        //ZboxProductplanResult zs = ZboxUtil.getInstance().getProductPlan("9", session);
        //System.out.println(zs.toString());
        //System.out.println(ZboxUtil.getInstance().login("admin", "Cwk199432", session).getIp());
        //ZboxTask tas = ZboxUtil.getInstance().getTask("4", session);
        System.out.println(zx.toString());
       // ZboxUtil.getInstance().logout(session);
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
    
    
    public ZboxProjectsResult getAllZboxProjects(ZboxSession session){
    	 Map<String, Object> bodyParams = new HashMap<String, Object>();
         bodyParams.put(session.getSessionName(), session.getSessionID());
         Map<String, Object> urlParams = new HashMap<String, Object>();
         ZboxProjectsResult zpr = this.proxyPost(bodyParams, urlParams, ZboxProjectsResult.class, "/project.json");
         return zpr;
    }
    
    /**
     * 获取产品-包该产品含全部计划
     * @param productId
     * @param session
     * @return
     */
    public ZboxProductResult getAllProductPlan(String productId , ZboxSession session){
    	 Map<String, Object> bodyParams = new HashMap<String, Object>();
         bodyParams.put(session.getSessionName(), session.getSessionID());
         Map<String, Object> urlParams = new HashMap<String, Object>();
         ZboxProductResult zpr = this.proxyPost(bodyParams, urlParams, ZboxProductResult.class, "/productplan-browse-"+productId+".json");
         return zpr;
    }
    
    /**
     * 获取产品计划
     * @param plandId
     * @param session
     * @return
     */
    public ZboxProductplanResult getProductPlan(String plandId , ZboxSession session){
   	 	Map<String, Object> bodyParams = new HashMap<String, Object>();
        bodyParams.put(session.getSessionName(), session.getSessionID());
        Map<String, Object> urlParams = new HashMap<String, Object>();
        ZboxProductplanResult zpr = this.proxyPost(bodyParams, urlParams, ZboxProductplanResult.class, "/productplan-view-"+plandId+".json");
        return zpr;
   }
    
    /**
     * 获取项目信息-包含关联的产品
     * @param projectId
     * @param session
     * @return
     */
    public ZboxProjectResult getProjectByPid(String projectId , ZboxSession session){
    	Map<String, Object> bodyParams = new HashMap<String, Object>();
        bodyParams.put(session.getSessionName(), session.getSessionID());
        Map<String, Object> urlParams = new HashMap<String, Object>();
        ZboxProjectResult zpr = this.proxyPost(bodyParams, urlParams, ZboxProjectResult.class, "/project-view-"+projectId+".json");
        return zpr;
    }
    
}
