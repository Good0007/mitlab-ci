package com.mitlab.ci.manager;

public class ActionMappingEntity {

	/**
	 * 生成uuid主键
	 */
	private String aid = BaseUtil.getUuid();
	/**
	 * zbox操作
	 */
	private String zboxAction = null;
	/**
	 * gitlab操作
	 */
	private String gitlabAction = null;
	/**
	 * gitlab 对应的gitlab labels
	 */
	private String gitlabLabel = null;
	
	/**
	 * 所属项目,zbox项目
	 */
	private String project = null;
	
	
	public String getAid() {
		return aid;
	}
	public String getZboxAction() {
		return zboxAction;
	}
	public String getGitlabAction() {
		return gitlabAction;
	}
	public void setAid(String aid) {
		this.aid = aid;
	}
	public void setZboxAction(String zboxAction) {
		this.zboxAction = zboxAction;
	}
	public void setGitlabAction(String gitlabAction) {
		this.gitlabAction = gitlabAction;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	@Override
	public String toString() {
		return "ActionMappingEntity [aid=" + aid + ", zboxAction=" + zboxAction + ", gitlabAction=" + gitlabAction
				+ ", project=" + project + "]";
	}
	public String getGitlabLabel() {
		return gitlabLabel;
	}
	public void setGitlabLabel(String gitlabLabel) {
		this.gitlabLabel = gitlabLabel;
	}
	
}
