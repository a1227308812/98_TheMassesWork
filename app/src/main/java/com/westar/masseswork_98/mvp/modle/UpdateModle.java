package com.westar.masseswork_98.mvp.modle;


import com.westar.masseswork_98.AppClient;
import com.westar.masseswork_98.library_base.http.been.HttpRequest;
import com.westar.masseswork_98.library_base.http.been.HttpResult;
import com.westar.masseswork_98.mvp.contract.UpdateContract;

import io.reactivex.Observable;

/**
 * Created by ZWP on 2019/3/12.
 * 描述：
 */
public class UpdateModle implements UpdateContract.modle {
    @Override
    public Observable<HttpResult> update(HttpRequest httpRequest) {
        //关联具体的api
        return AppClient.getInstance().api.update(httpRequest);
    }
}
