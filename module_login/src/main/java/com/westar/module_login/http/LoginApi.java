package com.westar.module_login.http;

import com.westar.masseswork_98.library_base.http.BaseApi;
import com.westar.masseswork_98.library_base.http.been.HttpResult;
import com.westar.module_login.been.User;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by ZWP on 2019/3/25 16:58.
 * 描述：
 */
public interface LoginApi extends BaseApi {

    /**
     * 登录接口
     *
     * @param jsonParam
     * @return
     */
    @FormUrlEncoded
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    @POST("/workApp/login")
    Observable<HttpResult<User>> login(@Field("jsonParam") String jsonParam);
}
