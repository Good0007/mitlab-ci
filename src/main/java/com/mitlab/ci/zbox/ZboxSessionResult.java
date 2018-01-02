package com.mitlab.ci.zbox;

import java.io.IOException;

public class ZboxSessionResult extends ZboxResult {

    private String data;
    private String md5;
    private ZboxSession session;

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
        this.transData2Session(data);
    }

    public ZboxSession getSession() {
        return session;
    }

    public void setSession(ZboxSession session) {
        this.session = session;
    }

    protected void transData2Session(String data) {
        try {
            this.setSession(ZboxUtil.getInstance("").newObjectMapper().readValue(data, ZboxSession.class));
        } catch (IOException e) {
            throw new ZboxException(e.getMessage(), e);
        }
    }
}
