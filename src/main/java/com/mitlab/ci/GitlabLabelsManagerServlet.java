package com.mitlab.ci;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mitlab.ci.gitlab.GitlabUtil;
import com.mitlab.ci.gitlab.issue.LabelsResponse;
import com.mitlab.ci.manager.ProjectEntity;
import com.mitlab.ci.manager.SettingEntity;
import com.mitlab.ci.manager.dao.ProjectDao;
import com.mitlab.ci.manager.dao.SettingDao;

@WebServlet("/gitlabLabelsManager")
public class GitlabLabelsManagerServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private SettingDao settingDao = null;
	private ProjectDao projectDao = null;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("m");
		settingDao = new SettingDao();
		projectDao = new ProjectDao();
		if("getLabels".equals(method)){
			  String projectId = request.getParameter("projectId");
		      ProjectEntity projectE = projectDao.getProjectByProjectId(projectId);
		      if(projectE!=null){
		    	    SettingEntity settingInfo =  settingDao.getSettingInfo();
				    LabelsResponse[] res = GitlabUtil.getInstance("").getProjectLabels(projectE.getGitlabProject(), settingInfo.getGitlabToken());
					String json = new ObjectMapper().writeValueAsString(res);
					response.getWriter().write(json);
		      }
		}
		if(settingDao!=null) settingDao.closeConn();
		settingDao = null;
		if(projectDao!=null)  projectDao.closeConn();
		projectDao = null;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
