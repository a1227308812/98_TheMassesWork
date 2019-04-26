package com.westar.masseswork_98.been;

import java.io.Serializable;

/**
 * Created by ZWP on 2019/4/26 20:40.
 * 描述：
 */
public class GlobalSearchHistory implements Serializable {
    String title;
    String hotNum;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHotNum() {
        return hotNum;
    }

    public void setHotNum(String hotNum) {
        this.hotNum = hotNum;
    }
}
