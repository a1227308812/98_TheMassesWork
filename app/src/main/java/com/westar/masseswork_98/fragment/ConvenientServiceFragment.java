package com.westar.masseswork_98.fragment;

import android.content.Context;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.westar.library_base.base.BaseFragment;
import com.westar.library_base.base.BaseMvpFragment;
import com.westar.library_base.base.BasePresenter;
import com.westar.masseswork_98.R;

/**
 * Created by ZWP on 2019/4/8 20:28.
 * 描述：便民服务
 */
public class ConvenientServiceFragment extends BaseFragment {


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

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void lazyLoadShow(boolean isLoad) {

    }

    @Override
    protected void lazyLoadHide(boolean isLoad) {

    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_convenien_service;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }
}
