package com.westar.masseswork_98.mvp.presenter;

import com.westar.been.LocationNode;
import com.westar.library_base.base.BasePresenter;
import com.westar.library_base.http.ObserverManager;
import com.westar.library_base.http.been.HttpRequest;
import com.westar.library_base.http.been.HttpResult;
import com.westar.library_base.rxjava.RxScheduler;
import com.westar.masseswork_98.mvp.contract.PersonalInformationContract;
import com.westar.masseswork_98.mvp.modle.PersonalInformationModle;

import java.util.List;


/**
 * Created by ZWP on 2019/5/6 16:16.
 * 描述：
 */
public class PersonalInformationPresenter extends BasePresenter<PersonalInformationContract.View> implements PersonalInformationContract.Presenter {
    PersonalInformationModle module;

    public PersonalInformationPresenter() {
        module = new PersonalInformationModle();
    }

    @Override
    public void getServiceAddressData(HttpRequest httpRequest) {
        if (!isViewAttached()) return;
        mView.showLoading();
        module.getServiceAddressData(httpRequest)
                .compose(mView.bindViewToLifecycle())
                .compose(RxScheduler.rxObservableSchedulerHelper())
                .subscribe(new ObserverManager<List<LocationNode>>(mView.getBaseContext()) {

                    @Override
                    protected void onOther(HttpResult<List<LocationNode>> httpResult) {

                    }

                    @Override
                    protected void onSuccess(final List<LocationNode> locationNodeList) {
//                        //保存数据库
//                        Realm realm = Realm.getDefaultInstance();
//                        realm.executeTransaction(new Realm.Transaction() {
//                            @Override
//                            public void execute(Realm realm) {
//                                for (LocationNode locationNode : locationNodeList) {
//                                    realm.copyToRealm(locationNode);
//                                }
//                            }
//                        });
                        mView.onSuccess(locationNodeList);
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
    public void updatePersonalInfo(HttpRequest httpRequest) {
        if (!isViewAttached()) return;
        mView.showLoading();
        module.getServiceAddressData(httpRequest)
                .compose(mView.bindViewToLifecycle())
                .compose(RxScheduler.rxObservableSchedulerHelper())
                .subscribe(new ObserverManager<String>(mView.getBaseContext()) {

                    @Override
                    protected void onOther(HttpResult<String> httpResult) {

                    }

                    @Override
                    protected void onSuccess(String data) {
                        mView.updatePersonalResult(data);
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
