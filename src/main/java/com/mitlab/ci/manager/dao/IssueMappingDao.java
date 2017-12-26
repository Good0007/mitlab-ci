package com.mitlab.ci.manager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mitlab.ci.gitlab.issue.IssueRequest;
import com.mitlab.ci.gitlab.issue.IssueResponse;
import com.mitlab.ci.manager.IssueMappingEntity;
import com.mitlab.ci.zbox.ZboxException;

public class IssueMappingDao extends BaseDao{

	private final Logger logger = Logger.getLogger(this.getClass().getName());
	
	/**
	 * 查询全部
	 * @return
	 */
	public List<IssueMappingEntity> findAllIssueMapping(){
		Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<IssueMappingEntity> list = new ArrayList<IssueMappingEntity>();
        try {
            conn = h2Pool.getConnection();
            stmt = conn.prepareStatement("select id,zid,gid,giid, project from t_issue");
            rs = stmt.executeQuery();
            while (rs.next()) {
            	IssueMappingEntity issue = new IssueMappingEntity();
            	issue.setId(rs.getString("id"));
            	issue.setZid(rs.getString("zid"));
            	issue.setGid(rs.getString("gid"));
            	issue.setGiid(rs.getLong("giid"));
            	issue.setProject(rs.getString("project"));
            	list.add(issue);
            }
        } catch (SQLException e) {
            throw new ZboxException("query issue mapping error", e);
        } finally {
            close(rs);
            close(stmt);
            close(conn);
        }
        return list;
	}
	
	/**
	 * 通过zid和项目查询
	 * @param cacheId
	 * @param project
	 * @return
	 */
	public IssueMappingEntity findIssueMapping(String cacheId ,String project){
		Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        IssueMappingEntity issue = null;
        try {
            conn = h2Pool.getConnection();
            stmt = conn.prepareStatement("select zid,gid, giid, project from t_issue where zid = ? and project=?");
            stmt.setString(1, cacheId);
            stmt.setString(2, project);
            rs = stmt.executeQuery();
            if (rs.next()) {
            	issue = new IssueMappingEntity();
            	issue.setZid(rs.getString("zid"));
            	issue.setGid(rs.getString("gid"));
            	issue.setGiid(rs.getLong("giid"));
            	issue.setProject(rs.getString("project"));
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
	
	/**
	 * 记录gitlab issue映射关系
	 * @param issueRequest
	 * @param cacheId
	 * @param project
	 * @param issueResponse
	 */
	public void setIssueMapping2DB(IssueRequest issueRequest, String cacheId, String project, IssueResponse issueResponse) {
        Connection conn = null;
        PreparedStatement stmt = null;
        IssueMappingEntity issue = new IssueMappingEntity();
        issue.setZid(cacheId);
        issue.setGid(issueResponse.getId());
        issue.setGiid(issueResponse.getIid());
        issue.setProject(project);
        try {
            conn = h2Pool.getConnection();
            stmt = conn.prepareStatement("insert into t_issue(id, zid, gid , giid, project) values(?,?, ?, ?, ?)");
            stmt.setString(1, issue.getId());
            stmt.setString(2, issue.getZid());
            stmt.setString(3, issue.getGid());
            stmt.setString(4, Long.toString(issue.getGiid()));
            //stmt.setString(5, Long.toString(issueResponse.getProjectId()));
            stmt.setString(5, issue.getProject());
            stmt.executeUpdate();
            if (logger.isLoggable(Level.INFO)) {
                logger.info("setIssueMapping2DB : "+issue.toString());
            }
        } catch (SQLException e) {
            throw new ZboxException("save issue mapping error", e);
        } finally {
            close(stmt);
            close(conn);
        }
    }
	
}
