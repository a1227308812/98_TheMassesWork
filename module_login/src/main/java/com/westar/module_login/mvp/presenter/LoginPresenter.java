package com.westar.module_login.mvp.presenter;

import com.westar.library_base.base.BasePresenter;
import com.westar.library_base.http.ObserverManager;
import com.westar.library_base.http.been.HttpRequest;
import com.westar.library_base.http.been.HttpResult;
import com.westar.library_base.rxjava.RxScheduler;
import com.westar.module_login.been.User;
import com.westar.module_login.mvp.contract.LoginContract;
import com.westar.module_login.mvp.modle.LoginModle;

/**
 * Created by ZWP on 2019/3/25 18:04.
 * 描述：
 */
public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.presenter {
    LoginModle loginModle;

    public LoginPresenter() {
        loginModle = new LoginModle();
    }

    /**
     * 登录
     *
     * @param httpRequest
     */
    @Override
    public void login(HttpRequest httpRequest) {
        if (!isViewAttached()) {
            return;
        }
        mView.showLoading();
        loginModle.login(httpRequest)
                .compose(mView.bindViewToLifecycle())
                .compose(RxScheduler.rxObservableSchedulerHelper())
                .subscribe(new ObserverManager<User>(mView.getBaseContext()) {
                    /**
                     * 请求成功其他返回值  一般用于登录失败时 用于区别登录不成功的原因
                     *
                     * @param httpResult
                     */
                    @Override
                    protected void onOther(HttpResult<User> httpResult) {
                        mView.hideLoading();
                        mView.onSuccess(httpResult);
                    }

                    /**
                     * 回调正确状态的data数据
                     *
                     * @param data
                     */
                    @Override
                    protected void onSuccess(User data) {
                        mView.hideLoading();
                        mView.onSuccess(data);
                    }

                    /**
                     * 回调错误方法
                     *
                     * @param e
                     */
                    @Override
                    protected void onFailure(Throwable e) {
                        mView.hideLoading();
                        mView.onError(e);
                    }

                    /**
                     * 请求流结束
                     */
                    @Override
                    protected void onFinish() {
                        mView.hideLoading();
                    }
                });
    }
}
