package com.westar.module_login.ui;


import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatTextView;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.jakewharton.rxbinding2.view.RxView;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;
import com.westar.Config;
import com.westar.library_base.base.BaseActivity;
import com.westar.library_base.common.ArouterPath;
import com.westar.module_login.R;
import com.westar.module_login.been.User;
import com.westar.module_login.mvp.presenter.LoginPresenter;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import io.reactivex.functions.Consumer;
import io.realm.Realm;

/**
 * Created by ZWP on 2019/3/28 17:04.
 * 描述：登录页面
 */
@Route(path = ArouterPath.MODULE_LOGIN_LOGIN_ACTIVITY)
public class LoginActivity extends BaseActivity {


    TextInputEditText etAccount;
    TextInputLayout textInputLayoutAccount;
    TextInputEditText etPassword;
    TextInputLayout textInputLayoutPassword;
    Button btnLogin;
    AppCompatTextView tvRegister;

    LoginPresenter presenter;

    @Override
    protected void initPresenter() {
        presenter = new LoginPresenter();
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        etAccount = findViewById(R.id.et_account);
        textInputLayoutAccount = findViewById(R.id.textInputLayout_account);
        etPassword = findViewById(R.id.et_password);
        textInputLayoutPassword = findViewById(R.id.textInputLayout_password);
        btnLogin = findViewById(R.id.btn_login);
        tvRegister = findViewById(R.id.tv_register);


        //登录
        addSubscribe(RxView.clicks(btnLogin)
                .throttleFirst(Config.windowDuration, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        //显示登录加载弹窗
                        showLoadingDialog();

                        Realm realm = Realm.getDefaultInstance();
                        realm.beginTransaction();//开启事务

                        User user = realm.where(User.class).equalTo("userName", etAccount.getText().toString()).findFirst();
                        if (user != null && etPassword.getText().toString().equals(user.getPassword())) {
//                            //验证通过 登录成功

                            new QMUITipDialog.Builder(mContext)
                                    .setTipWord("登录成功!")
                                    .setIconType(QMUITipDialog.Builder.ICON_TYPE_SUCCESS)
                                    .create(false)
                                    .show();

                            ARouter.getInstance()
                                    .build(ArouterPath.APP_HOMEGROUP_ACTIVITY)
                                    .withSerializable("user", user)
                                    .navigation();
                        } else {
                            ToastUtils.showShort("登录失败！");
                        }
                        realm.commitTransaction();
                        hideLoadingDialog();
                    }
                }));

        //注册
        addSubscribe(RxView.clicks(tvRegister)
                .throttleFirst(Config.windowDuration, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        //检查
                        if (checkLoginInfo()) {
                            Realm realm = Realm.getDefaultInstance();
                            realm.beginTransaction();//开启事务

                            User hasUser = realm.where(User.class)
                                    .equalTo("userName", etAccount.getText().toString())
                                    .findFirst();
                            if (hasUser == null) {
                                //创建一个数据库对象
                                User user = realm.createObject(User.class, UUID.randomUUID().toString());
                                user.setUserName(etAccount.getText().toString())
                                        .setPassword(etPassword.getText().toString())
                                        .setAge("22")
                                        .setGender("男")
                                        .setFraction("88")
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
    }

    @Override
    protected void initData() {

    }

    public boolean checkLoginInfo() {
        if (StringUtils.isEmpty(etAccount.getText())) {
            ToastUtils.showShort("账号不能为空！");
            return false;
        }
        if (StringUtils.isEmpty(etPassword.getText())) {
            ToastUtils.showShort("密码不能为空！");
            return false;
        }

        return true;
    }

}
