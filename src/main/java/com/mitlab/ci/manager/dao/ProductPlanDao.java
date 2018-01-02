package com.mitlab.ci.manager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mitlab.ci.manager.ProductPlanEntity;

public class ProductPlanDao extends BaseDao {

	public boolean addPlanMapping2DB(String planId, String productId, String gitlabProject, String milestoneId,
			String mid) {
		ProductPlanEntity plan = new ProductPlanEntity(planId, productId, gitlabProject, milestoneId, mid);
		String sql = " insert into t_product_plan ( " + "uuid," + "plan_id," + "product_id," + "gitlab_project,"
				+ "milestone_id," + "mid " + " ) values ( ?,?,?,?,?,?) ";
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = h2Pool.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, plan.getUuid());
			stmt.setString(2, plan.getPlanId());
			stmt.setString(3, plan.getProductId());
			stmt.setString(4, plan.getGitlabProject());
			stmt.setString(5, plan.getMilestoneId());
			stmt.setString(6, plan.getMid());
			stmt.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
			close(conn);
		}
		return false;
	}

	/**
	 * 查询记录 可能有多条
	 * 
	 * @param planId
	 * @param productId
	 * @return
	 */
	public boolean findPlanMapping(String planId, String productId) {
		String sql = "select uuid from t_product_plan where  plan_id=? and product_id=?";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet res = null;
		try {
			conn = h2Pool.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, planId);
			stmt.setString(2, productId);
			res = stmt.executeQuery();
			if (res.next())
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
			close(conn);
		}
		return false;
	}

	public ProductPlanEntity findOnePlanMapping(String planId, String productId, String gitlabProject) {
		String sql = "select plan_id,product_id,gitlab_project,milestone_id,mid from t_product_plan where  plan_id=? and product_id=? and gitlab_project=?";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet res = null;
		ProductPlanEntity plan = null;
		try {
			conn = h2Pool.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, planId);
			stmt.setString(2, productId);
			stmt.setString(3, gitlabProject);
			res = stmt.executeQuery();
			if (res.next()) {
				plan = new ProductPlanEntity();
				plan.setPlanId(res.getString("plan_id"));
				plan.setProductId(res.getString("product_id"));
				plan.setGitlabProject(res.getString("gitlab_project"));
				plan.setMilestoneId(res.getString("milestone_id"));
				plan.setMid(res.getString("mid"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(res);
			close(stmt);
			close(conn);
		}
		return plan;
	}

	public List<ProductPlanEntity> findAll() {
		String sql = "select plan_id,product_id,gitlab_project,milestone_id,mid from t_product_plan";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet res = null;
		List<ProductPlanEntity> list = new ArrayList<ProductPlanEntity>();
		try {
			conn = h2Pool.getConnection();
			stmt = conn.prepareStatement(sql);
			res = stmt.executeQuery();
			while (res.next()) {
				ProductPlanEntity plan = new ProductPlanEntity();
				plan.setPlanId(res.getString("plan_id"));
				plan.setProductId(res.getString("product_id"));
				plan.setGitlabProject(res.getString("gitlab_project"));
				plan.setMilestoneId(res.getString("milestone_id"));
				plan.setMid(res.getString("mid"));
				list.add(plan);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(res);
			close(stmt);
			close(conn);
		}
		return list;
	}
}
