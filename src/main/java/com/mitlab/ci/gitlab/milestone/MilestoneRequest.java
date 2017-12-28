package com.mitlab.ci.gitlab.milestone;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * gitlab 里程碑 创建 request
 * @author 维康
 *
 */
public class MilestoneRequest {

	private String id;
	private String title;
	private String description;
	
	@JsonProperty("due_date")
	private String dueDate;
	
	@JsonProperty("start_date")
	private String startDate ;
	
	@JsonProperty("milestone_id")
	private String milestoneId;
	
	public String getId() {
		return id;
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
	public void setId(String id) {
		this.id = id;
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
	public String getMilestoneId() {
		return milestoneId;
	}
	public void setMilestoneId(String milestoneId) {
		this.milestoneId = milestoneId;
	}
	@Override
	public String toString() {
		return "MilestoneRequest [id=" + id + ", title=" + title + ", description=" + description + ", dueDate="
				+ dueDate + ", startDate=" + startDate + ", milestoneId=" + milestoneId + "]";
	}
	
}
