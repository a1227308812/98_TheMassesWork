package com.westar.masseswork_98.ui.activity;

import android.widget.RelativeLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jakewharton.rxbinding2.view.RxView;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.westar.Config;
import com.westar.library_base.base.BasePresenter;
import com.westar.library_base.base.ToolbarActivity;
import com.westar.library_base.common.ArouterPath;
import com.westar.masseswork_98.R;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.functions.Consumer;

/**
 * Created by ZWP on 2019/5/5 10:58.
 * 描述：设置界面
 */
@Route(path = ArouterPath.SETTING_ACTIVITY)
public class SettingActivity extends ToolbarActivity {

    @BindView(R.id.rl_personal_information)
    RelativeLayout rlPersonalInformation;
    @BindView(R.id.rl_security_of_accounts)
    RelativeLayout rlSecurityOfAccounts;
    @BindView(R.id.rl_real_name_authentication)
    RelativeLayout rlRealNameAuthentication;
    @BindView(R.id.rl_about_me)
    RelativeLayout rlAboutMe;

    @Override
    public String setBarTitle() {
        return "设置";
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_setting;
    }

    @Override
    protected void findId() {

    }

    @Override
    protected void initView() {
        addSubscribe(RxView.clicks(rlPersonalInformation).throttleFirst(Config.WINDOWDURATION, TimeUnit.SECONDS).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                skipActivity(PersonalInformationActivity.class, null);
            }
        }));
        addSubscribe(RxView.clicks(rlSecurityOfAccounts).throttleFirst(Config.WINDOWDURATION, TimeUnit.SECONDS).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                skipActivity(securityOfAccountActivity.class, null);
            }
        }));
        addSubscribe(RxView.clicks(rlRealNameAuthentication).throttleFirst(Config.WINDOWDURATION, TimeUnit.SECONDS).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                skipActivity(RealNameAuthenticationActivity.class, null);
            }
        }));
        addSubscribe(RxView.clicks(rlAboutMe).throttleFirst(Config.WINDOWDURATION, TimeUnit.SECONDS).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                skipActivity(AboutMeActivity.class, null);
            }
        }));
    }

    @Override
    protected void initData() {

    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
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
        return this.bindToLifecycle();
    }

}
