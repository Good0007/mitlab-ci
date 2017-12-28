package com.mitlab.ci.zbox.project;

public class ZboxProductDetails {
	
	private String id;
	private String name;
	private String type;
	private String branch;
	
	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public String getBranch() {
		return branch;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	@Override
	public String toString() {
		return "ZboxProductDetails [id=" + id + ", name=" + name + ", type=" + type + ", branch=" + branch + "]";
	}
	
}
