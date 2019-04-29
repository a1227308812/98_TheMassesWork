package com.westar.masseswork_98.mvp.presenter;

import com.westar.been.AddressNode;
import com.westar.library_base.base.BasePresenter;
import com.westar.library_base.http.ObserverManager;
import com.westar.library_base.http.been.HttpRequest;
import com.westar.library_base.http.been.HttpResult;
import com.westar.library_base.rxjava.RxScheduler;
import com.westar.masseswork_98.mvp.contract.ChoiceAddressContract;
import com.westar.masseswork_98.mvp.modle.ChoiceAddressModle;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by ZWP on 2019/4/28 15:55.
 * 描述：
 */
public class ChoiceAddressPresenter extends BasePresenter<ChoiceAddressContract.View> implements ChoiceAddressContract.Presenter {
    ChoiceAddressModle modle;

    public ChoiceAddressPresenter() {
        modle = new ChoiceAddressModle();
    }

    @Override
    public void getAddressList(HttpRequest httpRequest) {
        if (!isViewAttached()) return;
        mView.showLoading();
        modle.getAddressList(httpRequest)
                .compose(mView.bindViewToLifecycle())
                .compose(RxScheduler.rxObservableSchedulerHelper())
                .subscribe(new ObserverManager<List<AddressNode>>(mView.getBaseContext()) {
                    @Override
                    protected void onOther(HttpResult httpResult) {

                    }


                    @Override
                    protected void onSuccess(final List<AddressNode> data) {
                        /**
                         * 1、把地址数据保存到本地数据库
                         * 2、返回第一级数据列表
                         */
                        Realm realm = Realm.getDefaultInstance();
                        realm.executeTransaction(new Realm.Transaction() {
                            @Override
                            public void execute(Realm realm) {
                                realm.where(AddressNode.class).findAll().deleteAllFromRealm();

                                for (AddressNode addressNode : data) {
                                    realm.copyToRealm(addressNode);
                                }

                                RealmResults<AddressNode> realmResults = realm.where(AddressNode.class)
                                        .equalTo("parentId", AddressNode.ROOTPOSITION)
                                        .findAll();

                                mView.onSuccess(realmResults);
                            }
                        });


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
