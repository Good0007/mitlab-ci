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
			String gitlabProject = request.getParameter("gitlabProject");
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
			if(projectsDao.addProject(project)){
				response.getWriter().print("0000");
			}else{
				logger.info("project 新增失败！");
				response.getWriter().print("0001");
				return;
			}
		}else if("updateProject".equals(method)){
			String pid = request.getParameter("pid");
			String zboxProject = request.getParameter("zboxProject");
			String gitlabProject = request.getParameter("gitlabProject");
			if(pid==null || "".equals(pid)){
				logger.info("pid 不得为空！");
				response.getWriter().print("0001");
				return;
			}
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
			project.setPid(pid);
			project.setZboxProject(zboxProject);
			project.setGitlabProject(gitlabProject);
			if(projectsDao.updateProject(project)){
				response.getWriter().print("0000");
			}else{
				logger.info("project 更新失败！");
				response.getWriter().print("0001");
				return;
			}
		}else if("removeProject".equals(method)){
			String pid = request.getParameter("pid");
			ProjectEntity project = projectsDao.getProjectByPid(pid);
			if(projectsDao.deleteProject(pid)){
				//删除该项目的action 
				if(project!=null) actionMappingDao.removeByProject(project.getZboxProject());
				response.getWriter().print("0000");
			}else{
				 logger.info("删除project错误！");
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
