package com.mitlab.ci.manager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mitlab.ci.manager.SettingEntity;

public class SettingDao extends BaseDao{

	/**
	 * 更新基础配置
	 * @param settingInfo
	 * @return
	 */
	public boolean updateSettingInfo(SettingEntity settingInfo){
		String sql = " update t_setting set "
				+ "zbox_url=?, "
				+ "gitlab_url=? ,"
				+ "zbox_user=? , "
				+ "zbox_password=? , "
				+ "gitlab_token=? "
				+ " where sid=? ";
		
		 Connection conn = null;
         PreparedStatement stmt = null;
         try {
			conn = h2Pool.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, settingInfo.getZboxUrl());
			stmt.setString(2, settingInfo.getGitlabUrl());
			stmt.setString(3, settingInfo.getZboxUser());
			stmt.setString(4, settingInfo.getZboxPassword());
			stmt.setString(5, settingInfo.getGitlabToken());
			stmt.setString(6, settingInfo.getSid());
			int i = stmt.executeUpdate();
			if(i > 0){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
            close(stmt);
            close(conn);
        }
		return false;
	}
	
	
	
	/**
	 *  获取设置信息
	 * @return SettingEntity
	 */
	public SettingEntity getSettingInfo(){
		
		SettingEntity setting = new SettingEntity();
		 String querySettingSql = "select "
										 		+ "zbox_url,"
										 		+ "zbox_user,"
										 		+ "zbox_password,"
										 		+ "gitlab_url,"
										 		+ "gitlab_token "
							 		+ "from "
							 				+ "t_setting where sid = ? ";
		 Connection conn = null;
	     PreparedStatement stmt = null;
	     ResultSet res = null;
		 try {
			conn = h2Pool.getConnection();
			stmt = conn.prepareStatement(querySettingSql);
			stmt.setString(1, setting.getSid());
			res = stmt.executeQuery();
			if(res.next()){
				setting.setZboxUrl(res.getString("zbox_url"));
				setting.setZboxUser(res.getString("zbox_user"));
				setting.setZboxPassword(res.getString("zbox_password"));
				setting.setGitlabUrl(res.getString("gitlab_url"));
				setting.setGitlabToken(res.getString("gitlab_token"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(res);
            close(stmt);
            close(conn);
        }
		return setting;
	}
	
	
}
