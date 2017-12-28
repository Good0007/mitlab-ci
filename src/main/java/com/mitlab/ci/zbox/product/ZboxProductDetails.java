package com.mitlab.ci.zbox.product;

public class ZboxProductDetails {

	private String id;
	private String name;
	private String code;
	private String type;
	private String status;
	private String desc;
	
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getCode() {
		return code;
	}
	public String getType() {
		return type;
	}
	public String getStatus() {
		return status;
	}
	public String getDesc() {
		return desc;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	@Override
	public String toString() {
		return "ZboxProduct [id=" + id + ", name=" + name + ", code=" + code + ", type=" + type + ", status=" + status
				+ ", desc=" + desc + "]";
	}
}
