package com.mitlab.ci.zbox.product;

import java.io.IOException;

import com.mitlab.ci.AbstractMitlabUtil;
import com.mitlab.ci.zbox.ZboxException;
import com.mitlab.ci.zbox.ZboxResult;

public class ZboxProductResult extends ZboxResult{

	private String data;
	private String md5;
	private ZboxProduct product;
	
	public String getData() {
		return data;
	}

	public ZboxProduct getProduct() {
		return product;
	}

	public void setData(String data) {
		this.data = data;
		this.transData2Product(data);
	}

	public void setProduct(ZboxProduct product) {
		this.product = product;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}
	

	protected void transData2Product(String data) {
        try {
			this.setProduct(AbstractMitlabUtil.newObjectMapper().readValue(data, ZboxProduct.class));
        } catch (IOException e) {
            throw new ZboxException(e.getMessage(), e);
        }
    }


}
