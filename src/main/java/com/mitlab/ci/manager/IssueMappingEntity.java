package com.mitlab.ci.manager;

public class IssueMappingEntity {

	private String gid = null;
	private long giid = 1L;
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
	@Override
	public String toString() {
		return "IssueMappingEntity [gid=" + gid + ", giid=" + giid + ", project=" + project + "]";
	}
	
	
}
