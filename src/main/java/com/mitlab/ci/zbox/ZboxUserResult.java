package com.mitlab.ci.zbox;

import com.mitlab.ci.zbox.ZboxResult;
import com.mitlab.ci.zbox.ZboxUser;

public class ZboxUserResult extends ZboxResult {
    private ZboxUser user;

    public ZboxUser getUser() {
        return user;
    }

    public void setUser(ZboxUser user) {
        this.user = user;
    }
}
