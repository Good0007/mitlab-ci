package com.mitlab.ci.zbox.task;

import com.mitlab.ci.zbox.ZboxProject;

public class ZboxTask {
    private String title;
    private ZboxProject project;
    private ZboxTaskDetails task;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ZboxProject getProject() {
        return project;
    }

    public void setProject(ZboxProject project) {
        this.project = project;
    }

    public ZboxTaskDetails getTask() {
        return task;
    }

    public void setTask(ZboxTaskDetails task) {
        this.task = task;
    }

    @Override
    public String toString() {
        return "ZboxTask{" +
                "title='" + title + '\'' +
                ", project=" + project +
                ", task=" + task +
                '}';
    }
}
