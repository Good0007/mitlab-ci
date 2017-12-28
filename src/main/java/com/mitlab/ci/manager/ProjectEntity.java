package com.mitlab.ci.manager;

public class ProjectEntity {

	/**
	 * 生成uuid主键
	 */
	private String pid = BaseUtil.getUuid();
	/**
	 * 禅道项目Id
	 */
	private String zboxProjectId = null;
	/**
	 * 禅道项目名称
	 */
	private String zboxProject = null;
	/**
	 * Gitlab项目名称
	 */
	private String gitlabProject = null;
	/**
	 * 创建产品计划时，是否同步计划到gitlab的里程碑,0 不同步 ，1 同步
	 */
	private String planSync;
	
	
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
	public String getPlanSync() {
		return planSync;
	}
	public void setPlanSync(String planSync) {
		this.planSync = planSync;
	}
	public String getZboxProjectId() {
		return zboxProjectId;
	}
	public void setZboxProjectId(String zboxProjectId) {
		this.zboxProjectId = zboxProjectId;
	}
	@Override
	public String toString() {
		return "ProjectEntity [pid=" + pid + ", zboxProjectId=" + zboxProjectId + ", zboxProject=" + zboxProject
				+ ", gitlabProject=" + gitlabProject + ", planSync=" + planSync + "]";
	}
	
}
