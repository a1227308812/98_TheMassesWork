package com.westar.module_login.ui;


import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.jakewharton.rxbinding2.view.RxView;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.westar.Config;
import com.westar.been.User;
import com.westar.library_base.base.BaseActivity;
import com.westar.library_base.base.BaseApplication;
import com.westar.library_base.base.BasePresenter;
import com.westar.library_base.common.ArouterPath;
import com.westar.library_base.common.Common;
import com.westar.library_base.eventbus.EventBusUtlis;
import com.westar.library_base.eventbus.SolideTypeEvent;
import com.westar.module_login.R;
import com.westar.module_login.mvp.presenter.LoginPresenter;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.functions.Consumer;
import io.realm.Realm;

/**
 * Created by ZWP on 2019/3/28 17:04.
 * 描述：登录页面
 */
@Route(path = ArouterPath.LOGIN_ACTIVITY)
public class LoginActivity extends BaseActivity {


    AppCompatImageView qivLogo;
    AppCompatImageView iv_back;

    AppCompatTextView tvGrdl;
    AppCompatTextView tvFrdl;

    TextInputEditText etYzmAccount;
    TextInputLayout textInputLayoutYzmAccount;
    TextInputEditText etYzm;
    TextInputLayout textInputLayoutYzm;
    AppCompatTextView btnYzm;
    ConstraintLayout clYzmLayout;

    TextInputEditText etPwdAccount;
    TextInputLayout textInputLayoutPwdAccount;
    TextInputEditText etPwdPassword;
    TextInputLayout textInputLayoutPwdPassword;
    ConstraintLayout clPasswordLayout;


    AppCompatTextView tvYzmSubmit;
    AppCompatButton btnLoginSubmit;
    AppCompatTextView tvWjmm;
    AppCompatTextView tvZczh;
    AppCompatImageView ivWxdl;
    AppCompatImageView ivZfbdl;


    LoginPresenter presenter;


    @Override
    protected void initStatusBar() {
        //状态栏透明 字体和图标白色
        QMUIStatusBarHelper.setStatusBarDarkMode(this);
    }

    @Override
    protected void initPresenter() {
        presenter = new LoginPresenter();
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_login;
    }

    @Override
    protected void findId() {
        qivLogo = findViewById(R.id.iv_top);
        iv_back = findViewById(R.id.iv_back);
        tvGrdl = findViewById(R.id.tv_grdl);
        tvFrdl = findViewById(R.id.tv_frdl);


        etYzmAccount = findViewById(R.id.et_yzm_account);
        etYzm = findViewById(R.id.et_yzm);
        btnYzm = findViewById(R.id.btn_yzm);
        textInputLayoutYzmAccount = findViewById(R.id.textInputLayout_yzm_account);
        textInputLayoutYzm = findViewById(R.id.textInputLayout_yzm);


        etPwdAccount = findViewById(R.id.et_pwd_account);
        etPwdPassword = findViewById(R.id.et_pwd_password);
        textInputLayoutPwdAccount = findViewById(R.id.textInputLayout_pwd_account);
        textInputLayoutPwdPassword = findViewById(R.id.textInputLayout_pwd_password);

        clPasswordLayout = findViewById(R.id.cl_password_layout);
        clYzmLayout = findViewById(R.id.cl_yzm_layout);


        tvYzmSubmit = findViewById(R.id.tv_yzm_submit);
        btnLoginSubmit = findViewById(R.id.btn_login_submit);

        tvWjmm = findViewById(R.id.tv_wjmm);
        tvZczh = findViewById(R.id.tv_zczh);

        ivWxdl = findViewById(R.id.iv_wxdl);
        ivZfbdl = findViewById(R.id.iv_zfbdl);

//
    }

    @Override
    protected void initView() {

        //个人登录
        addSubscribe(RxView.clicks(tvGrdl).throttleFirst(Config.WINDOWDURATION, TimeUnit.SECONDS).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                ToastUtils.showShort("个人登录");
            }
        }));

        //法人登录
        addSubscribe(RxView.clicks(tvFrdl).throttleFirst(Config.WINDOWDURATION, TimeUnit.SECONDS).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                ToastUtils.showShort("法人登录");
            }
        }));


        //登录
        addSubscribe(RxView.clicks(btnLoginSubmit)
                .throttleFirst(Config.WINDOWDURATION, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        if (checkLoginInfo()) {
                            //显示登录加载弹窗
                            showLoadingDialog();


                            Realm realm = Realm.getDefaultInstance();
                            realm.beginTransaction();//开启事务

                            String acccount = etPwdAccount.getText().toString();
                            String password = etPwdPassword.getText().toString();
                            if (clYzmLayout.getVisibility() == View.VISIBLE) {
                                acccount = etYzmAccount.getText().toString();
                                password = etYzm.getText().toString();
                            }
                            User user = realm.where(User.class).equalTo("account", acccount).findFirst();
                            if (user != null && password.equals(user.getPassword())) {
//                            //验证通过 登录成功
//                            new QMUITipDialog.Builder(mContext)
//                                    .setTipWord("登录成功!")
//                                    .setIconType(QMUITipDialog.Builder.ICON_TYPE_SUCCESS)
//                                    .create()
//                                    .show();
                                //设置当前登录user
                                BaseApplication.getIns().setUser(user);

                                EventBusUtlis.sendStickyEvent(new SolideTypeEvent(Common.HAD_AUTHENTICATION));
                                skipActivity(ConfirmPersonalInformationActivity.class, null);
                            } else {
                                ToastUtils.showShort("登录失败！");
                            }

                            realm.commitTransaction();
                            hideLoadingDialog();
                        }

                    }
                }));

        //注册
        addSubscribe(RxView.clicks(qivLogo)
                .throttleFirst(Config.WINDOWDURATION, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        //检查
                        if (checkLoginInfo()) {
                            Realm realm = Realm.getDefaultInstance();
                            realm.beginTransaction();//开启事务
                            String acccount = etPwdAccount.getText().toString();
                            String password = etPwdPassword.getText().toString();
                            if (clYzmLayout.getVisibility() == View.VISIBLE) {
                                acccount = etYzmAccount.getText().toString();
                                password = etYzm.getText().toString();
                            }
                            User hasUser = realm.where(User.class)
                                    .equalTo("account", acccount)
                                    .findFirst();
                            if (hasUser == null) {
                                //创建一个数据库对象
                                User user = realm.createObject(User.class, UUID.randomUUID().toString());
                                user.setAccount(acccount)
                                        .setUserName(acccount)
                                        .setPassword(password)
                                        .setAge("22")
                                        .setGender("男")
                                        .setDomicile_address("四川省成都市武侯区科园三路火炬时代B区")
                                        .setPhotoUrl("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=328179059,3377101288&fm=27&gp=0.jpg")
                                        .setBirthday("1990-12-21")
                                        .setFraction("88")
                                        .setNoticeDescribe("您好，您已成功预约2019-01-12日 14：00-15：00的号，请提前做好准备。")
                                        .setCardId("10001");
                                ToastUtils.showShort("注册成功！");
                            } else {
                                ToastUtils.showShort("账号已存在！");
                            }

                            //提交事务
                            realm.commitTransaction();
                        }
                    }
                }));

        //短信验证登录
        addSubscribe(RxView.clicks(tvYzmSubmit).throttleFirst(Config.WINDOWDURATION, TimeUnit.SECONDS).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                if (clPasswordLayout.getVisibility() == View.VISIBLE) {
                    clPasswordLayout.setVisibility(View.GONE);
                    clYzmLayout.setVisibility(View.VISIBLE);
                    tvYzmSubmit.setText("账号密码登录");
                } else {
                    clPasswordLayout.setVisibility(View.VISIBLE);
                    clYzmLayout.setVisibility(View.GONE);
                    tvYzmSubmit.setText("短信验证码登录");
                }
            }
        }));
        //忘记密码
        addSubscribe(RxView.clicks(tvWjmm).throttleFirst(Config.WINDOWDURATION, TimeUnit.SECONDS).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                ToastUtils.showShort("忘记密码");
            }
        }));
        //注册账号
        addSubscribe(RxView.clicks(tvZczh).throttleFirst(Config.WINDOWDURATION, TimeUnit.SECONDS).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                ToastUtils.showShort("注册账号");
            }
        }));
        //微信登录
        addSubscribe(RxView.clicks(ivWxdl).throttleFirst(Config.WINDOWDURATION, TimeUnit.SECONDS).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                ToastUtils.showShort("微信登录");
            }
        }));
        //支付宝登录
        addSubscribe(RxView.clicks(ivZfbdl).throttleFirst(Config.WINDOWDURATION, TimeUnit.SECONDS).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                ToastUtils.showShort("支付宝登录");
            }
        }));
        //获取验证码
        addSubscribe(RxView.clicks(btnYzm).throttleFirst(Config.WINDOWDURATION, TimeUnit.SECONDS).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                ToastUtils.showShort("获取验证码");
            }
        }));

        //开启软键盘回车提交功能
        etYzm.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    btnLoginSubmit.performClick();
                    return true;
                }
                return false;
            }
        });
        //开启软键盘回车提交功能
        etPwdPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    btnLoginSubmit.performClick();
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    protected void initData() {

    }

    public boolean checkLoginInfo() {
        //判断是账号密码登录还是短信验证码登录
        if (clPasswordLayout.getVisibility() == View.VISIBLE) {
            if (StringUtils.isEmpty(etPwdAccount.getText())) {
                ToastUtils.showShort(R.string.account_is_empty);
                return false;
            }
            if (StringUtils.isEmpty(etPwdPassword.getText())) {
                ToastUtils.showShort(R.string.account_is_password);
                return false;
            }
        } else {
            if (StringUtils.isEmpty(etYzmAccount.getText())) {
                ToastUtils.showShort(R.string.phone_num_is_empty);
                return false;
            }
            if (RegexUtils.isMobileSimple(etYzmAccount.getText())) {
                ToastUtils.showShort(R.string.match_phone);
                return false;
            }
            if (StringUtils.isEmpty(etYzm.getText())) {
                ToastUtils.showShort(R.string.match_yzm);
                return false;
            }
        }

        return true;
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
