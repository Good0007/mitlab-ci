package com.mitlab.ci;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mitlab.ci.manager.ProjectEntity;
import com.mitlab.ci.manager.dao.ActionMappingDao;
import com.mitlab.ci.manager.dao.ProjectDao;

@WebServlet("/zboxProjectManager")
public class ZboxProjectManagerServlet extends HttpServlet {
	
	private final Logger logger = Logger.getLogger(this.getClass().getName());
	private static final long serialVersionUID = 1L;
	private ProjectDao projectsDao;
	private ActionMappingDao actionMappingDao;
	@Override
    public void init(){
		projectsDao = new ProjectDao();
		actionMappingDao = new ActionMappingDao();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("m");
		if("addProject".equals(method)){
			String zboxProject = request.getParameter("zboxProject");
			String zboxProjectId = request.getParameter("zboxProjectId");
			String gitlabProject = request.getParameter("gitlabProject");
			String planSync = request.getParameter("planSync");
			if(zboxProject==null || "".equals(zboxProject)){
				logger.info("zboxProject 不得为空！");
				response.getWriter().print("0001");
				return;
			}
			if(gitlabProject==null || "".equals(gitlabProject)){
				logger.info("gitlabProject 不得为空！");
				response.getWriter().print("0001");
				return;
			}
			ProjectEntity project = new ProjectEntity();
			project.setZboxProject(new String(zboxProject.getBytes("ISO8859-1"),"UTF-8"));
			project.setGitlabProject(new String(gitlabProject.getBytes("ISO8859-1"),"UTF-8"));
			project.setZboxProjectId(zboxProjectId);
			project.setPlanSync(planSync);
			if(projectsDao.addProject(project)){
				response.getWriter().print("0000");
			}else{
				logger.info("project 新增失败！");
				response.getWriter().print("0001");
				return;
			}
		}else if("removeProject".equals(method)){
			String projectId = request.getParameter("projectId");
			ProjectEntity project = projectsDao.getProjectByProjectId(projectId);
			if(projectsDao.deleteByProjectId(projectId)){
				//删除该项目的action 
				if(project!=null) actionMappingDao.removeByProject(project.getZboxProject());
				response.getWriter().print("0000");
			}else{
				 logger.info("删除project错误！");
				 response.getWriter().print("0001");
			 }
		}else if("updatePlanSync".equals(method)){
			String pid = request.getParameter("pid");
			String planSync = request.getParameter("planSync");
			if(projectsDao.updatePlanSync(planSync, pid)){
				response.getWriter().print("0000");
			}else{
				logger.info("updatePlanSync 更新失败！");
				response.getWriter().print("0001");
			}
		}else{
			request.setAttribute("projectList", projectsDao.getAllProjects());
			request.getRequestDispatcher("/page/zboxProjectManager.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
