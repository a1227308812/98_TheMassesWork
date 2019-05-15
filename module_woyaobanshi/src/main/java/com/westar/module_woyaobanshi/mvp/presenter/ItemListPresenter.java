package com.westar.module_woyaobanshi.mvp.presenter;

import com.westar.been.ItemInfo;
import com.westar.library_base.base.BasePresenter;
import com.westar.library_base.http.ObserverManager;
import com.westar.library_base.http.been.HttpRequest;
import com.westar.library_base.http.been.HttpResult;
import com.westar.library_base.rxjava.RxScheduler;
import com.westar.module_woyaobanshi.mvp.contract.ItemListContract;
import com.westar.module_woyaobanshi.mvp.modle.ItemListModle;

import java.util.List;

/**
 * Created by ZWP on 2019/5/15 10:33.
 * 描述：
 */
public class ItemListPresenter extends BasePresenter<ItemListContract.View> implements ItemListContract.Presenter {
    ItemListModle modle;

    public ItemListPresenter() {
        modle = new ItemListModle();
    }

    @Override
    public void getItemList(HttpRequest httpRequest) {
        if (!isViewAttached()) return;
        mView.showLoading();
        modle.getItemList(httpRequest)
                .compose(mView.bindViewToLifecycle())
                .compose(RxScheduler.rxObservableSchedulerHelper())
                .subscribe(new ObserverManager<List<ItemInfo>>(mView.getBaseContext()) {

                    @Override
                    protected void onOther(HttpResult<List<ItemInfo>> httpResult) {

                    }

                    @Override
                    protected void onSuccess(List<ItemInfo> data) {
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
