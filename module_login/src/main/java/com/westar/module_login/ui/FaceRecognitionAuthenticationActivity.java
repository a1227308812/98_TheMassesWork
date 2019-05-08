package com.westar.module_login.ui;

import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.coorchice.library.SuperTextView;
import com.jakewharton.rxbinding2.view.RxView;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.westar.Config;
import com.westar.library_base.base.BasePresenter;
import com.westar.library_base.base.ToolbarActivity;
import com.westar.library_base.common.ArouterPath;
import com.westar.module_login.R;

import java.util.concurrent.TimeUnit;

import io.reactivex.functions.Consumer;

/**
 * Created by ZWP on 2019/5/8 15:52.
 * 描述：人脸识别认证
 */
@Route(path = ArouterPath.APP_FACE_RECOGNITION_AUTHENTICATION_ACTIVITY)
public class FaceRecognitionAuthenticationActivity extends ToolbarActivity {


    TextView tvTip;
    SuperTextView stvNext;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_face_recognition_authentication;
    }

    @Override
    protected void findId() {
        tvTip = findViewById(R.id.tv_tip);
        stvNext = findViewById(R.id.stv_next);
    }

    @Override
    protected void initView() {
        addSubscribe(RxView.clicks(stvNext).throttleFirst(Config.WINDOWDURATION, TimeUnit.SECONDS).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                skipActivity(FaceInAuthenticationActivity.class, null);
            }
        }));
    }

    @Override
    protected void initData() {

    }

    @Override
    public String setBarTitle() {
        return "人脸识别认证";
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
