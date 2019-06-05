package com.westar.module_woyaobanshi.mvp.modle;

import com.westar.library_base.http.ObserverManager;
import com.westar.library_base.http.been.HttpRequest;
import com.westar.library_base.http.been.HttpResult;
import com.westar.module_woyaobanshi.mvp.contract.ItemDetailContract;

import io.reactivex.Observable;

/**
 * Created by ZWP on 2019/5/30 14:22.
 * 描述：
 */
public class ItemDetailModle implements ItemDetailContract.Modle {
    @Override
    public Observable<HttpResult<String>> getData(HttpRequest httpRequest) {
        HttpResult<String> httpResult = new HttpResult<>();
        httpResult.setData("");
        httpResult.setCode(ObserverManager.SUCCEED_CODE);
        return Observable.just(httpResult);
    }
}
