package com.westar.masseswork_98.mvp.presenter;

import com.westar.library_base.base.BasePresenter;
import com.westar.library_base.http.ObserverManager;
import com.westar.library_base.http.been.HttpRequest;
import com.westar.library_base.http.been.HttpResult;
import com.westar.library_base.rxjava.RxScheduler;
import com.westar.masseswork_98.been.GlobalSearchHistory;
import com.westar.masseswork_98.been.GlobalSearchHot;
import com.westar.masseswork_98.been.SearchResult;
import com.westar.masseswork_98.mvp.contract.GlobalSearchContract;
import com.westar.masseswork_98.mvp.modle.GlobalSearchModle;

import java.util.List;

/**
 * Created by ZWP on 2019/4/26 16:07.
 * 描述：
 */
public class GlobalSearchPresenter extends BasePresenter<GlobalSearchContract.View> implements GlobalSearchContract.Presneter {
    GlobalSearchModle modle;

    public GlobalSearchPresenter() {
        this.modle = new GlobalSearchModle();
    }

    @Override
    public void searchResult(HttpRequest httpRequest) {
        if (!isViewAttached()) return;

        mView.showLoading();
        modle.searchResult(httpRequest)
                .compose(mView.bindViewToLifecycle())
                .compose(RxScheduler.rxObservableSchedulerHelper())
                .subscribe(new ObserverManager<List<GlobalSearchHot>>(mView.getBaseContext()) {

                    @Override
                    protected void onOther(HttpResult<List<GlobalSearchHot>> httpResult) {

                    }

                    @Override
                    protected void onSuccess(List<GlobalSearchHot> data) {
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
