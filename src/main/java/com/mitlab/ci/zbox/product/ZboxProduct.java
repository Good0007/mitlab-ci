package com.mitlab.ci.zbox.product;

import com.mitlab.ci.zbox.product.plan.ZboxProductPlanDetails;

public class ZboxProduct {
	
	private String title;
	private ZboxProductDetails product;
	private ZboxProductPlanDetails[] plans;

	public ZboxProductDetails getProduct() {
		return product;
	}

	public void setProduct(ZboxProductDetails product) {
		this.product = product;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public ZboxProductPlanDetails[] getPlans() {
		return plans;
	}

	public void setPlans(ZboxProductPlanDetails[] plans) {
		this.plans = plans;
	}
	
	
	
}
