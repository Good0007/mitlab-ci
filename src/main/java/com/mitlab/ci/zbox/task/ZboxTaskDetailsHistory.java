package com.mitlab.ci.zbox.task;

import org.codehaus.jackson.annotate.JsonProperty;

public class ZboxTaskDetailsHistory {
    private String id;
    private String action;
    private String field;
    private String canceledBy;
    private String old;
    @JsonProperty("new")
    private String newer;
    private String diff;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getCanceledBy() {
        return canceledBy;
    }

    public void setCanceledBy(String canceledBy) {
        this.canceledBy = canceledBy;
    }

    public String getOld() {
        return old;
    }

    public void setOld(String old) {
        this.old = old;
    }

    public String getNewer() {
        return newer;
    }

    public void setNewer(String newer) {
        this.newer = newer;
    }

    public String getDiff() {
        return diff;
    }

    public void setDiff(String diff) {
        this.diff = diff;
    }

    @Override
    public String toString() {
        return "ZboxTaskDetailsHistory{" +
                "id='" + id + '\'' +
                ", action='" + action + '\'' +
                ", field='" + field + '\'' +
                ", canceledBy='" + canceledBy + '\'' +
                ", old='" + old + '\'' +
                ", newer='" + newer + '\'' +
                ", diff='" + diff + '\'' +
                '}';
    }
}
