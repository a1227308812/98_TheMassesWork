package com.westar.module_woyaobanshi.ui.activity;

import android.os.Bundle;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.westar.library_base.base.BaseActivity;
import com.westar.library_base.base.BasePresenter;
import com.westar.library_base.base.ToolbarActivity;
import com.westar.module_woyaobanshi.R;

/**
 * Created by ZWP on 2019/6/5 14:35.
 * 描述：材料详情页面
 */
public class MateriaDetailActivity extends ToolbarActivity {


    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_materia_detail;
    }

    @Override
    protected void findId() {

    }

    @Override
    protected void initView() {

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
    public String setBarTitle() {
        return "申请材料详情";
    }
}
