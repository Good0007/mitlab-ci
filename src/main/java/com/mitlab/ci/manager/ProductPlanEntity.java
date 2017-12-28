package com.mitlab.ci.manager;

/**
 * 产品计划
 * @author 维康
 *
 */
public class ProductPlanEntity {

	/**
	 * 主键
	 */
	private String uuid = BaseUtil.getUuid();
	/**
	 * 禅道计划id
	 */
	private String planId;
	/**
	 * 计划归属的产品Id
	 */
	private String productId;
	/**
	 * 对应的gitLab项目
	 */
	private String gitlabProject;
	/**
	 * 更新时需要
	 */
	private String milestoneId;
	
	/**
	 * 更新时需要
	 */
	private String mid;
	
	public ProductPlanEntity(){}
	public ProductPlanEntity(String planId, String productId, String gitlabProject,String milestoneId, String mid) {
		super();
		this.planId = planId;
		this.productId = productId;
		this.gitlabProject = gitlabProject;
		this.milestoneId = milestoneId;
		this.mid = mid;
	}
	
	public String getUuid() {
		return uuid;
	}
	public String getPlanId() {
		return planId;
	}
	public String getProductId() {
		return productId;
	}
	public String getGitlabProject() {
		return gitlabProject;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public void setPlanId(String planId) {
		this.planId = planId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public void setGitlabProject(String gitlabProject) {
		this.gitlabProject = gitlabProject;
	}
	public String getMilestoneId() {
		return milestoneId;
	}
	public void setMilestoneId(String milestoneId) {
		this.milestoneId = milestoneId;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	@Override
	public String toString() {
		return "ProductPlanEntity [uuid=" + uuid + ", planId=" + planId + ", productId=" + productId
				+ ", gitlabProject=" + gitlabProject + ", milestoneId=" + milestoneId + ", mid=" + mid + "]";
	}
	
}
