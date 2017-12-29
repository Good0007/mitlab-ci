package com.mitlab.ci.manager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mitlab.ci.manager.ProjectEntity;

public class ProjectDao extends BaseDao{
	
	public boolean updatePlanSync(String planSync , String pid){
		String sql = " update t_project set "
				+ "plan_sync=? "
				+ " where pid=? ";
		 Connection conn = null;
         PreparedStatement stmt = null;
         try {
			conn = h2Pool.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, planSync);
			stmt.setString(2, pid);
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
	 * 通过 planSync 条件查询
	 * @param planSync
	 * @return
	 */
	public List<ProjectEntity> getProjectsByPlanSync(String planSync){
		String sql = "select "
					+ "pid,"
					+ "zbox_project,"
					+ "gitlab_project,"
					+ "zbox_project_id,"
					+ "plan_sync "
				+ "from "
					+ " t_project "
				+ "where plan_sync=?";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet res = null;
		List<ProjectEntity> list = new ArrayList<ProjectEntity>();
		try {
		conn = h2Pool.getConnection();
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, planSync);
		res = stmt.executeQuery();
		while(res.next()){
			ProjectEntity obj = new ProjectEntity();
			obj.setPid(res.getString("pid"));
			obj.setZboxProject(res.getString("zbox_project"));
			obj.setGitlabProject(res.getString("gitlab_project"));
			obj.setZboxProjectId(res.getString("zbox_project_id"));
			obj.setPlanSync(res.getString("plan_sync"));
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
		String sql = " insert into t_project ( "
							+ "pid,"
							+ "zbox_project,"
							+ "gitlab_project,"
							+ "zbox_project_id,"
							+ "plan_sync ) values ( ?,?,?,?,?) ";
		 Connection conn = null;
         PreparedStatement stmt = null;
         try {
			conn = h2Pool.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, project.getPid());
			stmt.setString(2, project.getZboxProject());
			stmt.setString(3, project.getGitlabProject());
			stmt.setString(4,project.getZboxProjectId());
			stmt.setString(5,project.getPlanSync());
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
	public boolean deleteByProjectId(String projectId){
		String sql = "delete from t_project where zbox_project_id=?";
		Connection conn = null;
        PreparedStatement stmt = null;
        try {
			conn = h2Pool.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, projectId);
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
		String sql = "select "
							+ "pid,"
							+ "zbox_project,"
							+ "gitlab_project,"
							+ "zbox_project_id,"
							+ "plan_sync "
							+ "from t_project";
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
				obj.setZboxProjectId(res.getString("zbox_project_id"));
				obj.setPlanSync(res.getString("plan_sync").equals("0")?"否":"是");
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
	public ProjectEntity getProjectByProjectId(String projectId){
		String sql = "select "
						+ "pid,"
						+ "zbox_project,"
						+ "zbox_project_id,"
						+ "gitlab_project,"
						+ "plan_sync "
				+ "from "
						+ "t_project "
				+ "where zbox_project_id=?";
		Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet res = null;
        ProjectEntity obj = new ProjectEntity();
        try {
			conn = h2Pool.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, projectId);
			res = stmt.executeQuery();
			if(res.next()){
				obj.setPid(res.getString("pid"));
				obj.setZboxProjectId(res.getString("zbox_project_id"));
				obj.setZboxProject(res.getString("zbox_project"));
				obj.setGitlabProject(res.getString("gitlab_project"));
				obj.setPlanSync(res.getString("plan_sync"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
           close(stmt);
           close(conn);
       }
        return obj;
	}
	
	
	public ProjectEntity getProjectByProjectName(String ProjectName){
		String sql = "select "
						+ "pid,"
						+ "zbox_project,"
						+ "zbox_project_id,"
						+ "gitlab_project,"
						+ "plan_sync "
				+ "from "
						+ "t_project "
				+ "where zbox_project=?";
		Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet res = null;
        ProjectEntity obj = new ProjectEntity();
        try {
			conn = h2Pool.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, ProjectName);
			res = stmt.executeQuery();
			if(res.next()){
				obj.setPid(res.getString("pid"));
				obj.setZboxProjectId(res.getString("zbox_project_id"));
				obj.setZboxProject(res.getString("zbox_project"));
				obj.setGitlabProject(res.getString("gitlab_project"));
				obj.setPlanSync(res.getString("plan_sync"));
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
