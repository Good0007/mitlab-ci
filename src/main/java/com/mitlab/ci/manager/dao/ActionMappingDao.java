package com.mitlab.ci.manager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.mitlab.ci.manager.ActionMappingEntity;

public class ActionMappingDao extends BaseDao{

	private final Logger logger = Logger.getLogger(this.getClass().getName());
	
	
	public ActionMappingEntity getActionByZboxAction(String zboxAction , String project){
		String sql = "select "
							+ "aid,"
							+ "zbox_action,"
							+ "gitlab_action,"
							+ "gitlab_label,"
							+ "project "
					+ "from "
							+ "t_action_mapping "
					+ "where "
							+ "zbox_action=? and project=?";
		Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet res = null;
        ActionMappingEntity mapping = null;
		try {
			conn = h2Pool.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, zboxAction);
			stmt.setString(2, project);
			res = stmt.executeQuery();
			if(res.next()){
				mapping = new ActionMappingEntity();
				mapping.setAid(res.getString("aid"));
				mapping.setZboxAction(res.getString("zbox_action"));
				mapping.setGitlabAction(res.getString("gitlab_action"));
				mapping.setGitlabLabel(res.getString("gitlab_label"));
				mapping.setProject(res.getString("project"));
				logger.info("getActionByZboxAction : "+mapping.toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(res);
            close(stmt);
            close(conn);
        }
		
		return mapping;
	}
	
	public boolean removeByAid(String aid){
		String sql = " delete from t_action_mapping where aid=? ";
		Connection conn = null;
        PreparedStatement stmt = null;
        try {
        	conn = h2Pool.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, aid);
			stmt.execute();
			return true;
        } catch (SQLException e) {
			e.printStackTrace();
		}finally {
            close(stmt);
            close(conn);
        }
        return false;
	}
	public boolean removeByProject(String project){
		String sql = " delete from t_action_mapping where project=? ";
		Connection conn = null;
        PreparedStatement stmt = null;
        try {
        	conn = h2Pool.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, project);
			stmt.execute();
			return true;
        } catch (SQLException e) {
			e.printStackTrace();
		}finally {
            close(stmt);
            close(conn);
        }
        return false;
	}
	
	/**
	 * 新增映射 
	 * @param mapping
	 * @return boolean
	 */
	public boolean addActionMapping(ActionMappingEntity mapping){
		String sql = " insert into t_action_mapping set aid=?,zbox_action=?,gitlab_action=?,gitlab_label=?,project=? ";
		Connection conn = null;
        PreparedStatement stmt = null;
		try {
			conn = h2Pool.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, mapping.getAid());
			stmt.setString(2, mapping.getZboxAction());
			stmt.setString(3, mapping.getGitlabAction());
			stmt.setString(4, mapping.getGitlabLabel());
			stmt.setString(5, mapping.getProject());
			stmt.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
            close(stmt);
            close(conn);
        }
		return false;
	} 
	
	/**
	 *  查询 MappingList
	 * @return List<ActionMappingEntity>
	 */
	public List<ActionMappingEntity> getMappingListByProject(String project){
		String sql = " select "
								+ "aid,"
								+ "zbox_action,"
								+ "gitlab_action,"
								+ "gitlab_label,"
								+ "project "
							+ " from "
								+ "t_action_mapping ";
		if(project != null){
			sql += " where project=? ";
		}
		Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet res = null;
        List<ActionMappingEntity> list = new ArrayList<ActionMappingEntity>();
		try {
			conn = h2Pool.getConnection();
			stmt = conn.prepareStatement(sql);
			if(project != null){
				stmt.setString(1, project);
			}
			res = stmt.executeQuery();
			while(res.next()){
				ActionMappingEntity mapping = new ActionMappingEntity();
				mapping.setAid(res.getString("aid"));
				mapping.setZboxAction(res.getString("zbox_action"));
				mapping.setGitlabAction(res.getString("gitlab_action"));
				mapping.setProject(res.getString("project"));
				mapping.setGitlabLabel(res.getString("gitlab_label"));
				list.add(mapping);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(res);
            close(stmt);
            close(conn);
        }
		logger.info(list.toString());
		return list;
	}
	
}
