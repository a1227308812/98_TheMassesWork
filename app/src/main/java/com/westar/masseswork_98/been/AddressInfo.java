package com.westar.masseswork_98.been;

/**
 * Created by ZWP on 2019/5/9 15:58.
 * 描述：收件地址
 */
public class AddressInfo {
    String name;
    String address;
    String tel;
    boolean selected;


    public String getName() {
        return name;
    }

    public AddressInfo setName(String name) {
        this.name = name;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public AddressInfo setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getTel() {
        return tel;
    }

    public AddressInfo setTel(String tel) {
        this.tel = tel;
        return this;
    }

    public boolean isSelected() {
        return selected;
    }

    public AddressInfo setSelected(boolean selected) {
        this.selected = selected;
        return this;
    }
}
