package com.westar.masseswork_98.fragment;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.coorchice.library.SuperTextView;
import com.jakewharton.rxbinding2.view.RxView;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.westar.Config;
import com.westar.library_base.base.BaseFragment;
import com.westar.library_base.base.BasePresenter;
import com.westar.library_base.common.ArouterPath;
import com.westar.masseswork_98.R;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.functions.Consumer;

/**
 * Created by ZWP on 2019/4/29 15:19.
 * 描述：未登录侧边fragment
 */
public class NotLoginSolideFragment extends BaseFragment {
    @BindView(R.id.stv_user_photo)
    SuperTextView stvUserPhoto;
    @BindView(R.id.ll_gywm)
    LinearLayout llGywm;
    @BindView(R.id.btn_login)
    SuperTextView btnLogin;
    @BindView(R.id.tv_tip)
    TextView tvTip;

    @Override
    protected void lazyLoadShow(boolean isLoad) {

    }

    @Override
    protected void lazyLoadHide(boolean isLoad) {

    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_slide_not_login;
    }

    @Override
    protected void initView() {
        addSubscribe(RxView.clicks(btnLogin)
                .throttleFirst(Config.WINDOWDURATION, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        ARouter.getInstance().build(ArouterPath.LOGIN_ACTIVITY).navigation();
                    }
                }));
        addSubscribe(RxView.clicks(llGywm)
                .throttleFirst(Config.WINDOWDURATION, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        ARouter.getInstance().build(ArouterPath.ABOUTME_ACTIVITY).navigation();
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
    public Context getBaseContext() {
        return mContext;
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
