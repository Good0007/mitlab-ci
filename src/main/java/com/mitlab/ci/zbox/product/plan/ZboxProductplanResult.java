package com.mitlab.ci.zbox.product.plan;

import java.io.IOException;

import com.mitlab.ci.zbox.ZboxException;
import com.mitlab.ci.zbox.ZboxResult;
import com.mitlab.ci.zbox.ZboxUtil;

public class ZboxProductplanResult extends ZboxResult{

	 private String md5;
	 private String data;
	 private ZboxProductplan plan;
	 
	public String getData() {
		return data;
	}
	public ZboxProductplan getPlan() {
		return plan;
	}
	public void setData(String data) {
		this.data = data;
		this.transData2Producplan(data);
	}
	public void setPlan(ZboxProductplan plan) {
		this.plan = plan;
	}
	public String getMd5() {
		return md5;
	}
	public void setMd5(String md5) {
		this.md5 = md5;
	}
	
	protected void transData2Producplan(String data) {
        try {
            this.setPlan(ZboxUtil.getInstance("").newObjectMapper().readValue(data, ZboxProductplan.class));
        } catch (IOException e) {
            throw new ZboxException(e.getMessage(), e);
        }
    }
}
