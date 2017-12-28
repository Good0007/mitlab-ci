package com.mitlab.ci.zbox.product.plan;

/**
 * ProductPlan实体
 * @author 维康
 *
 */
public class ZboxProductPlanDetails {

	/**
	 * planId
	 */
	  private String  id ;
	  /**
	   * 产品id
	   */
      private String  product;
      
      private String  branch;
      private String  title ;
      private String  desc;
      private String  begin;
      private String  end;
      
      
		public String getId() {
			return id;
		}
		public String getProduct() {
			return product;
		}
	
		public String getBranch() {
			return branch;
		}
		public String getTitle() {
			return title;
		}
		public String getDesc() {
			return desc;
		}
		public String getBegin() {
			return begin;
		}
		public String getEnd() {
			return end;
		}
		public void setId(String id) {
			this.id = id;
		}
		public void setProduct(String product) {
			this.product = product;
		}
		public void setBranch(String branch) {
			this.branch = branch;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public void setDesc(String desc) {
			this.desc = desc;
		}
		public void setBegin(String begin) {
			this.begin = begin;
		}
		public void setEnd(String end) {
			this.end = end;
		}
	
		@Override
		public String toString() {
			return "ZboxProductplan [id=" + id + ", product=" + product + ", branch=" + branch + ", title=" + title
					+ ", desc=" + desc + ", begin=" + begin + ", end=" + end + "]";
		}
      
}
