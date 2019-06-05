package com.westar.module_login.ui;

import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.coorchice.library.SuperTextView;
import com.jakewharton.rxbinding2.view.RxView;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.westar.Config;
import com.westar.library_base.base.BasePresenter;
import com.westar.library_base.base.ToolbarActivity;
import com.westar.library_base.common.ArouterPath;
import com.westar.library_base.common.Common;
import com.westar.library_base.eventbus.EventBusUtlis;
import com.westar.library_base.eventbus.SolideTypeEvent;
import com.westar.module_login.R;

import java.util.concurrent.TimeUnit;

import io.reactivex.functions.Consumer;

/**
 * Created by ZWP on 2019/4/11 16:43.
 * 描述：确认个人信息界面
 */
public class ConfirmPersonalInformationActivity extends ToolbarActivity {
    TextView tvName;
    TextView tvIdCard;
    SuperTextView stvConfirmPersonal;
    TextView tvNotConfirm;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_confirm_personal_information;
    }

    @Override
    protected void findId() {
        tvName = findViewById(R.id.tv_name);
        tvIdCard = findViewById(R.id.tv_id_card);
        stvConfirmPersonal = findViewById(R.id.stv_confirm_personal);
        tvNotConfirm = findViewById(R.id.tv_not_confirm);
    }

    @Override
    protected void initView() {

        addSubscribe(RxView.clicks(stvConfirmPersonal).throttleFirst(Config.WINDOWDURATION, TimeUnit.SECONDS).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                skipActivity(IDCardConfirmActivity.class, null);
            }
        }));
        addSubscribe(RxView.clicks(tvNotConfirm).throttleFirst(Config.WINDOWDURATION, TimeUnit.SECONDS).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                EventBusUtlis.sendStickyEvent(new SolideTypeEvent(Common.NOT_AUTHENTICATION));
                ARouter.getInstance()
                        .build(ArouterPath.HOMEGROUP_ACTIVITY)
                        .navigation();
            }
        }));
    }

    @Override
    protected void initData() {
    }

    public String setBarTitle() {
        return "确认个人信息";
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
