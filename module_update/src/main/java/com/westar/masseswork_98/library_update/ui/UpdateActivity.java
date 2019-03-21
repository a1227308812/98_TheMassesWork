package com.westar.masseswork_98.library_update.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.westar.masseswork_98.library_base.base.BaseMvpActivity;
import com.westar.masseswork_98.library_base.http.been.HttpRequest;
import com.westar.masseswork_98.library_base.utils.LLog;
import com.westar.masseswork_98.library_update.R;
import com.westar.masseswork_98.library_update.mvp.contract.UpdateContract;
import com.westar.masseswork_98.library_update.mvp.presenter.UpdatePresenter;

import java.util.concurrent.TimeUnit;

import io.reactivex.functions.Consumer;

public class UpdateActivity extends BaseMvpActivity<UpdatePresenter> implements UpdateContract.View {

    UpdatePresenter updatePresenter;
    TextView textView;

    @Override
    protected void initView() {
        textView = findViewById(R.id.textView);
    }

    @Override
    protected void initData() {
        addSubscribe(RxView.
                clicks(textView)
                .throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        updatePresenter.update(new HttpRequest<String>().setData("sp"));
                    }
                }));
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_update;
    }

    @Override
    protected UpdatePresenter createPresenter() {
        updatePresenter = new UpdatePresenter();
        return updatePresenter;
    }

    @Override
    public void showLoading() {
        showLoadingDialog();
    }

    @Override
    public void hideLoading() {
        hideLoadingDialog();
    }

    @Override
    public void onOther(Object data) {
        LLog.e("", "onOther");
    }

    @Override
    public void onSuccess(Object data) {
        LLog.e("", "onSuccess");
        textView.setText(data.toString());
    }

    @Override
    public void onError(Throwable e) {
        LLog.e("", "onError");
    }

    @Override
    public LifecycleTransformer bindViewToLifecycle() {
        return this.bindToLifecycle();
    }
}
