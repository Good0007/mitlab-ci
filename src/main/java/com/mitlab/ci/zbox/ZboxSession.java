package com.mitlab.ci.zbox;

import com.mitlab.ci.manager.SettingEntity;

public class ZboxSession {
    private String title;
    private String zentaosid;
    private String sessionID;
    private String sessionName;
    private int rand;
    private SettingEntity settingInfo;

    public String getSessionName() {
        return sessionName;
    }

    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getZentaosid() {
        return zentaosid;
    }

    public void setZentaosid(String zentaosid) {
        this.zentaosid = zentaosid;
    }

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    public int getRand() {
        return rand;
    }

    public void setRand(int rand) {
        this.rand = rand;
    }

    public String getPager() {
        return pager;
    }

    public void setPager(String pager) {
        this.pager = pager;
    }

    private String pager;


	public SettingEntity getSettingInfo() {
		return settingInfo;
	}

	public void setSettingInfo(SettingEntity settingInfo) {
		this.settingInfo = settingInfo;
	}

	@Override
	public String toString() {
		return "ZboxSession [title=" + title + ", zentaosid=" + zentaosid + ", sessionID=" + sessionID
				+ ", sessionName=" + sessionName + ", rand=" + rand + ", settingInfo=" + settingInfo + ", pager="
				+ pager + "]";
	}
	
}
