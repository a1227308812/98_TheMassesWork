package com.westar.masseswork_98.mvp.presenter;

import com.westar.library_base.base.BasePresenter;
import com.westar.library_base.http.ObserverManager;
import com.westar.library_base.http.been.HttpRequest;
import com.westar.library_base.http.been.HttpResult;
import com.westar.library_base.rxjava.RxScheduler;
import com.westar.masseswork_98.been.NewsInformationTabs;
import com.westar.masseswork_98.mvp.contract.NewsInformationContract;
import com.westar.masseswork_98.mvp.modle.NewsInformationModle;

import java.util.List;

/**
 * Created by ZWP on 2019/4/24 20:33.
 * 描述：
 */
public class NewsInformationPresenter extends BasePresenter<NewsInformationContract.View> implements NewsInformationContract.Presenter {
    NewsInformationModle modle;

    public NewsInformationPresenter() {
        modle = new NewsInformationModle();
    }

    @Override
    public void getTabData(HttpRequest httpRequest) {
        if (!isViewAttached()) return;

        mView.showLoading();
        modle.getTabData(httpRequest)
                .compose(mView.bindViewToLifecycle())
                .compose(RxScheduler.rxObservableSchedulerHelper())
                .subscribe(new ObserverManager<List<NewsInformationTabs>>(mView.getBaseContext()) {
                    @Override
                    protected void onOther(HttpResult<List<NewsInformationTabs>> httpResult) {

                    }

                    @Override
                    protected void onSuccess(List<NewsInformationTabs> data) {
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
