package com.westar.module_login.ui;


import com.blankj.utilcode.util.ToastUtils;
import com.coorchice.library.SuperTextView;
import com.jakewharton.rxbinding2.view.RxView;
import com.westar.Config;
import com.westar.library_base.base.BaseActivity;
import com.westar.module_login.R;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.functions.Consumer;

/**
 * Created by ZWP on 2019/4/9 10:40.
 * 描述：登录方式选择
 */
public class LoginTypeSelectActivity extends BaseActivity {


    SuperTextView stvWeixin;
    SuperTextView stvZhifubao;
    SuperTextView stvMsg;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_login_type_select;
    }

    @Override
    protected void initView() {
        findId();

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

    public void findId() {
        stvWeixin = findViewById(R.id.stv_weixin);
        stvZhifubao = findViewById(R.id.stv_zhifubao);
        stvMsg = findViewById(R.id.stv_msg);
    }

    @Override
    protected void initData() {

    }

}
