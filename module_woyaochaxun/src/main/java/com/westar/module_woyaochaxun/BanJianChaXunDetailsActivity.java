package com.westar.module_woyaochaxun;

import android.content.Context;
import android.view.View;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.westar.library_base.base.BasePresenter;
import com.westar.library_base.base.ToolbarActivity;

/**
 * 办件详情界面
 * Created by zb on 2019/4/11.
 */

public class BanJianChaXunDetailsActivity extends ToolbarActivity {



    @Override
    public String setBarTitle() {
            return "办件详情";
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_ban_jian_cha_xun_details;
    }

    @Override
    protected void findId() {

    }

    @Override
    protected void initView() {

        setListener();
    }

    private void setListener() {

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
    public Context getBaseContext() {
        return null;
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
        return null;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }
}
