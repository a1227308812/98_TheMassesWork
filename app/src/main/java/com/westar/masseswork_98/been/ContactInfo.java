package com.westar.masseswork_98.been;

import java.io.Serializable;

public class ContactInfo implements Serializable{
    private String depName;
    private String phone;

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
