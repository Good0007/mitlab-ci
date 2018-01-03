package com.mitlab.ci.zbox.task;

import java.io.IOException;

import com.mitlab.ci.AbstractMitlabUtil;
import com.mitlab.ci.zbox.ZboxException;
import com.mitlab.ci.zbox.ZboxResult;

public class ZboxTaskResult extends ZboxResult {
    private String data;
    private String md5;
    private ZboxTask task;

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
        this.transData2Task(data);
    }

    public ZboxTask getTask() {
        return task;
    }

    public void setTask(ZboxTask task) {
        this.task = task;
    }

    protected void transData2Task(String data) {
        try {
			this.setTask(AbstractMitlabUtil.newObjectMapper().readValue(data, ZboxTask.class));
        } catch (IOException e) {
            throw new ZboxException(e.getMessage(), e);
        }
    }
}
