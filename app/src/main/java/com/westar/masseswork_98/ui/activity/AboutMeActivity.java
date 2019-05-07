package com.westar.masseswork_98.ui.activity;


import com.trello.rxlifecycle2.LifecycleTransformer;
import com.westar.library_base.base.BasePresenter;
import com.westar.library_base.base.ToolbarActivity;
import com.westar.masseswork_98.R;

/**
 * Created by ZWP on 2019/5/7 20:45.
 * 描述：
 */
public class AboutMeActivity extends ToolbarActivity {

    @Override
    public String setBarTitle() {
        return "关于我们";
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_about_me;
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
