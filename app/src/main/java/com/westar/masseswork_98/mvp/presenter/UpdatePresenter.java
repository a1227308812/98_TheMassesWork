//package com.westar.masseswork_98.mvp.presenter;
//
//import BasePresenter;
//import ObserverManager;
//import HttpRequest;
//import HttpResult;
//import RxScheduler;
//import LLog;
//import com.westar.masseswork_98.mvp.contract.UpdateContract;
//import com.westar.masseswork_98.mvp.modle.UpdateModle;
//
///**
// * Created by ZWP on 2019/3/12.
// * 描述：更新控制类
// * 1、实例化modle
// * 2、通过实例化的modle获取数据
// * 3、获取到数据之后的处理与界面的交互操作通过view完成
// */
//public class UpdatePresenter extends BasePresenter<UpdateContract.View> implements UpdateContract.presenter {
//    //更新模块的数据modle
//    private UpdateModle modle;
//
//    /**
//     * 在初始化presenter的时候就初始化modle
//     */
//    public UpdatePresenter() {
//        modle = new UpdateModle();
//    }
//
//
//
//    /**
//     * 更新数据回调
//     *
//     * @param httpRequest
//     */
//    @Override
//    public void update(HttpRequest httpRequest) {
//        //判断是否已经绑定presenter
//        if (!isViewAttached()) {
//            return;
//        }
//        mView.showLoading();
//
//        modle.update(httpRequest)
//                .compose(mView.bindViewToLifecycle())
//                .compose(RxScheduler.rxObservableSchedulerHelper())
//                .subscribe(new ObserverManager<Version>(mView.getBaseContext()) {
//
//                    @Override
//                    protected void onOther(HttpResult<Version> httpResult) {
//                        LLog.e("","onOther");
//                        mView.onOther(httpResult);
//                    }
//
//                    @Override
//                    protected void onSuccess(Version data) {
//                        LLog.e("","onSuccess");
//                        mView.onSuccess(data);
//                    }
//
//                    @Override
//                    protected void onFailure(Throwable e) {
//                        LLog.e("","onFailure ---- " + e.getMessage());
//                        mView.onError(e);
//                    }
//
//                    @Override
//                    protected void onFinish() {
//                        LLog.e("","onFinish");
//                        mView.hideLoading();
//                    }
//                });
//    }
//}
