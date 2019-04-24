package com.westar.masseswork_98.mvp.presenter;

import com.westar.library_base.base.BasePresenter;
import com.westar.library_base.http.ObserverManager;
import com.westar.library_base.http.been.HttpRequest;
import com.westar.library_base.http.been.HttpResult;
import com.westar.library_base.rxjava.RxScheduler;
import com.westar.masseswork_98.been.AccountInfo;
import com.westar.masseswork_98.mvp.contract.OfficeHallContract;
import com.westar.masseswork_98.mvp.modle.OfficeHallModle;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by ZWP on 2019/4/24 13:44.
 * 描述：办事大厅数据处理类
 */
public class OfficeHallPresenter extends BasePresenter<OfficeHallContract.View> implements OfficeHallContract.Presenter {
    OfficeHallModle modle;
    /*当前页面的请求次数*/
    int requestNum = 0;
    int tempNum = 0;

    public OfficeHallPresenter(int requestNum) {
        modle = new OfficeHallModle();
        this.requestNum = requestNum;
    }

    @Override
    public void getBaseData(final HttpRequest httpRequest) {
        if (!isViewAttached()) {
            return;
        }
        //打开加载框
        mView.showLoading();
        modle.getBaseData(httpRequest)
                //绑定rx生命周期
                .compose(mView.bindViewToLifecycle())
                //绑定rx的上下游关系
                .compose(RxScheduler.rxObservableSchedulerHelper())
                .subscribe(new ObserverManager<AccountInfo>(mView.getBaseContext()) {
                    @Override
                    protected void onOther(HttpResult<AccountInfo> httpResult) {

                    }

                    @Override
                    protected void onSuccess(AccountInfo data) {
                        mView.getBaseData(data);
                    }

                    @Override
                    protected void onFailure(Throwable e) {

                    }

                    @Override
                    protected void onFinish() {
                        initDataIsAllFinish();
                    }
                });
    }

    @Override
    public void getBannerData(HttpRequest httpRequest) {
        modle.getBannerData(httpRequest)
                .compose(mView.bindViewToLifecycle())
                .compose(RxScheduler.rxObservableSchedulerHelper())
                .subscribe(new ObserverManager<List<String>>(mView.getBaseContext()) {
                    @Override
                    protected void onOther(HttpResult<List<String>> httpResult) {

                    }

                    @Override
                    protected void onSuccess(List<String> data) {
                        mView.getBannerData(data);
                    }

                    @Override
                    protected void onFailure(Throwable e) {

                    }

                    @Override
                    protected void onFinish() {
                        initDataIsAllFinish();
                    }
                });

    }

    @Override
    public void getHotData(HttpRequest httpRequest) {
        modle.getHotData(httpRequest)
                .compose(mView.bindViewToLifecycle())
                .compose(RxScheduler.rxObservableSchedulerHelper())
                .subscribe(new ObserverManager<List<String>>(mView.getBaseContext()) {
                    @Override
                    protected void onOther(HttpResult<List<String>> httpResult) {

                    }

                    @Override
                    protected void onSuccess(List<String> data) {
                        mView.getHotData(data);
                    }

                    @Override
                    protected void onFailure(Throwable e) {

                    }

                    @Override
                    protected void onFinish() {
                        initDataIsAllFinish();
                    }
                });
    }

    /**
     * 判断是否已经加载完成所有的数据
     *
     * @return
     */
    public synchronized boolean initDataIsAllFinish() {
        tempNum++;
        if (tempNum == requestNum) {
            tempNum = 0;
            mView.hideLoading();
            return true;
        }
        return false;
    }
}
