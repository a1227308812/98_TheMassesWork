package com.westar.module_login.ui;


import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.ToastUtils;
import com.coorchice.library.SuperTextView;
import com.jakewharton.rxbinding2.view.RxView;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.westar.Config;
import com.westar.library_base.base.BaseActivity;
import com.westar.library_base.common.ArouterPath;
import com.westar.library_base.view.shadowView.ShadowHelper;
import com.westar.library_base.view.shadowView.ShadowProperty;
import com.westar.library_base.base.BasePresenter;
import com.westar.module_login.R;

import java.util.concurrent.TimeUnit;

import io.reactivex.functions.Consumer;

/**
 * Created by ZWP on 2019/4/9 10:40.
 * 描述：登录方式选择
 */
public class LoginTypeSelectActivity extends BaseActivity {


    SuperTextView stvWeixin;
    SuperTextView stvZhifubao;
    SuperTextView stvMsg;
    ConstraintLayout contentLayout;

    TextView tvQuickLogin;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_login_type_select;
    }

    @Override
    protected void initStatusBar() {
        //状态栏透明 字体和图标白色
        QMUIStatusBarHelper.setStatusBarDarkMode(this);
    }

    @Override
    protected void initView() {
        //设置指定控件的阴影
        ShadowHelper.bindView(contentLayout, new ShadowProperty()
                .setShadowRadius(5)
                .setShadowColor(ContextCompat.getColor(mContext, R.color.shadow_color))
                .setRoundwWidth(10));

        tvQuickLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showShort("游客登录");
//                ARouter.getInstance()
//                        .build(ArouterPath.APP_HOMEGROUP_ACTIVITY)
//                        .navigation();

                skipActivity(TestActivity.class, null);
            }
        });

        RxView.clicks(stvWeixin).throttleFirst(Config.WINDOWDURATION, TimeUnit.SECONDS).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                ToastUtils.showShort("微信登录！");
            }
        });
        RxView.clicks(stvZhifubao).throttleFirst(Config.WINDOWDURATION, TimeUnit.SECONDS).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                ToastUtils.showShort("支付宝登录！");
            }
        });
        RxView.clicks(stvMsg).throttleFirst(Config.WINDOWDURATION, TimeUnit.SECONDS).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                skipActivity(LoginActivity.class, null);
            }
        });
    }

    @Override
    public void findId() {
        stvWeixin = findViewById(R.id.stv_weixin);
        stvZhifubao = findViewById(R.id.stv_zhifubao);
        stvMsg = findViewById(R.id.stv_msg);
        contentLayout = findViewById(R.id.contentLayout);
        tvQuickLogin = findViewById(R.id.tv_quick_login);
    }

    @Override
    protected void initData() {

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

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }
}