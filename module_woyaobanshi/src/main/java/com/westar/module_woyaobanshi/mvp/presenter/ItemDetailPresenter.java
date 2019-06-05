package com.westar.module_woyaobanshi.mvp.presenter;

import com.westar.library_base.base.BasePresenter;
import com.westar.library_base.http.ObserverManager;
import com.westar.library_base.http.been.HttpRequest;
import com.westar.library_base.http.been.HttpResult;
import com.westar.library_base.rxjava.RxScheduler;
import com.westar.module_woyaobanshi.mvp.contract.ItemDetailContract;
import com.westar.module_woyaobanshi.mvp.modle.ItemDetailModle;

/**
 * Created by ZWP on 2019/5/30 14:22.
 * 描述：
 */
public class ItemDetailPresenter extends BasePresenter<ItemDetailContract.View> implements ItemDetailContract.Presenter {
    ItemDetailModle modle;

    public ItemDetailPresenter() {
        modle = new ItemDetailModle();
    }

    @Override
    public void getData(HttpRequest httpRequest) {
        if (!isViewAttached()) return;
        mView.showLoading();
        modle.getData(httpRequest)
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
