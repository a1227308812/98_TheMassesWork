package com.westar.masseswork_98.library_base.http;

import com.westar.masseswork_98.library_base.http.been.HttpRequest;
import com.westar.masseswork_98.library_base.http.been.HttpResult;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by ZWP on 2019/3/11.
 * 描述：接口地址
 */
public interface BaseApi {

    /**
     * 登录
     * @return
     */
    @POST("user/login")
    Observable<HttpResult> login(@Body HttpRequest httpRequest);

}
