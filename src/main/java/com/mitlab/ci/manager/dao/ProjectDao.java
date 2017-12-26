package com.mitlab.ci.manager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mitlab.ci.manager.ProjectEntity;

public class ProjectDao extends BaseDao{

	/**
	 * 通过禅道项目查询对应的gitlab项目
	 * @param zboxProject
	 * @return
	 */
	public String findGitlabProjectByZboxProject(String zboxProject){
		String sql = " select gitlab_project from t_project  where zbox_project=? ";
		Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet res = null;
        String gitlabProject = null;
        try {
			conn = h2Pool.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, zboxProject);
			res = stmt.executeQuery();
			if(res.next()){
				gitlabProject =  res.getString("gitlab_project");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
           close(stmt);
           close(conn);
       }
        return gitlabProject;
	}
	
	/**
	 *  更新记录
	 * @param project
	 * @return
	 */
	public boolean updateProject(ProjectEntity project){
		String sql = " update t_project set "
				+ "zbox_project=?, "
				+ "gitlab_project=?"
				+ " where pid=? ";
		
		 Connection conn = null;
         PreparedStatement stmt = null;
         try {
			conn = h2Pool.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, project.getZboxProject());
			stmt.setString(2, project.getGitlabProject());
			stmt.setString(3, project.getPid());
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
	 * 添加纪录
	 * @param project
	 * @return
	 */
	public boolean addProject(ProjectEntity project){
		String sql = " insert into t_project (pid,zbox_project,gitlab_project) values ( ?,?,?) ";
		 Connection conn = null;
         PreparedStatement stmt = null;
         try {
			conn = h2Pool.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, project.getPid());
			stmt.setString(2, project.getZboxProject());
			stmt.setString(3, project.getGitlabProject());
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
	 * 删除记录
	 * @param pid
	 * @return
	 */
	public boolean deleteProject(String pid){
		String sql = "delete from t_project where pid=?";
		Connection conn = null;
        PreparedStatement stmt = null;
        try {
			conn = h2Pool.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, pid);
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
	 * 查询全部记录
	 * @return
	 */
	public List<ProjectEntity> getAllProjects(){
		String sql = "select pid,zbox_project,gitlab_project from t_project";
		Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet res = null;
        List<ProjectEntity> list = new ArrayList<ProjectEntity>();
        try {
			conn = h2Pool.getConnection();
			stmt = conn.prepareStatement(sql);
			res = stmt.executeQuery();
			while(res.next()){
				ProjectEntity obj = new ProjectEntity();
				obj.setPid(res.getString("pid"));
				obj.setZboxProject(res.getString("zbox_project"));
				obj.setGitlabProject(res.getString("gitlab_project"));
				list.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
           close(stmt);
           close(conn);
       }
        return list;
	}
	
	/**
	 * 通过主键查询
	 * @param pid
	 * @return
	 */
	public ProjectEntity getProjectByPid(String pid){
		String sql = "select pid,zbox_project,gitlab_project from t_project where pid=?";
		Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet res = null;
        ProjectEntity obj = new ProjectEntity();
        try {
			conn = h2Pool.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, pid);
			res = stmt.executeQuery();
			if(res.next()){
				obj.setPid(res.getString("pid"));
				obj.setZboxProject(res.getString("zbox_project"));
				obj.setGitlabProject(res.getString("gitlab_project"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
           close(stmt);
           close(conn);
       }
        return obj;
	}
	
}
