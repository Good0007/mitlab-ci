package com.mitlab.ci.zbox.bug;

import java.io.IOException;

import com.mitlab.ci.zbox.ZboxException;
import com.mitlab.ci.zbox.ZboxResult;
import com.mitlab.ci.zbox.ZboxUtil;

public class ZboxBugResult extends ZboxResult {
    private String data;
    private String md5;
    private ZboxBug bug;

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
        this.transData2Bug(data);
    }

    public ZboxBug getBug() {
        return bug;
    }

    public void setBug(ZboxBug bug) {
        this.bug = bug;
    }

    protected void transData2Bug(String data) {
        try {
            this.setBug(ZboxUtil.getInstance("").newObjectMapper().readValue(data, ZboxBug.class));
        } catch (IOException e) {
            throw new ZboxException(e.getMessage(), e);
        }
    }
}
