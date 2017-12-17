package com.mitlab.ci.gitlab.issue;

import org.codehaus.jackson.annotate.JsonProperty;

public class IssueTimeStats {
    @JsonProperty("time_estimate")
    private Long timeEstimate;
    @JsonProperty("total_time_estimate")
    private Long totalTimeEstimate;
    @JsonProperty("human_time_estimate")
    private Long humanTimeEstimate;
    @JsonProperty("humanTotal_time_spent")
    private Long humanTotalTimeSpent;

    public Long getTimeEstimate() {
        return timeEstimate;
    }

    public void setTimeEstimate(Long timeEstimate) {
        this.timeEstimate = timeEstimate;
    }

    public Long getTotalTimeEstimate() {
        return totalTimeEstimate;
    }

    public void setTotalTimeEstimate(Long totalTimeEstimate) {
        this.totalTimeEstimate = totalTimeEstimate;
    }

    public Long getHumanTimeEstimate() {
        return humanTimeEstimate;
    }

    public void setHumanTimeEstimate(Long humanTimeEstimate) {
        this.humanTimeEstimate = humanTimeEstimate;
    }

    public Long getHumanTotalTimeSpent() {
        return humanTotalTimeSpent;
    }

    public void setHumanTotalTimeSpent(Long humanTotalTimeSpent) {
        this.humanTotalTimeSpent = humanTotalTimeSpent;
    }

    @Override
    public String toString() {
        return "IssueTimeStats{" +
                "timeEstimate=" + timeEstimate +
                ", totalTimeEstimate=" + totalTimeEstimate +
                ", humanTimeEstimate=" + humanTimeEstimate +
                ", humanTotalTimeSpent=" + humanTotalTimeSpent +
                '}';
    }
}
