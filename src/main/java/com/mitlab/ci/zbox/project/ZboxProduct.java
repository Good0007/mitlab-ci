package com.mitlab.ci.zbox.project;

import com.sun.javafx.collections.MappingChange.Map;

public class ZboxProduct {

	private Map<String, ZboxProductDetails> products;

	public Map<String, ZboxProductDetails> getProducts() {
		return products;
	}

	public void setProducts(Map<String, ZboxProductDetails> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "ZboxProduct [products=" + products + "]";
	}
	

}
