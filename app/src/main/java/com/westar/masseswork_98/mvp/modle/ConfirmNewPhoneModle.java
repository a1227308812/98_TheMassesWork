package com.westar.masseswork_98.mvp.modle;

import com.westar.library_base.http.ObserverManager;
import com.westar.library_base.http.been.HttpRequest;
import com.westar.library_base.http.been.HttpResult;
import com.westar.masseswork_98.mvp.contract.ChangePhoneContract;
import com.westar.masseswork_98.mvp.contract.ConfirmNewPhoneContract;

import io.reactivex.Observable;

/**
 * Created by ZWP on 2019/5/7 17:10.
 * 描述：
 */
public class ConfirmNewPhoneModle implements ConfirmNewPhoneContract.Module {
    @Override
    public Observable<HttpResult<String>> getYzm(HttpRequest httpRequest) {
        HttpResult<String> httpResult = new HttpResult<>();
        httpResult.setCode(ObserverManager.SUCCEED_CODE);
        return Observable.just(httpResult);
    }

    @Override
    public Observable<HttpResult<String>> updataNewPhone(HttpRequest httpRequest) {
        HttpResult<String> httpResult = new HttpResult<>();
        httpResult.setCode(ObserverManager.SUCCEED_CODE);
        return Observable.just(httpResult);
    }
}
