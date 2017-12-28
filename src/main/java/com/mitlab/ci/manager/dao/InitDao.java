package com.mitlab.ci.manager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mitlab.ci.zbox.ZboxException;

public class InitDao extends BaseDao{
	
	 public void test(){
		 String dropTable = "drop table t_product_plan";
   	  	 String createIssueTable = "create cached table if not exists t_issue("
     			+ "id varchar(32) primary key,"
     			+ "zid varchar(32), "
     			+ "gid varchar(32), "
     			+ "giid varchar(32),"
     			+ "assign_to varchar(32), "
     			+ "project varchar(128))";
   	//计划-里程碑映射表
     	String  createPlanMappingTable = "create cached table if not exists t_product_plan("
     			+ "uuid varchar(32) primary key, "
     			+ "product_id varchar(32), "
     			+ "gitlab_project varchar(128),"
     			+ "plan_id varchar(32),"
     			+ "milestone_id varchar(32),"
     			+ "mid varchar(32) )";
   	  /*String  createProjectMappingTable = "create cached table if not exists t_project("
  			+ "pid varchar(32) primary key, "
  			+ "zbox_project varchar(128), "
  			+ "gitlab_project varchar(128),"
  			+ "zbox_project_id varchar(32),"
  			+ "plan_sync varchar(2))";*/
	    	 Connection conn = null;
	         PreparedStatement stmt = null; 
	         PreparedStatement stmt2 = null; 
			 try {
				 conn = h2Pool.getConnection();
				 stmt = conn.prepareStatement(dropTable);
				 stmt.execute();
				 stmt2 = conn.prepareStatement(createPlanMappingTable);
				 stmt2.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
	            close(stmt);
	            close(stmt2);
	            close(conn);
	        }
	}
	
      public static void main(String args[]){
    	  new InitDao().test();
      }

	   private void initBaseSetting(){
	    	/**
	    	 * 初始化基础配置表，
	    	 * 默认主键为10000
	    	 * 
	    	 */
		   String initSql = "insert into t_setting ("
	          		+ "sid,"
	          		+ "zbox_url,"
	          		+ "gitlab_url,"
	          		+ "zbox_user,"
	          		+ "zbox_password ,"
	          		+ "gitlab_token"
	          		+ ") "
	          		+ "values "
	          		+ "('10000',"
	          		+ "'',"
	          		+ "'',"
	          		+ "'',"
	          		+ "'',"
	          		+ "'' )";
	    	 
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
	  
	/**
	 * 初始化数据表
	 * @return
	 */
	public boolean initDataTable(){
    	boolean flag = true;
    	//基础配置表
    	String createSettingTable = "create cached table if not exists "
         		+ "t_setting(sid varchar(32) primary key, "
         		+ "zbox_url varchar(64), "
         		+ "gitlab_url varchar(64), "
         		+ "zbox_user varchar(20) , "
         		+ "zbox_password varchar(32),"
         		+ "gitlab_token varchar(32))";
    	//issue映射表
    	String createIssueTable = "create cached table if not exists t_issue("
    			+ "id varchar(32) primary key,"
    			+ "zid varchar(32), "
    			+ "gid varchar(32), "
    			+ "giid varchar(32),"
    			+ "assign_to varchar(32), "
    			+ "project varchar(128))";
    	//action和label映射表
    	String createActionMappingTable = "create cached table if not exists t_action_mapping("
    			+ "aid varchar(32) primary key, "
    			+ "zbox_action varchar(32),"
    			+ "gitlab_label varchar(32), "
    			+ "gitlab_action varchar(32), "
    			+ "project varchar(128)) ";
    	//项目映射表
    	String  createProjectMappingTable = "create cached table if not exists t_project("
    			+ "pid varchar(32) primary key, "
    			+ "zbox_project varchar(128), "
    			+ "gitlab_project varchar(128),"
    			+ "zbox_project_id varchar(32),"
    			+ "plan_sync varchar(2))";
    	//计划-里程碑映射表
    	String  createPlanMappingTable = "create cached table if not exists t_product_plan("
    			+ "uuid varchar(32) primary key, "
    			+ "product_id varchar(32), "
    			+ "gitlab_project varchar(128),"
    			+ "plan_id varchar(32),"
    			+ "milestone_id varchar(32),"
    			+ "mid varchar(32) )";
        //h2Pool = JdbcConnectionPool.create("jdbc:h2:~/mitlab_ci", "sa", "sa");
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = h2Pool.getConnection();
            stmt = conn.createStatement(); 
            stmt.addBatch(createSettingTable);
            stmt.addBatch(createIssueTable);
            stmt.addBatch(createActionMappingTable);
            stmt.addBatch(createProjectMappingTable);
            stmt.addBatch(createPlanMappingTable);
            stmt.executeBatch();
            initBaseSetting();
        } catch (SQLException e) {
        	flag = false;
            throw new ZboxException("init memdb error", e);
        } finally {
            close(stmt);
            close(conn);
        }
        return flag;
    }
	
	
}
