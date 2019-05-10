package com.westar.masseswork_98.mvp.presenter;

import com.blankj.utilcode.util.ToastUtils;
import com.westar.been.LocationNode;
import com.westar.library_base.base.BasePresenter;
import com.westar.library_base.http.ObserverManager;
import com.westar.library_base.http.been.HttpRequest;
import com.westar.library_base.http.been.HttpResult;
import com.westar.library_base.rxjava.RxScheduler;
import com.westar.masseswork_98.mvp.contract.AddAddressContract;
import com.westar.masseswork_98.mvp.modle.AddAddressModle;

import java.util.List;

import io.realm.Realm;


/**
 * Created by ZWP on 2019/5/10 13:12.
 * 描述：
 */
public class AddAddressPresenter extends BasePresenter<AddAddressContract.View> implements AddAddressContract.Presenter {
    AddAddressModle modle;

    public AddAddressPresenter() {
        modle = new AddAddressModle();
    }

    @Override
    public void addAddress(HttpRequest httpRequest) {
        if (!isViewAttached()) return;
        mView.showLoading();
        modle.addAddress(httpRequest)
                .compose(mView.bindViewToLifecycle())
                .compose(RxScheduler.rxObservableSchedulerHelper())
                .subscribe(new ObserverManager<String>(mView.getBaseContext()) {
                    @Override
                    protected void onOther(HttpResult httpResult) {

                    }

                    @Override
                    protected void onSuccess(String data) {
                        ToastUtils.showShort(data);
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

    @Override
    public void getServiceAddressData(HttpRequest httpRequest) {
        if (!isViewAttached()) return;
        mView.showLoading();
        modle.getServiceAddressData(httpRequest)
                .compose(mView.bindViewToLifecycle())
                .compose(RxScheduler.rxObservableSchedulerHelper())
                .subscribe(new ObserverManager<List<LocationNode>>(mView.getBaseContext()) {

                    @Override
                    protected void onOther(HttpResult<List<LocationNode>> httpResult) {

                    }

                    @Override
                    protected void onSuccess(final List<LocationNode> locationNodeList) {

//                        Realm realm = Realm.getDefaultInstance();
//                        realm.executeTransaction(new Realm.Transaction() {
//                            @Override
//                            public void execute(Realm realm) {
//                                List<LocationNode> locationNodes = realm.where(LocationNode.class).findAll();
//                                if (locationNodes == null || locationNodes.size() == 0){
//                                    //保存数据库
//                                    for (LocationNode locationNode : locationNodeList) {
//                                        realm.copyToRealm(locationNode);
//                                    }
//                                }else {
//
//                                }
//                            }
//                        });
                        mView.getServiceAddressData(locationNodeList);
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
