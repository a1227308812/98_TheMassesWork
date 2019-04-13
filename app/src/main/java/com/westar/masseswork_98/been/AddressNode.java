package com.westar.masseswork_98.been;

import android.support.annotation.NonNull;

/**
 * Created by luoyz on 2019/4/12 11:50
 * Version : 1.0
 * Last update by luoyz on 2019/4/12 11:50
 * Describe :选择地址中地址节点实体
 */

public class AddressNode {
    private Integer nodeId;//节点ID
    private String nodeName;//节点名称
    private Integer parentId = -1;//父节点ID  根节点值为-1
    private boolean check;//选中状态
    private boolean expand = false;//是否默认展开  根节点默认展开  及 parentId 0== -1

    public AddressNode(Integer nodeId, String nodeName, Integer parentId, boolean check) {
        this.nodeId = nodeId;
        this.nodeName = nodeName;
        this.parentId = parentId;
        this.check = check;
    }

    public Integer getNodeId() {
        return nodeId;
    }

    public void setNodeId(Integer nodeId) {
        this.nodeId = nodeId;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(@NonNull Integer parentId) {
        this.parentId = parentId;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public boolean isExpand() {
        return expand;
    }

    public void setExpand(boolean expand) {
        this.expand = expand;
    }
}
