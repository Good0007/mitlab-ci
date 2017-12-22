package com.mitlab.ci.manager;

public class ActionMappingEntity {

	private String aid = BastUtil.getUuid();
	private String zboxAction = null;
	private String gitlabAction = null;
	
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
	@Override
	public String toString() {
		return "ActionMappingEntity [aid=" + aid + ", zboxAction=" + zboxAction + ", gitlabAction=" + gitlabAction + "]";
	}
	
}
