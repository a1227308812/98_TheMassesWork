package com.westar.masseswork_98.ui.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.widget.LinearLayout;

import com.alibaba.android.arouter.launcher.ARouter;
import com.coorchice.library.SuperTextView;
import com.jakewharton.rxbinding2.view.RxView;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.westar.Config;
import com.westar.library_base.base.BaseActivity;
import com.westar.library_base.base.BasePresenter;
import com.westar.library_base.base.ToolbarActivity;
import com.westar.library_base.common.ArouterPath;
import com.westar.library_base.view.TopBarLayout;
import com.westar.library_base.view.shadowView.ShadowHelper;
import com.westar.library_base.view.shadowView.ShadowProperty;
import com.westar.masseswork_98.R;
import com.westar.module_login.ui.ConfirmPersonalInformationActivity;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.functions.Consumer;

/**
 * Created by ZWP on 2019/5/7 19:55.
 * 描述：实名认证
 */
public class RealNameAuthenticationActivity extends BaseActivity {

    @BindView(R.id.topbar)
    TopBarLayout topbar;
    @BindView(R.id.ll_content)
    LinearLayout llContent;
    @BindView(R.id.authenticate_now)
    SuperTextView authenticateNow;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_real_name_authentication;
    }

    @Override
    protected void findId() {

    }

    @Override
    protected void initView() {
        topbar.setTitle("实名认证").setTextColor(ContextCompat.getColor(mContext, R.color.white));

        //设置指定控件的阴影
        ShadowHelper.bindView(llContent, new ShadowProperty()
                .setShadowRadius(5)
                .setShadowColor(ContextCompat.getColor(mContext, com.westar.module_login.R.color.shadow_color))
                .setLayoutBg(ContextCompat.getDrawable(mContext, R.drawable.png_smrzs))
                .setRoundwWidth(10));

        addSubscribe(RxView.clicks(authenticateNow).throttleFirst(Config.WINDOWDURATION, TimeUnit.SECONDS).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                ARouter.getInstance().build(ArouterPath.MODULE_LOGIN_IDCARD_CONFIRM_ACTIVITY).navigation();
            }
        }));
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
        return this.bindToLifecycle();
    }

}
