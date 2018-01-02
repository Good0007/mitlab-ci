package com.mitlab.ci.gitlab.milestone;

import org.codehaus.jackson.annotate.JsonProperty;

public class MilestoneResponse{
		/**
		 * MilestoneId 主键 ，更新Milestone时用
		 */
	 	private String id;
	 	private String iid;
	 	@JsonProperty("project_id")
	 	private String projectId;
	 	
	 	private String title;
	 	
	 	private String description;
	 	
	 	@JsonProperty("due_date")
	 	private String dueDate;
	 	
	 	@JsonProperty("start_date")
	 	private String startDate;
	 	
	 	private String state;
	 	
	 	@JsonProperty("updated_at")
	 	private String updatedAt;
	 	
	 	@JsonProperty("created_at")
	 	private String createdAt;

		public String getId() {
			return id;
		}

		public String getIid() {
			return iid;
		}

		public String getProjectId() {
			return projectId;
		}

		public String getTitle() {
			return title;
		}

		public String getDescription() {
			return description;
		}

		public String getDueDate() {
			return dueDate;
		}

		public String getStartDate() {
			return startDate;
		}

		public String getState() {
			return state;
		}

		public String getUpdatedAt() {
			return updatedAt;
		}

		public String getCreatedAt() {
			return createdAt;
		}

		public void setId(String id) {
			this.id = id;
		}

		public void setIid(String iid) {
			this.iid = iid;
		}

		public void setProjectId(String projectId) {
			this.projectId = projectId;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public void setDueDate(String dueDate) {
			this.dueDate = dueDate;
		}

		public void setStartDate(String startDate) {
			this.startDate = startDate;
		}

		public void setState(String state) {
			this.state = state;
		}

		public void setUpdatedAt(String updatedAt) {
			this.updatedAt = updatedAt;
		}

		public void setCreatedAt(String createdAt) {
			this.createdAt = createdAt;
		}

		@Override
		public String toString() {
			return "MilestoneResponse [id=" + id + ", iid=" + iid + ", projectId=" + projectId + ", title=" + title
					+ ", description=" + description + ", dueDate=" + dueDate + ", startDate=" + startDate + ", state="
					+ state + ", updatedAt=" + updatedAt + ", createdAt=" + createdAt + "]";
		}
	 	
}
