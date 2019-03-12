package com.westar.masseswork_98;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.westar.masseswork_98.library_base.base.BaseMvpActivity;
import com.westar.masseswork_98.library_base.base.BaseView;
import com.westar.masseswork_98.mvp.presenter.UpdatePresenter;

public class MainActivity extends BaseMvpActivity<UpdatePresenter> implements BaseView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getLayoutID() {
        return 0;
    }

    @Override
    protected UpdatePresenter createPresenter() {
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
