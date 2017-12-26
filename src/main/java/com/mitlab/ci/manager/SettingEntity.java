package com.mitlab.ci.manager;

public class SettingEntity {

	/**
	 * 主键,该实体对应表只有一条记录，主键初始为10000
	 */
	private String sid = "10000";
	/**
	 * 禅道地址
	 */
	private String zboxUrl = null;
	/**
	 * gitlab地址
	 */
	private String gitlabUrl = null;
	/**
	 * 禅道管理员用户名
	 */
	private String zboxUser = null;
	/**
	 * 禅道管理员密码
	 */
	private String zboxPassword = null;
	/**
	 * gitlab令牌
	 */
	private String gitlabToken = null;
	
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
	@Override
	public String toString() {
		return "SettingEntity [sid=" + sid + ", zboxUrl=" + zboxUrl + ", gitlabUrl=" + gitlabUrl + ", zboxUser="
				+ zboxUser + ", zboxPassword=" + zboxPassword + ", gitlabToken=" + gitlabToken + "]";
	}
	
}
