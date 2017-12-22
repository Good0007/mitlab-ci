package com.mitlab.ci.gitlab;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

import com.mitlab.ci.AbstractMitlabUtil;
import com.mitlab.ci.gitlab.issue.IssueRequest;
import com.mitlab.ci.gitlab.issue.IssueResponse;
import com.mitlab.ci.gitlab.user.GitlabUser;
import com.mitlab.ci.zbox.ZboxException;

public final class GitlabUtil extends AbstractMitlabUtil {
    private static final GitlabUtil GITLAB_UTIL = new GitlabUtil("http://192.168.60.50:27080/gitlab");

    protected GitlabUtil(String accessUrl) {
        super(accessUrl);
    }

    public static final GitlabUtil getInstance() {
        return GITLAB_UTIL;
    }

    public IssueResponse createIssue(String project, IssueRequest issue, String accessToken) {
        ObjectMapper om = GitlabUtil.newObjectMapper();
        Map<String, Object> bodyParams = new HashMap<String, Object>();
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        try {
            om.writeValue(buffer, issue);
            buffer.flush();
            bodyParams = om.readValue(buffer.toByteArray(), HashMap.class);
            buffer.close();
        } catch (IOException e) {
            throw new ZboxException("create or update issue error", e);
        }

        String encodedProject = project;
        try {
            encodedProject = URLEncoder.encode(project, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new ZboxException("create or update issue error", e);
        }
        Map<String, Object> headers = new HashMap<String, Object>();
        headers.put("PRIVATE-TOKEN", accessToken);
        headers.put("Content-Type", "application/json");
        StringBuilder relativePath = new StringBuilder("/api/v4/projects/").append(encodedProject).append("/issues");
        if (issue.getIssueIid() != null) {
            relativePath.append("/").append(issue.getIssueIid());
            headers.put("Method", "PUT");
        }
        return this.proxyPost(bodyParams, null, headers, IssueResponse.class, relativePath.toString());
    }

    public void deleteIssue(String project, String issueIid, String accessToken) {
        String encodedProject = project;
        try {
            encodedProject = URLEncoder.encode(project, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new ZboxException("create or update issue error", e);
        }
        Map<String, Object> headers = new HashMap<String, Object>();
        headers.put("PRIVATE-TOKEN", accessToken);
        headers.put("Content-Type", "application/json");
        StringBuilder relativePath = new StringBuilder("/api/v4/projects/").append(encodedProject).append("/issues");
        headers.put("Method", "DELETE");
        relativePath.append("/").append(issueIid);
        this.proxyPost(null, null, headers, Void.class, relativePath.toString());
    }

    public GitlabUser getUserDetails(String username, String accessToken) {
        Map<String, Object> urlParams = new HashMap<String, Object>();
        urlParams.put("username", username);
        Map<String, Object> headers = new HashMap<String, Object>();
        headers.put("PRIVATE-TOKEN", accessToken);
        headers.put("Content-Type", "application/json");
        headers.put("Method", "GET");
        GitlabUser[] users = this.proxyPost(null,urlParams, headers, GitlabUser[].class, "/api/v4/users");
        if (users != null && users.length > 0) {
            return users[0];
        }
        return null;
    }

}
