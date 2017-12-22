package com.mitlab.ci;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mitlab.ci.manager.ActionMappingEntity;
import com.mitlab.ci.manager.dao.ActionMappingDao;
import com.mitlab.ci.zbox.ZboxSession;
import com.mitlab.ci.zbox.ZboxUtil;

@WebServlet("/zboxActionManager")
public class zboxActionManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final Logger logger = Logger.getLogger(this.getClass().getName());
    private volatile ZboxSession session;
    private static ActionMappingDao actionMappingDao;
    
    @Override
    public void init(){
    	actionMappingDao = new ActionMappingDao();
    }
    
    @Override
    public void destroy() {
        ZboxUtil.getInstance().logout(session);
        session = null;
        actionMappingDao.closeConn();
        actionMappingDao = null;
    }
       
    public zboxActionManagerServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String method = request.getParameter("m");
		if("addMapping".equals(method)){
			 String zboxAction = request.getParameter("zboxAction");
			 String gitlabAction = request.getParameter("gitlabAction");
			if(zboxAction!=null && gitlabAction!=null){
				ActionMappingEntity mapping = new ActionMappingEntity();
				mapping.setGitlabAction(gitlabAction);
				mapping.setZboxAction(zboxAction);
				if(actionMappingDao.addActionMapping(mapping)){
					logger.info("add mapping:"+mapping.toString());
					response.getWriter().print("0000");
				}else{
					 logger.info("新增actionMapping错误！");
					 response.getWriter().print("0001");
				 }
			}else{
				 logger.info("新增actionMapping：参数有误！");
				 response.getWriter().print("0001");
			 }
		}else if("removeMapping".equals(method)){
			String aid = request.getParameter("aid");
			if(actionMappingDao.removeActionMapping(aid)){
				response.getWriter().print("0000");
			}else{
				 logger.info("删除actionMapping错误！");
				 response.getWriter().print("0001");
			 }
		}else{
			request.setAttribute("actionMappingList", actionMappingDao.getMappingList());
			request.getRequestDispatcher("/page/zboxActionManager.jsp").forward(request, response);
		} 
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	
	

}
