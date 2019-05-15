package com.westar.been;

import java.util.List;

/**
 * Created by ZWP on 2019/5/14 15:33.
 * 描述：
 */
public class ItemInfo {
    String itemTitle;
    String bljg;
    String zxdh;
    int star;
    List<String> typeList;
    List<String> functionList;

    public String getItemTitle() {
        return itemTitle;
    }

    public ItemInfo setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
        return this;
    }

    public String getBljg() {
        return bljg;
    }

    public ItemInfo setBljg(String bljg) {
        this.bljg = bljg;
        return this;
    }

    public String getZxdh() {
        return zxdh;
    }

    public ItemInfo setZxdh(String zxdh) {
        this.zxdh = zxdh;
        return this;
    }

    public int getStar() {
        return star;
    }

    public ItemInfo setStar(int star) {
        this.star = star;
        return this;
    }

    public List<String> getTypeList() {
        return typeList;
    }

    public ItemInfo setTypeList(List<String> typeList) {
        this.typeList = typeList;
        return this;
    }

    public List<String> getFunctionList() {
        return functionList;
    }

    public ItemInfo setFunctionList(List<String> functionList) {
        this.functionList = functionList;
        return this;
    }
}
