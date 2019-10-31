package com.westar.module_woyaozixun.databean;

import java.io.Serializable;

public class Reply implements Serializable{
    String userContent;
    String sysContent;

    public String getUserContent() {
        return userContent;
    }

    public void setUserContent(String userContent) {
        this.userContent = userContent;
    }

    public String getSysContent() {
        return sysContent;
    }

    public void setSysContent(String sysContent) {
        this.sysContent = sysContent;
    }
}
