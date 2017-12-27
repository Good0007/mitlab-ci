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
            stmt = conn.prepareStatement("select id,zid,gid,giid, project ,assign_to from t_issue");
            rs = stmt.executeQuery();
            while (rs.next()) {
            	IssueMappingEntity issue = new IssueMappingEntity();
            	issue.setId(rs.getString("id"));
            	issue.setZid(rs.getString("zid"));
            	issue.setGid(rs.getString("gid"));
            	issue.setGiid(rs.getLong("giid"));
            	issue.setProject(rs.getString("project"));
            	issue.setAssignTo(rs.getString("assign_to"));
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
	
	/*
	 * 更新指派信息
	 */
	public void updateAssignTo(String zid ,String project, String assignTo){
		String sql = "update t_issue set assign_to=? where zid = ? and project=?";
		Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = h2Pool.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, assignTo);
            stmt.setString(2, zid);
            stmt.setString(3, project);
            stmt.execute();
        } catch (SQLException e) {
            throw new ZboxException("update issue mapping error", e);
        } finally {
            close(stmt);
            close(conn);
        }   
	}
	
	
	/**
	 * 通过zid和项目查询
	 * @param cacheId
	 * @param project
	 * @return
	 */
	public IssueMappingEntity findIssueMapping(String zid ,String project){
		Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        IssueMappingEntity issue = null;
        try {
            conn = h2Pool.getConnection();
            stmt = conn.prepareStatement("select zid,gid, giid, project,assign_to from t_issue where zid = ? and project=?");
            stmt.setString(1, zid);
            stmt.setString(2, project);
            rs = stmt.executeQuery();
            if (rs.next()) {
            	issue = new IssueMappingEntity();
            	issue.setZid(rs.getString("zid"));
            	issue.setGid(rs.getString("gid"));
            	issue.setGiid(rs.getLong("giid"));
            	issue.setProject(rs.getString("project"));
            	issue.setAssignTo(rs.getString("assign_to"));
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
	public void setIssueMapping2DB(IssueRequest issueRequest, String zid, String project,String assignTo, IssueResponse issueResponse) {
        Connection conn = null;
        PreparedStatement stmt = null;
        IssueMappingEntity issue = new IssueMappingEntity();
        issue.setZid(zid);
        issue.setGid(issueResponse.getId());
        issue.setGiid(issueResponse.getIid());
        issue.setProject(project);
        issue.setAssignTo(assignTo);
        try {
            conn = h2Pool.getConnection();
            stmt = conn.prepareStatement("insert into t_issue(id, zid, gid , giid, project ,assign_to) values(?,?, ?, ?,?,?)");
            stmt.setString(1, issue.getId());
            stmt.setString(2, issue.getZid());
            stmt.setString(3, issue.getGid());
            stmt.setString(4, Long.toString(issue.getGiid()));
            stmt.setString(5, issue.getProject());
            stmt.setString(6, issue.getAssignTo());
            stmt.execute();
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
