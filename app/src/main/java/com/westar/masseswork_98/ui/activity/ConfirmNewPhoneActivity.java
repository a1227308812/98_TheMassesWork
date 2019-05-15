package com.westar.masseswork_98.ui.activity;


import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;

import com.blankj.utilcode.util.ToastUtils;
import com.coorchice.library.SuperTextView;
import com.jakewharton.rxbinding2.view.RxView;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.westar.Config;
import com.westar.library_base.base.BasePresenter;
import com.westar.library_base.base.ToolbarActivity;
import com.westar.library_base.http.been.HttpRequest;
import com.westar.masseswork_98.R;
import com.westar.masseswork_98.mvp.contract.ConfirmNewPhoneContract;
import com.westar.masseswork_98.mvp.presenter.ConfirmNewPhonePresenter;
import com.westar.library_base.view.CustomTextInputEditText;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.functions.Consumer;

/**
 * Created by ZWP on 2019/5/7 17:16.
 * 描述：确认新手机号界面
 */
public class ConfirmNewPhoneActivity extends ToolbarActivity implements ConfirmNewPhoneContract.View {

    @BindView(R.id.et_phone)
    CustomTextInputEditText etPhone;
    @BindView(R.id.et_yzm)
    TextInputEditText etYzm;
    @BindView(R.id.btn_yzm)
    AppCompatTextView btnYzm;
    @BindView(R.id.stv_confirm)
    SuperTextView stvConfirm;

    ConfirmNewPhonePresenter presenter;

    @Override
    public String setBarTitle() {
        return "更改手机号";
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_confirm_new_phone;
    }

    @Override
    protected void findId() {

    }

    @Override
    protected void initView() {
        addSubscribe(RxView.clicks(btnYzm).throttleFirst(Config.WINDOWDURATION, TimeUnit.SECONDS).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                // TODO: 2019/5/7 获取验证码
                presenter.getYzm(new HttpRequest());
            }
        }));

        addSubscribe(RxView.clicks(stvConfirm).throttleFirst(Config.WINDOWDURATION, TimeUnit.SECONDS).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                if (TextUtils.isEmpty(etYzm.getText())) {
                    ToastUtils.showShort("验证码不能为空！");
                    return;
                }
                presenter.updataNewPhone(new HttpRequest());
            }
        }));
    }

    @Override
    protected void initData() {

    }

    @Override
    protected BasePresenter createPresenter() {
        return presenter = new ConfirmNewPhonePresenter();
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
        ToastUtils.showShort("更新手机号成功");
        setResultOk(null);
        finish();
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public LifecycleTransformer bindViewToLifecycle() {
        return this.bindToLifecycle();
    }
}
