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
import com.mitlab.ci.manager.dao.ProjectDao;

@WebServlet("/zboxActionManager")
public class zboxActionManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final Logger logger = Logger.getLogger(this.getClass().getName());
    private static ActionMappingDao actionMappingDao;
    private static ProjectDao projectDao = null;
    
    @Override
    public void init(){
    	actionMappingDao = new ActionMappingDao();
    	projectDao = new ProjectDao();
    }
    
    @Override
    public void destroy() {
        actionMappingDao.closeConn();
        actionMappingDao = null;
    }
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String method = request.getParameter("m");
		if("addMapping".equals(method)){
			String project = request.getParameter("project");
			String zboxAction = request.getParameter("zboxAction");
			String gitlabAction = request.getParameter("gitlabAction");
			String gitlabLabel = request.getParameter("gitlabLabel");
			if(zboxAction==null || "".equals(zboxAction)){
				logger.info("新增actionMapping参数有误：zboxAction不得为空");
				 response.getWriter().print("0001");
				 return ;
			}
			/*if(gitlabAction==null || "".equals(gitlabAction)){
				logger.info("新增actionMapping参数有误：gitlabAction不得为空");
				 response.getWriter().print("0001");
				 return ;
			}*/
			/*if(gitlabLabel==null || "".equals(gitlabLabel)){
				logger.info("新增actionMapping参数有误：gitlabLabel不得为空");
				 response.getWriter().print("0001");
				 return ;
			}*/
			ActionMappingEntity mapping = new ActionMappingEntity();
			mapping.setGitlabAction(gitlabAction);
			mapping.setZboxAction(zboxAction);
			mapping.setProject(new String(project.getBytes("ISO8859-1"),"UTF-8"));
			mapping.setGitlabLabel(gitlabLabel);
			if(actionMappingDao.addActionMapping(mapping)){
				logger.info("add mapping:"+mapping.toString());
				response.getWriter().print("0000");
			}else{
				 logger.info("新增actionMapping失败！");
				 response.getWriter().print("0001");
			 }
		}else if("removeMapping".equals(method)){
			String aid = request.getParameter("aid");
			if(actionMappingDao.removeByAid(aid)){
				response.getWriter().print("0000");
			}else{
				 logger.info("删除actionMapping失败！");
				 response.getWriter().print("0001");
			 }
		}else{
			String project = request.getParameter("project");
			if(project == null || "".equals(project)){
				project = null;
			}else{
				project = new String(project.getBytes("ISO8859-1"),"UTF-8");
			}
			request.setAttribute("project", project);
			request.setAttribute("actionMappingList", actionMappingDao.getMappingListByProject(project));
			request.setAttribute("projectList", projectDao.getAllProjects());
			request.getRequestDispatcher("/page/zboxActionManager.jsp").forward(request, response);
		} 
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	
	

}
