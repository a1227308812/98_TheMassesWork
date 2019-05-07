package com.westar.masseswork_98.ui.activity;

import android.support.constraint.ConstraintLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.jakewharton.rxbinding2.view.RxView;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.westar.Config;
import com.westar.library_base.base.BasePresenter;
import com.westar.library_base.base.ToolbarActivity;
import com.westar.masseswork_98.R;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.functions.Consumer;

/**
 * Created by ZWP on 2019/5/7 13:21.
 * 描述：账号安全界面
 */
public class securityOfAccountActivity extends ToolbarActivity {


    @BindView(R.id.tv_phone_num)
    TextView tvPhoneNum;
    @BindView(R.id.cl_phone)
    ConstraintLayout clPhone;
    @BindView(R.id.cl_other_accoutn)
    ConstraintLayout clOtherAccoutn;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_security_of_account;
    }

    @Override
    protected void findId() {

    }

    @Override
    protected void initView() {
        tvPhoneNum.setText("18322446655");

        addSubscribe(RxView.clicks(clPhone).throttleFirst(Config.WINDOWDURATION, TimeUnit.SECONDS).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                skipActivityForResult(ChangePhoneActivity.class, 111, null);
            }
        }));
        addSubscribe(RxView.clicks(clOtherAccoutn).throttleFirst(Config.WINDOWDURATION, TimeUnit.SECONDS).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                ToastUtils.showShort("功能暂未开放！");
            }
        }));
    }

    @Override
    protected void initData() {

    }

    @Override
    public String setBarTitle() {
        return "账号安全";
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
