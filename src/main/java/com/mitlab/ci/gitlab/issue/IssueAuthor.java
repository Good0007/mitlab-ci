package com.mitlab.ci.gitlab.issue;

import org.codehaus.jackson.annotate.JsonProperty;

public class IssueAuthor {
    private String name;
    @JsonProperty("avatar_url")
    private String avatarUrl;
    private String state;
    @JsonProperty("web_url")
    private String webUrl;
    private Long id;
    private String username;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "IssueAuthor{" +
                "name='" + name + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", state='" + state + '\'' +
                ", webUrl='" + webUrl + '\'' +
                ", id=" + id +
                ", username='" + username + '\'' +
                '}';
    }
}
