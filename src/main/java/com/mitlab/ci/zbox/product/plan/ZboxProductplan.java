package com.mitlab.ci.zbox.product.plan;

import com.mitlab.ci.zbox.product.ZboxProductDetails;

public class ZboxProductplan {
	
	private String title;
	private ZboxProductDetails product;
	private ZboxProductPlanDetails plan;
	
	public String getTitle() {
		return title;
	}
	public ZboxProductDetails getProduct() {
		return product;
	}
	public ZboxProductPlanDetails getPlan() {
		return plan;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setProduct(ZboxProductDetails product) {
		this.product = product;
	}
	public void setPlan(ZboxProductPlanDetails plan) {
		this.plan = plan;
	}
	
	
}
