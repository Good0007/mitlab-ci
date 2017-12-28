package com.mitlab.ci.zbox.project;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

import com.mitlab.ci.zbox.ZboxException;
import com.mitlab.ci.zbox.ZboxResult;
import com.mitlab.ci.zbox.ZboxUtil;

public class ZboxProjectResult extends ZboxResult{

	private String md5;
	private String data;
	private ZboxProject project;
	private Map<String, ZboxProductDetails> products;
	 
	public String getMd5() {
		return md5;
	}

	public String getData() {
		return data;
	}

	public ZboxProject getProject() {
		return project;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public void setData(String data) {
		this.data = data;
		this.transData2Project(data);
	}

	public void setProject(ZboxProject project) {
		this.project = project;
	}
	

	public Map<String, ZboxProductDetails> getProducts() {
		return products;
	}

	public void setProducts(Map<String, ZboxProductDetails> products) {
		this.products = products;
	}

	@SuppressWarnings("static-access")
	protected void transData2Project(String data) {
		 Map<String, ZboxProductDetails> map = new HashMap<String, ZboxProductDetails>();
        try {
            this.setProject(ZboxUtil.getInstance().newObjectMapper().readValue(data, ZboxProject.class));
            JsonNode jsonNode =  ZboxUtil.getInstance().newObjectMapper().readTree(data);
            Iterator<Entry<String, JsonNode>> elements = jsonNode.getFields();
            while (elements.hasNext()) {
              Entry<String, JsonNode> node = elements.next();
              String key = node.getKey();
              if("products".equals(key)){
            	  JsonNode root = new ObjectMapper().readTree(node.getValue().toString());
            	  Iterator<Entry<String, JsonNode>> elements2 = root.getFields();
            	  while (elements2.hasNext()) {
        	        Entry<String, JsonNode> node2 = elements2.next();
        	        String key2 = node2.getKey();
        	        String obj = node2.getValue().toString();
        	        ZboxProductDetails e = new ObjectMapper().readValue(obj, ZboxProductDetails.class);
        	        map.put(key2, e);
        	      }
              }
            }
        } catch (IOException e) {
            throw new ZboxException(e.getMessage(), e);
        }
        this.setProducts(map);
    }
	
	
}
