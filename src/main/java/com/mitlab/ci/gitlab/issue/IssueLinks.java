package com.mitlab.ci.gitlab.issue;

import org.codehaus.jackson.annotate.JsonProperty;

public class IssueLinks {
    private String self;
    private String notes;
    @JsonProperty("award_emoji")
    private String awardEmoji;
    private String project;

    public String getSelf() {
        return self;
    }

    public void setSelf(String self) {
        this.self = self;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getAwardEmoji() {
        return awardEmoji;
    }

    public void setAwardEmoji(String awardEmoji) {
        this.awardEmoji = awardEmoji;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    @Override
    public String toString() {
        return "IssueLinks{" +
                "self='" + self + '\'' +
                ", notes='" + notes + '\'' +
                ", awardEmoji='" + awardEmoji + '\'' +
                ", project='" + project + '\'' +
                '}';
    }
}
