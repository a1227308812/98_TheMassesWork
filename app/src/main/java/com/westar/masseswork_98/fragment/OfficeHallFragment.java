package com.westar.masseswork_98.fragment;

import android.content.Context;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.westar.library_base.base.BaseMvpFragment;
import com.westar.library_base.base.BasePresenter;
import com.westar.masseswork_98.R;

/**
 * Created by ZWP on 2019/4/8 20:14.
 * 描述：
 */
public class OfficeHallFragment extends BaseMvpFragment {
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
        return R.layout.fragment_office_hall;
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
}
