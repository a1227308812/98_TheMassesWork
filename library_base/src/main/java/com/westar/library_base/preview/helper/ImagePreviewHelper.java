package com.westar.library_base.preview.helper;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.westar.library_base.preview.bean.GlobalBean;
import com.westar.library_base.preview.ui.ImagePreviewDialogFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luoyz on 2019/3/28 14:13
 * Version : 1.0
 * Last update by luoyz on 2019/3/28 14:13
 * Describe : 图片预览
 */

public class ImagePreviewHelper {
    private AppCompatActivity activity;
    private String mUrl;
    private List<String> mUrls;
    private int mDefaultPos;

    public static class Builder {
        private AppCompatActivity activity;
        private String mUrl;
        private List<String> mUrls;
        private int mDefaultPos = 0;

        public Builder(AppCompatActivity activity, @NonNull String mUrl) {
            this.activity = activity;
            this.mUrl = mUrl;
        }

        public Builder(AppCompatActivity activity, @NonNull List<String> mUrls) {
            this.activity = activity;
            this.mUrls = mUrls;
        }

        public Builder defaultPos(int pos) {
            mDefaultPos = pos;
            return this;
        }

        public ImagePreviewHelper build() {
            return new ImagePreviewHelper(this);
        }
    }

    private ImagePreviewHelper(Builder builder) {
        this.activity = builder.activity;
        this.mUrl = builder.mUrl;
        this.mUrls = builder.mUrls;
        this.mDefaultPos = builder.mDefaultPos;
        if (this.mUrl != null && !"".equals(this.mUrl)) {
            this.mUrls = new ArrayList<>();
            this.mUrls.add(mUrl);
        }
        if (mDefaultPos > this.mUrls.size() - 1) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * 跳转到图片预览界面
     */
    public void jump2PreviewView() {
        Bundle bundle = new Bundle();
        bundle.putStringArrayList(GlobalBean.KEY_IMAGE_URL, (ArrayList<String>) mUrls);
        bundle.putInt(GlobalBean.KEY_DEFAULT_POS, mDefaultPos);
        ImagePreviewDialogFragment fragment = new ImagePreviewDialogFragment();
        fragment.setArguments(bundle);
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        transaction.add(fragment, "img");
        transaction.commit();
    }
}
