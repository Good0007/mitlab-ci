package com.mitlab.ci.zbox.project;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

import com.mitlab.ci.AbstractMitlabUtil;
import com.mitlab.ci.zbox.ZboxException;

/**
 * 禅道项目列表
 * @author 维康
 *
 */
public class ZboxProjectsResult {
	
	private String md5;
	private String data;
	/**
	 * key projectId, 
	 * value projectName
	 */
	private Map<String, String> projects;
	
	public String getMd5() {
		return md5;
	}
	public String getData() {
		return data;
	}
	public void setMd5(String md5) {
		this.md5 = md5;
	}
	public void setData(String data) {
		this.data = data;
		this.transData2Projects(data);
	}
	public Map<String, String> getProjects() {
		return projects;
	}
	public void setProjects(Map<String, String> projects) {
		this.projects = projects;
	}
	@Override
	public String toString() {
		return "ZboxProjectsResult [md5=" + md5 + ", data=" + data + ", projects=" + projects + "]";
	}
	
	protected void transData2Projects(String data) {
		 Map<String, String> map = new HashMap<String, String>();
       try {
    	   JsonNode jsonNode =  AbstractMitlabUtil.newObjectMapper().readTree(data);
           Iterator<Entry<String, JsonNode>> elements = jsonNode.getFields();
           while (elements.hasNext()) {
             Entry<String, JsonNode> node = elements.next();
             String key = node.getKey();
             if("projects".equals(key)){
           	  JsonNode root =  AbstractMitlabUtil.newObjectMapper().readTree(node.getValue().toString());
           	  Iterator<Entry<String, JsonNode>> elements2 = root.getFields();
           	  while (elements2.hasNext()) {
       	        Entry<String, JsonNode> node2 = elements2.next();
       	        String key2 = node2.getKey();
       	        String value2 = node2.getValue().toString();
       	        map.put(key2, value2);
       	      }
           	  break;
             }
           }
       } catch (IOException e) {
           throw new ZboxException(e.getMessage(), e);
       }
       this.setProjects(map);
   }
	
}
