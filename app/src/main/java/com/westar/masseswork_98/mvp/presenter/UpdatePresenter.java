package com.westar.masseswork_98.mvp.presenter;

import com.westar.masseswork_98.been.Update;
import com.westar.masseswork_98.library_base.base.BasePresenter;
import com.westar.masseswork_98.library_base.base.BaseView;
import com.westar.masseswork_98.library_base.http.ObserverManager;
import com.westar.masseswork_98.library_base.http.been.HttpRequest;
import com.westar.masseswork_98.library_base.http.been.HttpResult;
import com.westar.masseswork_98.library_base.rxjava.RxScheduler;
import com.westar.masseswork_98.library_base.utils.LLog;
import com.westar.masseswork_98.mvp.contract.UpdateContract;
import com.westar.masseswork_98.mvp.modle.UpdateModle;

import io.reactivex.Observable;

/**
 * Created by ZWP on 2019/3/12.
 * 描述：更新控制类
 * 1、实例化modle
 * 2、通过实例化的modle获取数据
 * 3、获取到数据之后的处理与界面的交互操作通过view完成
 */
public class UpdatePresenter extends BasePresenter<UpdateContract.View> implements UpdateContract.presenter {
    //更新模块的数据modle
    private UpdateModle modle;

    /**
     * 在初始化presenter的时候就初始化modle
     */
    public UpdatePresenter() {
        modle = new UpdateModle();
    }

    /**
     * 更新数据回调
     *
     * @param httpRequest
     */
    @Override
    public void update(HttpRequest httpRequest) {
        //判断是否已经绑定presenter
        if (!isViewAttached()) {
            return;
        }

        modle.update(httpRequest)
                .compose(mView.bindViewToLifecycle())
                .compose(RxScheduler.rxObservableSchedulerHelper())
                .subscribe(new ObserverManager<Update>(mView.getBaseContext()) {

                    @Override
                    protected void onOther(HttpResult<Update> httpResult) {
                        LLog.e("","onOther");
                    }

                    @Override
                    protected void onSuccess(Update data) {
                        LLog.e("","onSuccess");
                    }

                    @Override
                    protected void onFailure(Throwable e) {
                        LLog.e("","onFailure ---- " + e.getMessage());
                    }

                    @Override
                    protected void onFinish() {
                        LLog.e("","onFinish");
                    }
                });
    }
}
