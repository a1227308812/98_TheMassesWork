package com.westar.masseswork_98.library_update.been;

import java.io.Serializable;

/**
 * Created by ZWP on 2019/3/12.
 * 描述：更新类
 */
public class Version implements Serializable {

    //版本名称
    private String versionName;
    //版本更新说明
    private String versionNote;
    //版本号
    private Integer versionCode;
    //apk下载路径
    private String filePath;

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getVersionNote() {
        return versionNote;
    }

    public void setVersionNote(String versionNote) {
        this.versionNote = versionNote;
    }

    public Integer getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(Integer versionCode) {
        this.versionCode = versionCode;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
