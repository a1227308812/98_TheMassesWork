package com.westar.masseswork_98.mvp.presenter;

import com.westar.library_base.base.BasePresenter;
import com.westar.library_base.http.ObserverManager;
import com.westar.library_base.http.been.HttpRequest;
import com.westar.library_base.http.been.HttpResult;
import com.westar.library_base.rxjava.RxScheduler;
import com.westar.masseswork_98.been.MeCardInfo;
import com.westar.masseswork_98.mvp.contract.CardListContract;
import com.westar.masseswork_98.mvp.modle.CardListModle;

import java.util.List;

/**
 * Created by ZWP on 2019/5/9 11:09.
 * 描述：
 */
public class CardListPresenter extends BasePresenter<CardListContract.View> implements CardListContract.Presenter {
    CardListModle modle;

    public CardListPresenter() {
        modle = new CardListModle();
    }

    @Override
    public void cardList(HttpRequest httpRequest) {
        if (!isViewAttached()) return;
        mView.showLoading();
        modle.cardList(httpRequest)
                .compose(mView.bindViewToLifecycle())
                .compose(RxScheduler.rxObservableSchedulerHelper())
                .subscribe(new ObserverManager<List<MeCardInfo>>(mView.getBaseContext()) {
                    @Override
                    protected void onOther(HttpResult httpResult) {

                    }

                    @Override
                    protected void onSuccess(List<MeCardInfo> data) {
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
