package com.mitlab.ci.gitlab.issue;

import org.codehaus.jackson.annotate.JsonProperty;

public class Milestone {

	@JsonProperty("projectId")
	private String project_id;
	private String description;
	private String state;
	@JsonProperty("due_date")
	private String dueDate;
	private String iid;
	@JsonProperty("created_at")
	private String createdAt;
	private String title;
	private String id;
	@JsonProperty("updated_at")
	private String updatedAt;
	
	public String getProject_id() {
		return project_id;
	}
	public String getDescription() {
		return description;
	}
	public String getState() {
		return state;
	}
	public String getDueDate() {
		return dueDate;
	}
	public String getIid() {
		return iid;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public String getTitle() {
		return title;
	}
	public String getId() {
		return id;
	}
	public String getUpdatedAt() {
		return updatedAt;
	}
	public void setProject_id(String project_id) {
		this.project_id = project_id;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setState(String state) {
		this.state = state;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	public void setIid(String iid) {
		this.iid = iid;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}
	@Override
	public String toString() {
		return "Milestone [project_id=" + project_id + ", description=" + description + ", state=" + state
				+ ", dueDate=" + dueDate + ", iid=" + iid + ", createdAt=" + createdAt + ", title=" + title + ", id="
				+ id + ", updatedAt=" + updatedAt + "]";
	}
	
}
