package com.westar.masseswork_98;

import com.westar.library_base.http.BaseApi;
import com.westar.library_base.http.been.HttpResult;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by ZWP on 2019/4/24 13:29.
 * 描述：主页模块的api地址集合
 */
public interface ApiStore extends BaseApi {
    /**
     * 办事大厅人获取页面数据接口
     *
     * @param jsonParam
     * @return
     */
    @FormUrlEncoded
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    @POST("/workApp/login")
    Observable<HttpResult<String>> officahHellGetData(@Field("jsonParam") String jsonParam);

    @FormUrlEncoded
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    @POST("/workApp/login")
    Observable<HttpResult<List<String>>> officahHellGetBannerData(@Field("jsonParam") String jsonParam);
}