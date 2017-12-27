package com.mitlab.ci.manager;

public class IssueMappingEntity {

	/**
	 * uuid主键
	 */
	private String id = BaseUtil.getUuid();
	/**
	 * bug+issueId/task+issueId
	 */
	private String zid = null;
	private String gid = null;
	private long giid = 1L;
	/**
	 * 记录当前任务指派的用户
	 */
	private String assignTo = null;
	
	/**
	 * 对应的gitlab项目
	 */
	private String project = null;
	
	public String getGid() {
		return gid;
	}
	public String getProject() {
		return project;
	}
	public void setGid(String gid) {
		this.gid = gid;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public long getGiid() {
		return giid;
	}
	public void setGiid(long giid) {
		this.giid = giid;
	}
	public String getZid() {
		return zid;
	}
	public void setZid(String zid) {
		this.zid = zid;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAssignTo() {
		return assignTo;
	}
	public void setAssignTo(String assignTo) {
		this.assignTo = assignTo;
	}
	@Override
	public String toString() {
		return "IssueMappingEntity [id=" + id + ", zid=" + zid + ", gid=" + gid + ", giid=" + giid + ", assignTo="
				+ assignTo + ", project=" + project + "]";
	}
	
}
