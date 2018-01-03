package com.mitlab.ci.gitlab.project;

import org.codehaus.jackson.annotate.JsonProperty;

public class ProjectResponse {

	private String id;
	private String description;
	private String name;

	@JsonProperty("name_with_namespace")
	private String nameWithNamespace;

	private String path;

	@JsonProperty("path_with_namespace")
	private String pathWithNamespace;

	@JsonProperty("created_at")
	private String createdAt;

	@JsonProperty("default_branch")
	private String defaultBranch;

	@JsonProperty("ssh_url_to_repo")
	private String sshUrlToRepo;

	@JsonProperty("http_url_to_repo")
	private String httpUrlToRepo;

	@JsonProperty("web_url")
	private String webUrl;

	public String getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public String getName() {
		return name;
	}

	public String getNameWithNamespace() {
		return nameWithNamespace;
	}

	public String getPath() {
		return path;
	}

	public String getPathWithNamespace() {
		return pathWithNamespace;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public String getDefaultBranch() {
		return defaultBranch;
	}

	public String getSshUrlToRepo() {
		return sshUrlToRepo;
	}

	public String getHttpUrlToRepo() {
		return httpUrlToRepo;
	}

	public String getWebUrl() {
		return webUrl;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNameWithNamespace(String nameWithNamespace) {
		this.nameWithNamespace = nameWithNamespace;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setPathWithNamespace(String pathWithNamespace) {
		this.pathWithNamespace = pathWithNamespace;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public void setDefaultBranch(String defaultBranch) {
		this.defaultBranch = defaultBranch;
	}

	public void setSshUrlToRepo(String sshUrlToRepo) {
		this.sshUrlToRepo = sshUrlToRepo;
	}

	public void setHttpUrlToRepo(String httpUrlToRepo) {
		this.httpUrlToRepo = httpUrlToRepo;
	}

	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
	}

	@Override
	public String toString() {
		return "ProjectResoponse [id=" + id + ", description=" + description + ", name=" + name + ", nameWithNamespace="
				+ nameWithNamespace + ", path=" + path + ", pathWithNamespace=" + pathWithNamespace + ", createdAt="
				+ createdAt + ", defaultBranch=" + defaultBranch + ", sshUrlToRepo=" + sshUrlToRepo + ", httpUrlToRepo="
				+ httpUrlToRepo + ", webUrl=" + webUrl + "]";
	}
	

}
