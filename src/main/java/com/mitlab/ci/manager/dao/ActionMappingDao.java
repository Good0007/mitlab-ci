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
	
	public String geGitlabActionByZboxAction(String zboxAction){
		String sql = "select gitlab_action from t_action_mapping where zbox_action=?";
		Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet res = null;
        String gitAction = null;
		try {
			conn = h2Pool.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, zboxAction);
			res = stmt.executeQuery();
			if(res.next()){
				gitAction = res.getString("gitlab_action");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(res);
            close(stmt);
            close(conn);
        }
		logger.info("gitlab_action : "+gitAction);
		return gitAction;
	}
	
	public boolean removeActionMapping(String aid){
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
	/**
	 * 新增映射 
	 * @param mapping
	 * @return boolean
	 */
	public boolean addActionMapping(ActionMappingEntity mapping){
		String sql = " insert into t_action_mapping set aid=?,zbox_action=?,gitlab_action=? ";
		Connection conn = null;
        PreparedStatement stmt = null;
		try {
			conn = h2Pool.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, mapping.getAid());
			stmt.setString(2, mapping.getZboxAction());
			stmt.setString(3, mapping.getGitlabAction());
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
	public List<ActionMappingEntity> getMappingList(){
		String sql = " select * from t_action_mapping ";
		Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet res = null;
        List<ActionMappingEntity> list = new ArrayList<ActionMappingEntity>();
		try {
			conn = h2Pool.getConnection();
			stmt = conn.prepareStatement(sql);
			res = stmt.executeQuery();
			while(res.next()){
				ActionMappingEntity mapping = new ActionMappingEntity();
				mapping.setAid(res.getString("aid"));
				mapping.setZboxAction(res.getString("zbox_action"));
				mapping.setGitlabAction(res.getString("gitlab_action"));
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
