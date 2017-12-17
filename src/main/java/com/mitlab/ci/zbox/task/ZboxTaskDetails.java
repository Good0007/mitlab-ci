package com.mitlab.ci.zbox.task;

import java.util.Arrays;

public class ZboxTaskDetails {
    private String id;
    private String parent;
    private String project;
    private String module;
    private String story;
    private String storyVersion;
    private String fromBug;
    private String name;
    private String type;
    private String pri;
    private String estimate;
    private String consumed;
    private String left;
    private String deadline;
    private String status;
    private String color;
    private String mailto;
    private String desc;
    private String openedBy;
    private String openedDate;
    private String assignedTo;
    private String assignedDate;
    private String estStarted;
    private String realStarted;
    private String finishedBy;
    private String finishedDate;
    private String canceledBy;
    private String canceledDate;
    private String closedBy;
    private String closedDate;
    private String closedReason;
    private String lastEditedBy;
    private String lastEditedDate;
    private String deleted;
    private String storyID;
    private String storyTitle;
    private String latestStoryVersion;
    private String storyStatus;
    private String assignedToRealName;
    private boolean needConfirm;
    private int progress;
    private String storySpec;
    private String storyVerify;

    private ZboxTaskDetails[] children;
    private String[] team;
    private String[] files;
    private String[] storyFiles;

    private ZboxTaskDetailsHistory[] history;

    public ZboxTaskDetailsHistory[] getHistory() {
        return history;
    }

    public void setHistory(ZboxTaskDetailsHistory[] history) {
        this.history = history;
    }

    public String[] getStoryFiles() {
        return storyFiles;
    }

    public void setStoryFiles(String[] storyFiles) {
        this.storyFiles = storyFiles;
    }

    public String[] getFiles() {
        return files;
    }

    public void setFiles(String[] files) {
        this.files = files;
    }

    public String[] getTeam() {
        return team;
    }

    public void setTeam(String[] team) {
        this.team = team;
    }

    public ZboxTaskDetails[] getChildren() {
        return children;
    }

    public void setChildren(ZboxTaskDetails[] children) {
        this.children = children;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
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

    public String getFromBug() {
        return fromBug;
    }

    public void setFromBug(String fromBug) {
        this.fromBug = fromBug;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPri() {
        return pri;
    }

    public void setPri(String pri) {
        this.pri = pri;
    }

    public String getEstimate() {
        return estimate;
    }

    public void setEstimate(String estimate) {
        this.estimate = estimate;
    }

    public String getConsumed() {
        return consumed;
    }

    public void setConsumed(String consumed) {
        this.consumed = consumed;
    }

    public String getLeft() {
        return left;
    }

    public void setLeft(String left) {
        this.left = left;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMailto() {
        return mailto;
    }

    public void setMailto(String mailto) {
        this.mailto = mailto;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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

    public String getEstStarted() {
        return estStarted;
    }

    public void setEstStarted(String estStarted) {
        this.estStarted = estStarted;
    }

    public String getRealStarted() {
        return realStarted;
    }

    public void setRealStarted(String realStarted) {
        this.realStarted = realStarted;
    }

    public String getFinishedBy() {
        return finishedBy;
    }

    public void setFinishedBy(String finishedBy) {
        this.finishedBy = finishedBy;
    }

    public String getFinishedDate() {
        return finishedDate;
    }

    public void setFinishedDate(String finishedDate) {
        this.finishedDate = finishedDate;
    }

    public String getCanceledBy() {
        return canceledBy;
    }

    public void setCanceledBy(String canceledBy) {
        this.canceledBy = canceledBy;
    }

    public String getCanceledDate() {
        return canceledDate;
    }

    public void setCanceledDate(String canceledDate) {
        this.canceledDate = canceledDate;
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

    public String getClosedReason() {
        return closedReason;
    }

    public void setClosedReason(String closedReason) {
        this.closedReason = closedReason;
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

    public String getStoryID() {
        return storyID;
    }

    public void setStoryID(String storyID) {
        this.storyID = storyID;
    }

    public String getStoryTitle() {
        return storyTitle;
    }

    public void setStoryTitle(String storyTitle) {
        this.storyTitle = storyTitle;
    }

    public String getLatestStoryVersion() {
        return latestStoryVersion;
    }

    public void setLatestStoryVersion(String latestStoryVersion) {
        this.latestStoryVersion = latestStoryVersion;
    }

    public String getStoryStatus() {
        return storyStatus;
    }

    public void setStoryStatus(String storyStatus) {
        this.storyStatus = storyStatus;
    }

    public String getAssignedToRealName() {
        return assignedToRealName;
    }

    public void setAssignedToRealName(String assignedToRealName) {
        this.assignedToRealName = assignedToRealName;
    }

    public boolean isNeedConfirm() {
        return needConfirm;
    }

    public void setNeedConfirm(boolean needConfirm) {
        this.needConfirm = needConfirm;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public String getStorySpec() {
        return storySpec;
    }

    public void setStorySpec(String storySpec) {
        this.storySpec = storySpec;
    }

    public String getStoryVerify() {
        return storyVerify;
    }

    public void setStoryVerify(String storyVerify) {
        this.storyVerify = storyVerify;
    }

    @Override
    public String toString() {
        return "ZboxTaskDetails{" +
                "id='" + id + '\'' +
                ", parent='" + parent + '\'' +
                ", project='" + project + '\'' +
                ", module='" + module + '\'' +
                ", story='" + story + '\'' +
                ", storyVersion='" + storyVersion + '\'' +
                ", fromBug='" + fromBug + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", pri='" + pri + '\'' +
                ", estimate='" + estimate + '\'' +
                ", consumed='" + consumed + '\'' +
                ", left='" + left + '\'' +
                ", deadline='" + deadline + '\'' +
                ", status='" + status + '\'' +
                ", color='" + color + '\'' +
                ", mailto='" + mailto + '\'' +
                ", desc='" + desc + '\'' +
                ", openedBy='" + openedBy + '\'' +
                ", openedDate='" + openedDate + '\'' +
                ", assignedTo='" + assignedTo + '\'' +
                ", assignedDate='" + assignedDate + '\'' +
                ", estStarted='" + estStarted + '\'' +
                ", realStarted='" + realStarted + '\'' +
                ", finishedBy='" + finishedBy + '\'' +
                ", finishedDate='" + finishedDate + '\'' +
                ", canceledBy='" + canceledBy + '\'' +
                ", canceledDate='" + canceledDate + '\'' +
                ", closedBy='" + closedBy + '\'' +
                ", closedDate='" + closedDate + '\'' +
                ", closedReason='" + closedReason + '\'' +
                ", lastEditedBy='" + lastEditedBy + '\'' +
                ", lastEditedDate='" + lastEditedDate + '\'' +
                ", deleted='" + deleted + '\'' +
                ", storyID='" + storyID + '\'' +
                ", storyTitle='" + storyTitle + '\'' +
                ", latestStoryVersion='" + latestStoryVersion + '\'' +
                ", storyStatus='" + storyStatus + '\'' +
                ", assignedToRealName='" + assignedToRealName + '\'' +
                ", needConfirm=" + needConfirm +
                ", progress=" + progress +
                ", storySpec='" + storySpec + '\'' +
                ", storyVerify='" + storyVerify + '\'' +
                ", children=" + Arrays.toString(children) +
                ", team=" + Arrays.toString(team) +
                ", files=" + Arrays.toString(files) +
                ", storyFiles=" + Arrays.toString(storyFiles) +
                ", history=" + Arrays.toString(history) +
                '}';
    }
}
