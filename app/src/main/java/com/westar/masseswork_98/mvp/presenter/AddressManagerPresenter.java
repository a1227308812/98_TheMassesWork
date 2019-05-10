package com.westar.masseswork_98.mvp.presenter;

import com.westar.library_base.base.BasePresenter;
import com.westar.library_base.http.ObserverManager;
import com.westar.library_base.http.been.HttpRequest;
import com.westar.library_base.http.been.HttpResult;
import com.westar.library_base.rxjava.RxScheduler;
import com.westar.masseswork_98.been.AddressInfo;
import com.westar.masseswork_98.mvp.contract.AddressManagerContract;
import com.westar.masseswork_98.mvp.modle.AddressManagerModle;

import java.util.List;

/**
 * Created by ZWP on 2019/5/9 16:02.
 * 描述：
 */
public class AddressManagerPresenter extends BasePresenter<AddressManagerContract.View> implements AddressManagerContract.Presenter {
    AddressManagerModle modle;

    public AddressManagerPresenter() {
        modle = new AddressManagerModle();
    }

    @Override
    public void getAddressList(HttpRequest httpRequest) {
        if (!isViewAttached()) return;
        mView.showLoading();
        modle.getAddressList(httpRequest)
                .compose(mView.bindViewToLifecycle())
                .compose(RxScheduler.rxObservableSchedulerHelper())
                .subscribe(new ObserverManager<List<AddressInfo>>(mView.getBaseContext()) {

                    @Override
                    protected void onOther(HttpResult<List<AddressInfo>> httpResult) {

                    }

                    @Override
                    protected void onSuccess(List<AddressInfo> data) {
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
