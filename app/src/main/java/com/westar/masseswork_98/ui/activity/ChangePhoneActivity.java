package com.westar.masseswork_98.ui.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
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
import com.westar.library_base.http.been.HttpResult;
import com.westar.masseswork_98.R;
import com.westar.masseswork_98.mvp.contract.ChangePhoneContract;
import com.westar.masseswork_98.mvp.presenter.ChangePhonePresenter;
import com.westar.masseswork_98.ui.custom.CustomTextInputEditText;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.functions.Consumer;

/**
 * Created by ZWP on 2019/5/7 14:19.
 * 描述：修改手机号码界面
 */
public class ChangePhoneActivity extends ToolbarActivity implements ChangePhoneContract.View {


    @BindView(R.id.et_phone)
    CustomTextInputEditText etPhone;
    @BindView(R.id.et_yzm)
    TextInputEditText etYzm;
    @BindView(R.id.btn_yzm)
    AppCompatTextView btnYzm;
    @BindView(R.id.stv_change_phone_num)
    SuperTextView stvChangePhoneNum;

    ChangePhonePresenter presenter;

    @Override
    protected BasePresenter createPresenter() {
        return presenter = new ChangePhonePresenter();
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_change_phone;
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

        addSubscribe(RxView.clicks(stvChangePhoneNum).throttleFirst(Config.WINDOWDURATION, TimeUnit.SECONDS).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                if (TextUtils.isEmpty(etYzm.getText())) {
                    ToastUtils.showShort("验证码不能为空！");
                    return;
                }
                presenter.checkOldPhoneNum(new HttpRequest());
            }
        }));
    }

    @Override
    protected void initData() {

    }

    @Override
    public String setBarTitle() {
        return "更改手机号";
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


    @Override
    public void checkOldPhoneNumResult(String result) {
        ToastUtils.showShort("验证通过");
        skipActivityForResult(ConfirmNewPhoneActivity.class, 111, null);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            setResultOk(null);
            finish();
        }
    }
}
