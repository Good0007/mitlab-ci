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


@WebServlet("/zboxSettingManager")
public class ZboxSettingManagerServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    private final Logger logger = Logger.getLogger(this.getClass().getName());
    private static SettingDao settingDao;
    
    @Override
    public void init(){
    	settingDao = new SettingDao();
    }
    
    @Override
    public void destroy() {
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
				 if(zboxUrl==null || "".equals(zboxUrl) ){
					 logger.info("更新设置,参数有误 : zboxUrl不得为空！");
					 response.getWriter().print("0001");
					 return ;
				 }
				 if(gitlabUrl==null || "".equals(gitlabUrl) ){
					 logger.info("更新设置,参数有误 : gitlabUrl不得为空！");
					 response.getWriter().print("0001");
					 return ;
				 }
				 if(zboxUser==null || "".equals(zboxUser) ){
					 logger.info("更新设置,参数有误 : zboxUser不得为空！");
					 response.getWriter().print("0001");
					 return ;
				 }
				 if(zboxPassword==null || "".equals(zboxPassword) ){
					 logger.info("更新设置,参数有误 : zboxPassword不得为空！");
					 response.getWriter().print("0001");
					 return ;
				 }
				 if(gitlabToken==null || "".equals(gitlabToken) ){
					 logger.info("更新设置,参数有误 : gitlabToken不得为空！");
					 response.getWriter().print("0001");
					 return ;
				 }
				 SettingEntity settingInfo = new SettingEntity();
				 settingInfo.setZboxUrl(zboxUrl);
				 settingInfo.setGitlabUrl(gitlabUrl);
				 settingInfo.setGitlabToken(gitlabToken);
				 settingInfo.setZboxUser(zboxUser);
				 settingInfo.setZboxPassword(zboxPassword);
				 boolean f = settingDao.updateSettingInfo(settingInfo);
				 if( f ){
					 logger.info("更新成功："+settingInfo.toString());
					 response.getWriter().print("0000");
				 }else{
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
