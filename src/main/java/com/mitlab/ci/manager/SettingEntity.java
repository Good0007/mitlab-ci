package com.mitlab.ci.manager;

public class SettingEntity {

	private String sid = "10000";
	private String zboxUrl = null;
	private String gitlabUrl = null;
	private String zboxUser = null;
	private String zboxPassword = null;
	private String gitlabToken = null;
	private String gitProject = null;
	private String zboxProject = null;
	
	public String getSid() {
		return sid;
	}
	public String getZboxUrl() {
		return zboxUrl;
	}
	public String getGitlabUrl() {
		return gitlabUrl;
	}
	public String getZboxUser() {
		return zboxUser;
	}
	public String getZboxPassword() {
		return zboxPassword;
	}
	public String getGitlabToken() {
		return gitlabToken;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public void setZboxUrl(String zboxUrl) {
		this.zboxUrl = zboxUrl;
	}
	public void setGitlabUrl(String gitlabUrl) {
		this.gitlabUrl = gitlabUrl;
	}
	public void setZboxUser(String zboxUser) {
		this.zboxUser = zboxUser;
	}
	public void setZboxPassword(String zboxPassword) {
		this.zboxPassword = zboxPassword;
	}
	public void setGitlabToken(String gitlabToken) {
		this.gitlabToken = gitlabToken;
	}
	public String getGitProject() {
		return gitProject;
	}
	public void setGitProject(String gitProject) {
		this.gitProject = gitProject;
	}
	public String getZboxProject() {
		return zboxProject;
	}
	public void setZboxProject(String zboxProject) {
		this.zboxProject = zboxProject;
	}
	@Override
	public String toString() {
		return "SettingEntity [sid=" + sid + ", zboxUrl=" + zboxUrl + ", gitlabUrl=" + gitlabUrl + ", zboxUser="
				+ zboxUser + ", zboxPassword=" + zboxPassword + ", gitlabToken=" + gitlabToken + ", gitProject="
				+ gitProject + ", zboxProject=" + zboxProject + "]";
	}
	
}
