package com.mitlab.ci;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mitlab.ci.manager.SettingEntity;
import com.mitlab.ci.manager.dao.SettingDao;
import com.mitlab.ci.zbox.ZboxSession;
import com.mitlab.ci.zbox.ZboxUtil;


@WebServlet("/zboxSettingManager")
public class ZboxSettingManagerServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	

    public ZboxSettingManagerServlet() {
        super();
    }
   
    private final Logger logger = Logger.getLogger(this.getClass().getName());
    private volatile ZboxSession session;
    private static SettingDao settingDao;
    
    @Override
    public void init(){
    	settingDao = new SettingDao();
    }
    
    @Override
    public void destroy() {
        ZboxUtil.getInstance().logout(session);
        session = null;
        settingDao.closeConn();
        settingDao = null;
    }

  
	protected void doGet(HttpServletRequest  request, HttpServletResponse response) throws ServletException, IOException {
		 	 String method = request.getParameter("m");
			 if("updateSetting".equals(method)){
				 String zboxUrl = request.getParameter("zboxUrl");
				 String gitlabUrl = request.getParameter("gitlabUrl");
				 String zboxUser = request.getParameter("zboxUser");
				 String zboxPassword = request.getParameter("zboxPassword");
				 String gitlabToken = request.getParameter("gitlabToken");
				 String zboxProject = request.getParameter("zboxProject");
				 String gitProject = request.getParameter("gitProject");
				 if(zboxUrl!=null && gitlabUrl!=null && zboxUser!=null && 
						 zboxPassword!=null && gitlabToken !=null){
					 SettingEntity settingInfo = new SettingEntity();
					 settingInfo.setZboxUrl(zboxUrl);
					 settingInfo.setGitlabUrl(gitlabUrl);
					 settingInfo.setGitlabToken(gitlabToken);
					 settingInfo.setZboxUser(zboxUser);
					 settingInfo.setZboxPassword(zboxPassword);
					 settingInfo.setZboxProject(zboxProject);
					 settingInfo.setGitProject(gitProject);
					 boolean f = settingDao.updateSettingInfo(settingInfo);
					 if( f ){
						 logger.info("更新成功："+settingInfo.toString());
						 response.getWriter().print("0000");
					 }else{
						 response.getWriter().print("0001");
					 }
				 }else{
					 logger.info("更新设置：参数有误！");
					 response.getWriter().print("0001");
				 }
				 
			}else{
				request.setAttribute("baseSetting", settingDao.getSettingInfo());
				request.getRequestDispatcher("/page/zboxSettingManager.jsp").forward(request, response);
			} 
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	

}
