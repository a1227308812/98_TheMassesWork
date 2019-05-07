package com.westar.masseswork_98.mvp.presenter;

import com.blankj.utilcode.util.ToastUtils;
import com.westar.library_base.base.BasePresenter;
import com.westar.library_base.http.ObserverManager;
import com.westar.library_base.http.been.HttpRequest;
import com.westar.library_base.http.been.HttpResult;
import com.westar.library_base.rxjava.RxScheduler;
import com.westar.masseswork_98.mvp.contract.ChangePhoneContract;
import com.westar.masseswork_98.mvp.contract.ConfirmNewPhoneContract;
import com.westar.masseswork_98.mvp.modle.ChangePhoneModule;
import com.westar.masseswork_98.mvp.modle.ConfirmNewPhoneModule;

/**
 * Created by ZWP on 2019/5/7 17:11.
 * 描述：
 */
public class ConfirmNewPhonePresenter extends BasePresenter<ConfirmNewPhoneContract.View> implements ConfirmNewPhoneContract.Presenter {
    ConfirmNewPhoneModule module;

    public ConfirmNewPhonePresenter() {
        module = new ConfirmNewPhoneModule();
    }

    @Override
    public void getYzm(HttpRequest httpRequest) {
        if (!isViewAttached()) return;
        mView.showLoading();
        module.getYzm(httpRequest)
                .compose(mView.bindViewToLifecycle())
                .compose(RxScheduler.rxObservableSchedulerHelper())
                .subscribe(new ObserverManager<String>(mView.getBaseContext()) {

                    @Override
                    protected void onOther(HttpResult<String> httpResult) {

                    }

                    @Override
                    protected void onSuccess(String data) {
                        ToastUtils.showShort("服务器发送验证码成功");
                    }

                    @Override
                    protected void onFailure(Throwable e) {

                    }

                    @Override
                    protected void onFinish() {
                        mView.hideLoading();
                    }
                });
    }

    @Override
    public void updataNewPhone(HttpRequest httpRequest) {
        if (!isViewAttached()) return;
        mView.showLoading();
        module.updataNewPhone(httpRequest)
                .compose(mView.bindViewToLifecycle())
                .compose(RxScheduler.rxObservableSchedulerHelper())
                .subscribe(new ObserverManager<String>(mView.getBaseContext()) {

                    @Override
                    protected void onOther(HttpResult<String> httpResult) {

                    }

                    @Override
                    protected void onSuccess(String data) {
                        mView.onSuccess(data);
                    }

                    @Override
                    protected void onFailure(Throwable e) {

                    }

                    @Override
                    protected void onFinish() {
                        mView.hideLoading();
                    }
                });
    }
}
