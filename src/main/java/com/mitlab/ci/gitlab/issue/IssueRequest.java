package com.mitlab.ci.gitlab.issue;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.Arrays;

public class IssueRequest {
    private String id;
    @JsonProperty("issue_iid")
    private Long issueIid;
    private String title;
    private String description;
    private Boolean confidential;
    @JsonProperty("assignee_ids")
    private Long[] assigneeIds;
    @JsonProperty("milestone_id")
    private Long milestoneId;
    private String labels;
    /** ISO 8601 formatted, e.g. 2016-03-11T03:45:40Z*/
    @JsonProperty("created_at")
    private String createdAt;
    /** Date time string in the format YEAR-MONTH-DAY, e.g. 2016-03-11 */
    @JsonProperty("due_date")
    private String dueDate;
    @JsonProperty("merge_request_to_resolve_discussions_of")
    private Long mergeRequestToResolveDiscussionsOf;
    @JsonProperty("discussion_to_resolve")
    private String discussionToResolve;

    @JsonProperty("state_event")
    private String stateEvent;

    public String getStateEvent() {
        return stateEvent;
    }

    public void setStateEvent(String stateEvent) {
        this.stateEvent = stateEvent;
    }

    public Long getIssueIid() {
        return issueIid;
    }

    public void setIssueIid(Long issueIid) {
        this.issueIid = issueIid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getConfidential() {
        return confidential;
    }

    public void setConfidential(Boolean confidential) {
        this.confidential = confidential;
    }

    public Long[] getAssigneeIds() {
        return assigneeIds;
    }

    public void setAssigneeIds(Long[] assigneeIds) {
        this.assigneeIds = assigneeIds;
    }

    public Long getMilestoneId() {
        return milestoneId;
    }

    public void setMilestoneId(Long milestoneId) {
        this.milestoneId = milestoneId;
    }

    public String getLabels() {
        return labels;
    }

    public void setLabels(String labels) {
        this.labels = labels;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public Long getMergeRequestToResolveDiscussionsOf() {
        return mergeRequestToResolveDiscussionsOf;
    }

    public void setMergeRequestToResolveDiscussionsOf(Long mergeRequestToResolveDiscussionsOf) {
        this.mergeRequestToResolveDiscussionsOf = mergeRequestToResolveDiscussionsOf;
    }

    public String getDiscussionToResolve() {
        return discussionToResolve;
    }

    public void setDiscussionToResolve(String discussionToResolve) {
        this.discussionToResolve = discussionToResolve;
    }

    @Override
    public String toString() {
        return "IssueRequest{" +
                "id='" + id + '\'' +
                ", issueIid=" + issueIid +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", confidential=" + confidential +
                ", assigneeIds=" + Arrays.toString(assigneeIds) +
                ", milestoneId=" + milestoneId +
                ", labels='" + labels + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", dueDate='" + dueDate + '\'' +
                ", mergeRequestToResolveDiscussionsOf=" + mergeRequestToResolveDiscussionsOf +
                ", discussionToResolve='" + discussionToResolve + '\'' +
                '}';
    }
}
