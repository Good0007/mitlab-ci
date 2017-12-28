package com.mitlab.ci.zbox;

import org.codehaus.jackson.annotate.JsonProperty;

public class ZboxNotify {
    private String objectType;
    @JsonProperty("objectID")
    private String ObjectId;
    private String product;
    private String project;
    private String action;
    private String actor;
    private String date;
    private String comment;
    private String text;

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public String getObjectId() {
        return ObjectId;
    }

    public void setObjectId(String objectId) {
        ObjectId = objectId;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    

	@Override
    public String toString() {
        return "ZboxNotify{" +
                "objectType='" + objectType + '\'' +
                ", ObjectId='" + ObjectId + '\'' +
                ", product=" + product +
                ", project='" + project + '\'' +
                ", action='" + action + '\'' +
                ", actor='" + actor + '\'' +
                ", date='" + date + '\'' +
                ", comment='" + comment + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
