package com.westar.been;

import android.support.annotation.NonNull;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.Index;

/**
 * Created by luoyz on 2019/4/12 11:50
 * Version : 1.0
 * Last update by luoyz on 2019/4/12 11:50
 * Describe :选择地址中地址节点实体
 */

public class AddressNode extends RealmObject implements Serializable {
    public final static int ROOTPOSITION = -1;

    private Integer nodeId;//节点ID
    private String nodeName;//节点名称
    @Index
    private Integer parentId;//父节点ID  根节点值为-1
    private boolean check = false;//选中状态
    private boolean expand = false;//是否默认展开  根节点默认展开  及 parentId 0== -1

    public Integer getNodeId() {
        return nodeId;
    }

    public AddressNode setNodeId(Integer nodeId) {
        this.nodeId = nodeId;
        return this;
    }

    public String getNodeName() {
        return nodeName;
    }

    public AddressNode setNodeName(String nodeName) {
        this.nodeName = nodeName;
        return this;
    }

    public Integer getParentId() {
        return parentId;
    }

    public AddressNode setParentId(@NonNull Integer parentId) {
        this.parentId = parentId;
        return this;
    }

    public boolean isCheck() {
        return check;
    }

    public AddressNode setCheck(boolean check) {
        this.check = check;
        return this;
    }

    public boolean isExpand() {
        return expand;
    }

    public AddressNode setExpand(boolean expand) {
        this.expand = expand;
        return this;
    }
}
