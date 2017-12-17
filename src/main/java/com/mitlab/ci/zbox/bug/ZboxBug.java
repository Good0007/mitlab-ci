package com.mitlab.ci.zbox.bug;

public class ZboxBug {
    private String title;
    private String productID;
    private String productName;
    private ZboxBugDetails bug;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public ZboxBugDetails getBug() {
        return bug;
    }

    public void setBug(ZboxBugDetails bug) {
        this.bug = bug;
    }

    @Override
    public String toString() {
        return "ZboxBug{" +
                "title='" + title + '\'' +
                ", productID='" + productID + '\'' +
                ", productName='" + productName + '\'' +
                ", bug=" + bug +
                '}';
    }
}
