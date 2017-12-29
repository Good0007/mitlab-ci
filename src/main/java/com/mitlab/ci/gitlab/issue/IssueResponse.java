package com.mitlab.ci.gitlab.issue;

import com.mitlab.ci.gitlab.user.GitlabUser;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.Arrays;

public class IssueResponse {
    @JsonProperty("project_id")
    private Long projectId;
    private String id;
    @JsonProperty("created_at")
    private String createdAt;
    private Long iid;
    private String title;
    private String state;
    private GitlabUser[] assignees;
    private String[] labels;
    private IssueAuthor author;
    private String description;
    @JsonProperty("updated_at")
    private String updatedAt;
    @JsonProperty("closed_at")
    private String closedAt;
    private Milestone milestone;
    private Boolean subscribed;
    @JsonProperty("user_notes_count")
    private Integer userNotesCount;
    @JsonProperty("due_date")
    private String dueDate;
    @JsonProperty("web_url")
    private String webUrl;
    @JsonProperty("time_stats")
    private IssueTimeStats timeStats;
    private Boolean confidential;
    @JsonProperty("discussion_locked")
    private Boolean discussionLocked;
    @JsonProperty("_links")
    private IssueLinks links;

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Long getIid() {
        return iid;
    }

    public void setIid(Long iid) {
        this.iid = iid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public GitlabUser[] getAssignees() {
        return assignees;
    }

    public void setAssignees(GitlabUser[] assignees) {
        this.assignees = assignees;
    }

    public String[] getLabels() {
        return labels;
    }

    public void setLabels(String[] labels) {
        this.labels = labels;
    }

    public IssueAuthor getAuthor() {
        return author;
    }

    public void setAuthor(IssueAuthor author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getClosedAt() {
        return closedAt;
    }

    public void setClosedAt(String closedAt) {
        this.closedAt = closedAt;
    }

    public Boolean getSubscribed() {
        return subscribed;
    }

    public void setSubscribed(Boolean subscribed) {
        this.subscribed = subscribed;
    }

    public Integer getUserNotesCount() {
        return userNotesCount;
    }

    public void setUserNotesCount(Integer userNotesCount) {
        this.userNotesCount = userNotesCount;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public IssueTimeStats getTimeStats() {
        return timeStats;
    }

    public void setTimeStats(IssueTimeStats timeStats) {
        this.timeStats = timeStats;
    }

    public Boolean getConfidential() {
        return confidential;
    }

    public void setConfidential(Boolean confidential) {
        this.confidential = confidential;
    }

    public Boolean getDiscussionLocked() {
        return discussionLocked;
    }

    public void setDiscussionLocked(Boolean discussionLocked) {
        this.discussionLocked = discussionLocked;
    }

    public IssueLinks getLinks() {
        return links;
    }

    public void setLinks(IssueLinks links) {
        this.links = links;
    }

    @Override
    public String toString() {
        return "IssueResponse{" +
                "projectId=" + projectId +
                ", id='" + id + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", iid=" + iid +
                ", title='" + title + '\'' +
                ", state='" + state + '\'' +
                ", assignees=" + Arrays.toString(assignees) +
                ", labels=" + Arrays.toString(labels) +
                ", author=" + author +
                ", description='" + description + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", closedAt='" + closedAt + '\'' +
                ", milestone='" + milestone.toString() + '\'' +
                ", subscribed=" + subscribed +
                ", userNotesCount=" + userNotesCount +
                ", dueDate='" + dueDate + '\'' +
                ", webUrl='" + webUrl + '\'' +
                ", timeStats=" + timeStats +
                ", confidential=" + confidential +
                ", discussionLocked=" + discussionLocked +
                ", links=" + links +
                '}';
    }
}
