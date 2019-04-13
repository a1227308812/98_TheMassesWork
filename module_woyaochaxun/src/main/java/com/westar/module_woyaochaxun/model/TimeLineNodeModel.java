package com.westar.module_woyaochaxun.model;

import java.io.Serializable;

/**
 * 时间轴所需数据
 * Created by zb on 2019/4/11.
 */

public class TimeLineNodeModel implements Serializable{

    //处理日期 (yyyy.MM.dd hh:mm)
   private String doDate;

   //办件节点
   private String nodeName;

   //是否高亮显示连接线   默认不高亮
   private boolean showLightLine = false;

   //节点类型
   private String nodeType;

    public String getDoDate() {
        return doDate;
    }

    public void setDoDate(String doDate) {
        this.doDate = doDate;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public boolean isShowLightLine() {
        return showLightLine;
    }

    public void setShowLightLine(boolean showLightLine) {
        this.showLightLine = showLightLine;
    }

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }
}
