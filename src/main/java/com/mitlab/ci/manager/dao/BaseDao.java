package com.mitlab.ci.manager.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.h2.jdbcx.JdbcConnectionPool;

import com.mitlab.ci.zbox.ZboxException;

public class BaseDao {

	public  JdbcConnectionPool h2Pool;
	
	public BaseDao() {
		h2Pool = JdbcConnectionPool.create("jdbc:h2:~/mitlab_ci", "sa", "sa");
	}

    public  void close(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                throw new ZboxException(e.getMessage(), e);
            }
        }
    }

    public  void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new ZboxException(e.getMessage(), e);
            }
        }
    }

    public  void close(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new ZboxException(e.getMessage(), e);
            }
        }
    }
    
    public  void closeConn(){
    	if (h2Pool != null) {
            h2Pool.dispose();
            h2Pool = null;
        }
    }
}
