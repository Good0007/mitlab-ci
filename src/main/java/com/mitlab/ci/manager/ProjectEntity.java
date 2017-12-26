package com.mitlab.ci.manager;

public class ProjectEntity {

	/**
	 * 生成uuid主键
	 */
	private String pid = BaseUtil.getUuid();
	/**
	 * 禅道项目名称
	 */
	private String zboxProject = null;
	/**
	 * Gitlab项目名称
	 */
	private String gitlabProject = null;
	
	
	public String getPid() {
		return pid;
	}
	public String getZboxProject() {
		return zboxProject;
	}
	public String getGitlabProject() {
		return gitlabProject;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public void setZboxProject(String zboxProject) {
		this.zboxProject = zboxProject;
	}
	public void setGitlabProject(String gitlabProject) {
		this.gitlabProject = gitlabProject;
	}
	@Override
	public String toString() {
		return "ProjectsEntity [pid=" + pid + ", zboxProject=" + zboxProject + ", gitlabProject=" + gitlabProject + "]";
	}
	
	
}
