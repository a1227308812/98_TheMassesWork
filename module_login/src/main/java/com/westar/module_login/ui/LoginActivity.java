package com.westar.module_login.ui;


import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.RegexUtils;
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
    TextInputEditText etYzm;
    AppCompatButton btnLogin;
    CheckedTextView cbZcxy;

    LoginPresenter presenter;

    ImageView ivLogo;

    @Override
    protected void initPresenter() {
        presenter = new LoginPresenter();
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_login;
    }

    @Override
    protected void findId() {
        ivLogo = findViewById(R.id.iv_top);
        etAccount = findViewById(R.id.et_account);
        etYzm = findViewById(R.id.et_yzm);
        btnLogin = findViewById(R.id.btn_login);
        cbZcxy = findViewById(R.id.cb_zcxy);
    }

    @Override
    protected void initView() {

        cbZcxy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cbZcxy.setChecked(!cbZcxy.isChecked());
            }
        });

//        mSpanTouchFixTextView1.setMovementMethodDefault();
//        mSpanTouchFixTextView1.setText(generateSp(getResources().getString(R.string.span_touch_fix_1)));


        //登录
        addSubscribe(RxView.clicks(btnLogin)
                .throttleFirst(Config.WINDOWDURATION, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        //显示登录加载弹窗
                        showLoadingDialog();

                        Realm realm = Realm.getDefaultInstance();
                        realm.beginTransaction();//开启事务

                        User user = realm.where(User.class).equalTo("userName", etAccount.getText().toString()).findFirst();
                        if (user != null && etYzm.getText().toString().equals(user.getPassword())) {
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
        addSubscribe(RxView.clicks(ivLogo)
                .throttleFirst(Config.WINDOWDURATION, TimeUnit.SECONDS)
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
                                        .setPassword(etYzm.getText().toString())
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
            ToastUtils.showShort(R.string.phone_num_is_empty);
            return false;
        }
        if (RegexUtils.isMobileSimple(etAccount.getText())) {
            ToastUtils.showShort(R.string.match_phone);
            return false;
        }
        if (StringUtils.isEmpty(etYzm.getText())) {
            ToastUtils.showShort(R.string.match_yzm);
            return false;
        }
        return true;
    }

}
