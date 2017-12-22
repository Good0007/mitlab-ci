package com.mitlab.ci.manager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mitlab.ci.zbox.ZboxException;

public class InitDao extends BaseDao{

	   private void initBaseSetting(){
	    	 String initSql = "insert into t_setting ("
	          		+ "sid,"
	          		+ "zbox_url,"
	          		+ "gitlab_url,"
	          		+ "zbox_user,"
	          		+ "zbox_password ,"
	          		+ "gitlab_token,"
	          		+ "gitlab_project,"
	          		+ "zbox_project) "
	          		+ "values "
	          		+ "('10000',"
	          		+ "'http://192.168.60.50:26080/zentao',"
	          		+ "'http://192.168.60.50:27080/gitlab',"
	          		+ "'admin',"
	          		+ "'Passw0rd',"
	          		+ "'ctruqkRFonzDbNuBzYVc',"
	          		+ " '',"
	          		+ " '' )";
	    	 
	    	 String sql = "select sid from t_setting where sid = 10000"; 
	    	 Connection conn = null;
	         PreparedStatement stmt = null; 
	         PreparedStatement stmt2 = null; 
	         ResultSet res = null;
			 try {
				 conn = h2Pool.getConnection();
				 stmt = conn.prepareStatement(sql);
				 res = stmt.executeQuery();
				 if(!res.next()){
					 stmt2 = conn.prepareStatement(initSql);
					 stmt2.execute();
				 }
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
	            close(stmt);
	            close(stmt2);
	            close(conn);
	        }
	    }
	   
	public boolean initDataTable(){
    	boolean flag = true;
    	//初始化数据表
    	String createSettingTable = "create cached table if not exists "
         		+ "t_setting(sid varchar(32) primary key, "
         		+ "zbox_url varchar(64), "
         		+ "gitlab_url varchar(64), "
         		+ "zbox_user varchar(20) , "
         		+ "zbox_password varchar(32),"
         		+ "gitlab_token varchar(32),"
         		+ "gitlab_project varchar(64),"
         		+ "zbox_project varchar(64))";
    	String createIssueTable = "create cached table if not exists t_issue("
    			+ "zid varchar(32) primary key, "
    			+ "gid varchar(32), "
    			+ "giid varchar(32), "
    			+ "project varchar(128))";
    	String createActionMappingTable = "create cached table if not exists t_action_mapping("
    			+ "aid varchar(32) primary key, "
    			+ "zbox_action varchar(32), "
    			+ "gitlab_action varchar(32))";
    	
        //h2Pool = JdbcConnectionPool.create("jdbc:h2:~/mitlab_ci", "sa", "sa");
        Connection conn = null;
        PreparedStatement stmt1 = null;
        PreparedStatement stmt2 = null;
        PreparedStatement stmt3 = null;
        try {
            conn = h2Pool.getConnection();
            stmt1 = conn.prepareStatement(createSettingTable);
            stmt1.execute();
            stmt2 = conn.prepareStatement(createIssueTable);
            stmt2.execute();
            stmt3 = conn.prepareStatement(createActionMappingTable);
            stmt3.execute();
            initBaseSetting();
        } catch (SQLException e) {
        	flag = false;
            throw new ZboxException("init memdb error", e);
        } finally {
            close(stmt1);
            close(stmt2);
            close(stmt3);
            close(conn);
        }
        return flag;
    }
	
	
}
