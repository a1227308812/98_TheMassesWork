package com.westar.library_base.preview.helper;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.westar.library_base.preview.ui.TbsPreviewActivity;
import com.westar.library_base.preview.bean.GlobalBean;

/**
 * Created by luoyz on 2019/3/26 10:58
 * Version : 1.0
 * Last update by luoyz on 2019/3/26 10:58
 * Describe :文件预览
 */

public class FilePreviewHelper {
    private Context context;
    private String fileUrl;//文件下载地址
    private String fileType;//文件类型
    private String fileName;//文件名
    private String uuid;//文件唯一标识符

    public static class Builder {
        private Context context;
        private String fileUrl;//文件下载地址
        private String fileType;//文件类型
        private String fileName;//文件名
        private String uuid;//文件唯一标识符

        public Builder(Context context, String fileUrl, String fileType) {
            this.context = context;
            this.fileUrl = fileUrl;
            this.fileType = fileType;
        }

        public Builder fileName(String fileName) {
            this.fileName = fileName;
            return this;
        }

        public Builder uuid(String uuid) {
            this.uuid = uuid;
            return this;
        }

        public FilePreviewHelper build() {
            return new FilePreviewHelper(this);
        }
    }

    private FilePreviewHelper(Builder build) {
        this.context = build.context;
        this.fileUrl = build.fileUrl;
        this.fileType = build.fileType;
        this.fileName = build.fileName;
        this.uuid = build.uuid;
    }

    /**
     * 跳转到文件预览界面
     */
    public void jump2PreviewActivity() {
        Bundle bundle = new Bundle();
        bundle.putString(GlobalBean.KEY_FILE_URL, fileUrl);
        bundle.putString(GlobalBean.KEY_FILE_TYPE, fileType);
        if (null != fileName && !"".equals(fileName)) {
            bundle.putString(GlobalBean.KEY_FILE_NAME, fileName);
        }
        if (null != uuid && !"".equals(uuid)) {
            bundle.putString(GlobalBean.KEY_FILE_UUID, uuid);
        }
        Intent intent = new Intent();
        intent.setClass(context, TbsPreviewActivity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}
