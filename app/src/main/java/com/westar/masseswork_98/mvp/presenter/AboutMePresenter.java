package com.westar.masseswork_98.mvp.presenter;

import com.westar.library_base.base.BasePresenter;
import com.westar.library_base.http.ObserverManager;
import com.westar.library_base.http.been.HttpRequest;
import com.westar.library_base.http.been.HttpResult;
import com.westar.library_base.rxjava.RxScheduler;
import com.westar.masseswork_98.been.AboutMe;
import com.westar.masseswork_98.mvp.contract.AboutMeContract;
import com.westar.masseswork_98.mvp.modle.AboutMeModule;

/**
 * Created by ZWP on 2019/5/8 10:11.
 * 描述：
 */
public class AboutMePresenter extends BasePresenter<AboutMeContract.View> implements AboutMeContract.Presenter {
    AboutMeModule module;

    public AboutMePresenter() {
        module = new AboutMeModule();
    }

    @Override
    public void getAboutMe(HttpRequest httpRequest) {
        if (!isViewAttached()) return;

        mView.showLoading();
        module.getAboutMe(httpRequest)
                .compose(mView.bindViewToLifecycle())
                .compose(RxScheduler.rxObservableSchedulerHelper())
                .subscribe(new ObserverManager<AboutMe>(mView.getBaseContext()) {

                    @Override
                    protected void onOther(HttpResult<AboutMe> httpResult) {

                    }

                    @Override
                    protected void onSuccess(AboutMe data) {
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
