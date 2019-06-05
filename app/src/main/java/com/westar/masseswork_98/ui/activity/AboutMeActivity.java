package com.westar.masseswork_98.ui.activity;


import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.coorchice.library.SuperTextView;
import com.jakewharton.rxbinding2.view.RxView;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.westar.Config;
import com.westar.library_base.base.BasePresenter;
import com.westar.library_base.base.ToolbarActivity;
import com.westar.library_base.callback.IPermissionsCallBack;
import com.westar.library_base.common.ArouterPath;
import com.westar.library_base.http.been.HttpRequest;
import com.westar.masseswork_98.R;
import com.westar.masseswork_98.been.AboutMe;
import com.westar.masseswork_98.mvp.contract.AboutMeContract;
import com.westar.masseswork_98.mvp.presenter.AboutMePresenter;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.functions.Consumer;

/**
 * Created by ZWP on 2019/5/7 20:45.
 * 描述：
 */
@Route(path = ArouterPath.ABOUTME_ACTIVITY)
public class AboutMeActivity extends ToolbarActivity implements AboutMeContract.View {

    @BindView(R.id.stv_logo)
    SuperTextView stvLogo;
    @BindView(R.id.stv_version)
    SuperTextView stvVersion;
    @BindView(R.id.tv_app_name)
    TextView tvAppName;
    @BindView(R.id.tv_app_describe)
    TextView tvAppDescribe;
    @BindView(R.id.tv_tel_num)
    TextView tvTelNum;
    @BindView(R.id.ll_tel)
    LinearLayout llTel;
    @BindView(R.id.tv_email_address)
    TextView tvEmailAddress;
    @BindView(R.id.ll_email)
    LinearLayout llEmail;
    @BindView(R.id.tv_web_site_address)
    TextView tvWebSiteAddress;
    @BindView(R.id.ll_web_site)
    LinearLayout llWebSite;
    @BindView(R.id.stv_feedback)
    SuperTextView stvFeedback;

    AboutMePresenter presenter;

    @Override
    public String setBarTitle() {
        return "关于我们";
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_about_me;
    }

    @Override
    protected void findId() {

    }

    @Override
    protected void initView() {
        addSubscribe(RxView.clicks(llTel).throttleFirst(Config.WINDOWDURATION, TimeUnit.SECONDS).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                requestPermissions(new String[]{Manifest.permission.CALL_PHONE},
                        true,
                        true,
                        new IPermissionsCallBack() {
                            @Override
                            public void permissionErro(String name) {
                                ToastUtils.showShort("获取权限失败！");
                            }

                            @Override
                            public void permissionSuccess(String name) {
                                if (!TextUtils.isEmpty(tvTelNum.getText().toString())) {
                                    Intent intent = new Intent();
//                                    intent.setAction(Intent.ACTION_CALL);//直接打电话
                                    intent.setAction(Intent.ACTION_DIAL);//渠道拨号界面，电话由用户发起拨打
                                    intent.setData(Uri.parse("tel:" + tvTelNum.getText().toString()));
                                    startActivity(intent);
                                }
                            }
                        });


            }
        }));
        addSubscribe(RxView.clicks(llEmail).throttleFirst(Config.WINDOWDURATION, TimeUnit.SECONDS).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                ToastUtils.showShort("发送邮件");

            }
        }));
        addSubscribe(RxView.clicks(llWebSite).throttleFirst(Config.WINDOWDURATION, TimeUnit.SECONDS).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                ToastUtils.showShort("跳转网站");
                String email = tvEmailAddress.getText().toString();
                if (!TextUtils.isEmpty(email)) {
                    if (!email.startsWith("http://")) {
                        email = "http://" + email;
                    }
                    Intent intent = new Intent();
                    intent.setAction("android.intent.action.VIEW");
                    Uri content_url = Uri.parse(email.trim());
                    intent.setData(content_url);
                    startActivity(intent);
                }
            }
        }));
        addSubscribe(RxView.clicks(stvFeedback).throttleFirst(Config.WINDOWDURATION, TimeUnit.SECONDS).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                skipActivity(FeedBackActivity.class, null);
            }
        }));
    }

    @Override
    protected void initData() {
        presenter.getAboutMe(new HttpRequest());
    }

    @Override
    protected BasePresenter createPresenter() {
        return presenter = new AboutMePresenter();
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
        // TODO: 2019/5/8 设置获取到的参数信息
        if (null != data) {
            stvVersion.setText("Version " + AppUtils.getAppVersionName());
            AboutMe aboutMe = (AboutMe) data;
            stvLogo.setUrlImage(aboutMe.getLogoUrl());
            tvAppName.setText(aboutMe.getAppName());
            tvAppDescribe.setText(aboutMe.getAppDescrible());
            tvTelNum.setText(aboutMe.getTelNum());
            tvEmailAddress.setText(aboutMe.getEmail());
            tvWebSiteAddress.setText(aboutMe.getWebSite());
        }
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public LifecycleTransformer bindViewToLifecycle() {
        return this.bindToLifecycle();
    }
}
