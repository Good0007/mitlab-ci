package com.mitlab.ci.zbox.bug;

import org.codehaus.jackson.annotate.JsonProperty;

public class ZboxBugDetails {
    private String id;
    private String product;
    private String branch;
    private String module;
    private String project;
    private String plan;
    private String story;
    private String storyVersion;
    private String task;
    private String toTask;
    private String toStory;
    private String title;
    private String keywords;
    private String pri;
    private String type;
    private String codeerror;
    private String os;
    private String browser;
    private String hardware;
    private String found;
    private String steps;
    private String confirmed;
    private String activatedCount;
    private String activatedDate;
    private String mailto;
    private String openedBy;
    private String openedDate;
    private String openedBuild;
    private String assignedTo;
    private String assignedDate;
    private String deadline;
    private String resolvedBy;
    private String resolution;
    private String resolvedBuild;
    private String resolvedDate;
    private String closedBy;
    private String closedDate;
    private String duplicateBug;
    private String linkBug;
    @JsonProperty("case")
    private String caseReason;
    private String caseVersion;
    private String result;
    private String testtask;
    private String lastEditedBy;
    private String lastEditedDate;
    private String deleted;
    private String projectName;
    private String storyTitle;
    private String storyStatus;
    private String latestStoryVersion;
    private String taskName;
    private String planName;
    private String branchName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public String getStoryVersion() {
        return storyVersion;
    }

    public void setStoryVersion(String storyVersion) {
        this.storyVersion = storyVersion;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getToTask() {
        return toTask;
    }

    public void setToTask(String toTask) {
        this.toTask = toTask;
    }

    public String getToStory() {
        return toStory;
    }

    public void setToStory(String toStory) {
        this.toStory = toStory;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getPri() {
        return pri;
    }

    public void setPri(String pri) {
        this.pri = pri;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCodeerror() {
        return codeerror;
    }

    public void setCodeerror(String codeerror) {
        this.codeerror = codeerror;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getHardware() {
        return hardware;
    }

    public void setHardware(String hardware) {
        this.hardware = hardware;
    }

    public String getFound() {
        return found;
    }

    public void setFound(String found) {
        this.found = found;
    }

    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }

    public String getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(String confirmed) {
        this.confirmed = confirmed;
    }

    public String getActivatedCount() {
        return activatedCount;
    }

    public void setActivatedCount(String activatedCount) {
        this.activatedCount = activatedCount;
    }

    public String getActivatedDate() {
        return activatedDate;
    }

    public void setActivatedDate(String activatedDate) {
        this.activatedDate = activatedDate;
    }

    public String getMailto() {
        return mailto;
    }

    public void setMailto(String mailto) {
        this.mailto = mailto;
    }

    public String getOpenedBy() {
        return openedBy;
    }

    public void setOpenedBy(String openedBy) {
        this.openedBy = openedBy;
    }

    public String getOpenedDate() {
        return openedDate;
    }

    public void setOpenedDate(String openedDate) {
        this.openedDate = openedDate;
    }

    public String getOpenedBuild() {
        return openedBuild;
    }

    public void setOpenedBuild(String openedBuild) {
        this.openedBuild = openedBuild;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public String getAssignedDate() {
        return assignedDate;
    }

    public void setAssignedDate(String assignedDate) {
        this.assignedDate = assignedDate;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getResolvedBy() {
        return resolvedBy;
    }

    public void setResolvedBy(String resolvedBy) {
        this.resolvedBy = resolvedBy;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getResolvedBuild() {
        return resolvedBuild;
    }

    public void setResolvedBuild(String resolvedBuild) {
        this.resolvedBuild = resolvedBuild;
    }

    public String getResolvedDate() {
        return resolvedDate;
    }

    public void setResolvedDate(String resolvedDate) {
        this.resolvedDate = resolvedDate;
    }

    public String getClosedBy() {
        return closedBy;
    }

    public void setClosedBy(String closedBy) {
        this.closedBy = closedBy;
    }

    public String getClosedDate() {
        return closedDate;
    }

    public void setClosedDate(String closedDate) {
        this.closedDate = closedDate;
    }

    public String getDuplicateBug() {
        return duplicateBug;
    }

    public void setDuplicateBug(String duplicateBug) {
        this.duplicateBug = duplicateBug;
    }

    public String getLinkBug() {
        return linkBug;
    }

    public void setLinkBug(String linkBug) {
        this.linkBug = linkBug;
    }

    public String getCaseReason() {
        return caseReason;
    }

    public void setCaseReason(String caseReason) {
        this.caseReason = caseReason;
    }

    public String getCaseVersion() {
        return caseVersion;
    }

    public void setCaseVersion(String caseVersion) {
        this.caseVersion = caseVersion;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getTesttask() {
        return testtask;
    }

    public void setTesttask(String testtask) {
        this.testtask = testtask;
    }

    public String getLastEditedBy() {
        return lastEditedBy;
    }

    public void setLastEditedBy(String lastEditedBy) {
        this.lastEditedBy = lastEditedBy;
    }

    public String getLastEditedDate() {
        return lastEditedDate;
    }

    public void setLastEditedDate(String lastEditedDate) {
        this.lastEditedDate = lastEditedDate;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getStoryTitle() {
        return storyTitle;
    }

    public void setStoryTitle(String storyTitle) {
        this.storyTitle = storyTitle;
    }

    public String getStoryStatus() {
        return storyStatus;
    }

    public void setStoryStatus(String storyStatus) {
        this.storyStatus = storyStatus;
    }

    public String getLatestStoryVersion() {
        return latestStoryVersion;
    }

    public void setLatestStoryVersion(String latestStoryVersion) {
        this.latestStoryVersion = latestStoryVersion;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    @Override
    public String toString() {
        return "ZboxBugDetails{" +
                "id='" + id + '\'' +
                ", product='" + product + '\'' +
                ", branch='" + branch + '\'' +
                ", module='" + module + '\'' +
                ", project='" + project + '\'' +
                ", plan='" + plan + '\'' +
                ", story='" + story + '\'' +
                ", storyVersion='" + storyVersion + '\'' +
                ", task='" + task + '\'' +
                ", toTask='" + toTask + '\'' +
                ", toStory='" + toStory + '\'' +
                ", title='" + title + '\'' +
                ", keywords='" + keywords + '\'' +
                ", pri='" + pri + '\'' +
                ", type='" + type + '\'' +
                ", codeerror='" + codeerror + '\'' +
                ", os='" + os + '\'' +
                ", browser='" + browser + '\'' +
                ", hardware='" + hardware + '\'' +
                ", found='" + found + '\'' +
                ", steps='" + steps + '\'' +
                ", confirmed='" + confirmed + '\'' +
                ", activatedCount='" + activatedCount + '\'' +
                ", activatedDate='" + activatedDate + '\'' +
                ", mailto='" + mailto + '\'' +
                ", openedBy='" + openedBy + '\'' +
                ", openedDate='" + openedDate + '\'' +
                ", openedBuild='" + openedBuild + '\'' +
                ", assignedTo='" + assignedTo + '\'' +
                ", assignedDate='" + assignedDate + '\'' +
                ", deadline='" + deadline + '\'' +
                ", resolvedBy='" + resolvedBy + '\'' +
                ", resolution='" + resolution + '\'' +
                ", resolvedBuild='" + resolvedBuild + '\'' +
                ", resolvedDate='" + resolvedDate + '\'' +
                ", closedBy='" + closedBy + '\'' +
                ", closedDate='" + closedDate + '\'' +
                ", duplicateBug='" + duplicateBug + '\'' +
                ", linkBug='" + linkBug + '\'' +
                ", caseReason='" + caseReason + '\'' +
                ", caseVersion='" + caseVersion + '\'' +
                ", result='" + result + '\'' +
                ", testtask='" + testtask + '\'' +
                ", lastEditedBy='" + lastEditedBy + '\'' +
                ", lastEditedDate='" + lastEditedDate + '\'' +
                ", deleted='" + deleted + '\'' +
                ", projectName='" + projectName + '\'' +
                ", storyTitle='" + storyTitle + '\'' +
                ", storyStatus='" + storyStatus + '\'' +
                ", latestStoryVersion='" + latestStoryVersion + '\'' +
                ", taskName='" + taskName + '\'' +
                ", planName='" + planName + '\'' +
                ", branchName='" + branchName + '\'' +
                '}';
    }
}
