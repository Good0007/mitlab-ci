package com.mitlab.ci.zbox;

import org.codehaus.jackson.annotate.JsonProperty;

public class ZboxProject {
    private String id;
    private String isCat;
    private String catID;
    private String type;
    private String parent;
    private String name;
    private String code;
    private String begin;
    private String end;
    private String days;
    private String status;
    private String statge;
    private String pri;
    private String desc;
    private String opendBy;
    private String openedDate;
    private String openedVersion;
    private String closedBy;
    private String closedDate;
    private String canceledBy;
    private String canceledDate;
    @JsonProperty("PO")
    private String PO;
    @JsonProperty("PM")
    private String PM;
    @JsonProperty("QD")
    private String QD;
    @JsonProperty("RD")
    private String RD;
    private String team;
    private String acl;
    private String openedBy;

    public String getRD() {
        return RD;
    }

    public void setRD(String RD) {
        this.RD = RD;
    }

    public String getOpenedBy() {
        return openedBy;
    }

    public void setOpenedBy(String openedBy) {
        this.openedBy = openedBy;
    }

    @JsonProperty("private")
    private String privateKey;
    private String whitelist;
    private String order;
    private String deleted;
    private String totalHours;
    private int totalEstimate;
    private int totalConsumed;
    private int totalLeft;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsCat() {
        return isCat;
    }

    public void setIsCat(String isCat) {
        this.isCat = isCat;
    }

    public String getCatID() {
        return catID;
    }

    public void setCatID(String catID) {
        this.catID = catID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBegin() {
        return begin;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatge() {
        return statge;
    }

    public void setStatge(String statge) {
        this.statge = statge;
    }

    public String getPri() {
        return pri;
    }

    public void setPri(String pri) {
        this.pri = pri;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getOpendBy() {
        return opendBy;
    }

    public void setOpendBy(String opendBy) {
        this.opendBy = opendBy;
    }

    public String getOpenedDate() {
        return openedDate;
    }

    public void setOpenedDate(String openedDate) {
        this.openedDate = openedDate;
    }

    public String getOpenedVersion() {
        return openedVersion;
    }

    public void setOpenedVersion(String openedVersion) {
        this.openedVersion = openedVersion;
    }

    public String getClosedBy() {
        return closedBy;
    }

    public void setClosedBy(String closedBy) {
        this.closedBy = closedBy;
    }

    public String getClosedDate() {
        return closedDate;
    }

    public void setClosedDate(String closedDate) {
        this.closedDate = closedDate;
    }

    public String getCanceledBy() {
        return canceledBy;
    }

    public void setCanceledBy(String canceledBy) {
        this.canceledBy = canceledBy;
    }

    public String getCanceledDate() {
        return canceledDate;
    }

    public void setCanceledDate(String canceledDate) {
        this.canceledDate = canceledDate;
    }

    public String getPO() {
        return PO;
    }

    public void setPO(String PO) {
        this.PO = PO;
    }

    public String getPM() {
        return PM;
    }

    public void setPM(String PM) {
        this.PM = PM;
    }

    public String getQD() {
        return QD;
    }

    public void setQD(String QD) {
        this.QD = QD;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getAcl() {
        return acl;
    }

    public void setAcl(String acl) {
        this.acl = acl;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getWhitelist() {
        return whitelist;
    }

    public void setWhitelist(String whitelist) {
        this.whitelist = whitelist;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public String getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(String totalHours) {
        this.totalHours = totalHours;
    }

    public int getTotalEstimate() {
        return totalEstimate;
    }

    public void setTotalEstimate(int totalEstimate) {
        this.totalEstimate = totalEstimate;
    }

    public int getTotalConsumed() {
        return totalConsumed;
    }

    public void setTotalConsumed(int totalConsumed) {
        this.totalConsumed = totalConsumed;
    }

    public int getTotalLeft() {
        return totalLeft;
    }

    public void setTotalLeft(int totalLeft) {
        this.totalLeft = totalLeft;
    }

    @Override
    public String toString() {
        return "ZboxProject{" +
                "id='" + id + '\'' +
                ", isCat='" + isCat + '\'' +
                ", catID='" + catID + '\'' +
                ", type='" + type + '\'' +
                ", parent='" + parent + '\'' +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", begin='" + begin + '\'' +
                ", end='" + end + '\'' +
                ", days='" + days + '\'' +
                ", status='" + status + '\'' +
                ", statge='" + statge + '\'' +
                ", pri='" + pri + '\'' +
                ", desc='" + desc + '\'' +
                ", opendBy='" + opendBy + '\'' +
                ", openedDate='" + openedDate + '\'' +
                ", openedVersion='" + openedVersion + '\'' +
                ", closedBy='" + closedBy + '\'' +
                ", closedDate='" + closedDate + '\'' +
                ", canceledBy='" + canceledBy + '\'' +
                ", canceledDate='" + canceledDate + '\'' +
                ", PO='" + PO + '\'' +
                ", PM='" + PM + '\'' +
                ", QD='" + QD + '\'' +
                ", RD='" + RD + '\'' +
                ", team='" + team + '\'' +
                ", acl='" + acl + '\'' +
                ", openedBy='" + openedBy + '\'' +
                ", privateKey='" + privateKey + '\'' +
                ", whitelist='" + whitelist + '\'' +
                ", order='" + order + '\'' +
                ", deleted='" + deleted + '\'' +
                ", totalHours='" + totalHours + '\'' +
                ", totalEstimate=" + totalEstimate +
                ", totalConsumed=" + totalConsumed +
                ", totalLeft=" + totalLeft +
                '}';
    }
}
