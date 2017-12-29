package com.mitlab.ci.gitlab.issue;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * label标记
 * @author 维康
 *
 */
public class LabelsResponse {

	/**
	 * id
	 */
     private String id;
     /**
      * 名称
      */
     @JsonProperty("name")
     private String name;
     /**
      * 颜色
      */
     @JsonProperty("color")
     private String color;
     private String description;
     /**
      * open issue统计
      */
     @JsonProperty("open_issues_count")
     private String openIssuesCount; 
     
     @JsonProperty("closed_issues_count")
     private String closedIssuesCount;
     
     @JsonProperty("openMergeRequestsCount")
     private String openMergeRequestsCount; 
     private String priority;
     private String subscribed;
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getColor() {
		return color;
	}
	public String getDescription() {
		return description;
	}
	public String getOpenIssuesCount() {
		return openIssuesCount;
	}
	public String getClosedIssuesCount() {
		return closedIssuesCount;
	}
	public String getOpenMergeRequestsCount() {
		return openMergeRequestsCount;
	}
	public String getPriority() {
		return priority;
	}
	public String getSubscribed() {
		return subscribed;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setOpenIssuesCount(String openIssuesCount) {
		this.openIssuesCount = openIssuesCount;
	}
	public void setClosedIssuesCount(String closedIssuesCount) {
		this.closedIssuesCount = closedIssuesCount;
	}
	public void setOpenMergeRequestsCount(String openMergeRequestsCount) {
		this.openMergeRequestsCount = openMergeRequestsCount;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public void setSubscribed(String subscribed) {
		this.subscribed = subscribed;
	}
	@Override
	public String toString() {
		return "LabelsResponse [id=" + id + ", name=" + name + ", color=" + color + ", description=" + description
				+ ", openIssuesCount=" + openIssuesCount + ", closedIssuesCount=" + closedIssuesCount
				+ ", openMergeRequestsCount=" + openMergeRequestsCount + ", priority=" + priority + ", subscribed="
				+ subscribed + "]";
	}
     
}
