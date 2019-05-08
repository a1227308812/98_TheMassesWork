package com.westar.module_login.ui;

import android.os.Bundle;
import android.widget.TextView;

import com.coorchice.library.SuperTextView;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.westar.library_base.base.BasePresenter;
import com.westar.library_base.base.ToolbarActivity;
import com.westar.module_login.R;

import butterknife.ButterKnife;

/**
 * Created by ZWP on 2019/5/8 15:52.
 * 描述：人脸识别认证中
 */
public class FaceInAuthenticationActivity extends ToolbarActivity {



    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_face_in_authentication;
    }

    @Override
    protected void findId() {
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public String setBarTitle() {
        return "人脸识别中";
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onOther(Object data) {

    }

    @Override
    public void onSuccess(Object data) {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public LifecycleTransformer bindViewToLifecycle() {
        return null;
    }

}
