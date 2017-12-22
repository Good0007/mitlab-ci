package com.mitlab.ci.manager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mitlab.ci.gitlab.issue.IssueRequest;
import com.mitlab.ci.gitlab.issue.IssueResponse;
import com.mitlab.ci.manager.IssueMappingEntity;
import com.mitlab.ci.zbox.ZboxException;

public class IssueMappingDao extends BaseDao{

	private final Logger logger = Logger.getLogger(this.getClass().getName());
	
	public IssueMappingEntity findIssueMapping(String cacheId){
		Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        IssueMappingEntity issue = new IssueMappingEntity();
        try {
            conn = h2Pool.getConnection();
            stmt = conn.prepareStatement("select gid, giid, project from t_issue where zid = ?");
            stmt.setString(1, cacheId);
            rs = stmt.executeQuery();
            if (rs.next()) {
            	issue.setGid(rs.getString(1));
            	issue.setGiid(rs.getLong(2));
            	issue.setProject(rs.getString(3));
            }
        } catch (SQLException e) {
            throw new ZboxException("query issue mapping error", e);
        } finally {
            close(rs);
            close(stmt);
            close(conn);
        }
        return issue;
	}
	
	public void setIssueMapping2DB(IssueRequest issueRequest, String cacheId, String project, IssueResponse issueResponse) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = h2Pool.getConnection();
            stmt = conn.prepareStatement("insert into t_issue(zid, gid , giid, project) values(?, ?, ?, ?)");
            stmt.setString(1, cacheId);
            stmt.setString(2, issueResponse.getId());
            stmt.setString(3, Long.toString(issueResponse.getIid()));
            stmt.setString(4, Long.toString(issueResponse.getProjectId()));
            stmt.executeUpdate();
            if (logger.isLoggable(Level.INFO)) {
                logger.info("save db:{issueId:" + issueRequest.getId() + ", issueIid:" + issueRequest.getIssueIid() + ", project:" + project + ",zid:" + cacheId + "}");
            }
        } catch (SQLException e) {
            throw new ZboxException("save issue mapping error", e);
        } finally {
            close(stmt);
            close(conn);
        }
    }
	
}
