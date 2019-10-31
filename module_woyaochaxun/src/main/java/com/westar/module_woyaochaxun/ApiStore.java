package com.westar.module_woyaochaxun;

import com.westar.library_base.http.BaseApi;
import com.westar.library_base.http.been.HttpResult;
import com.westar.module_woyaochaxun.model.DealItem;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by lgy on 2019/6/13
 * 描述：我要投诉模块的api地址集合
 */
public interface ApiStore extends BaseApi{
    /**
     * 我要查询获取办件列表
     *
     * @return
     */
    @FormUrlEncoded
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    @POST("/woyaochaxun/tousuTitleList")
    Observable<HttpResult<List<DealItem>>> chaxunDealItemList(@Field("jsonParam") String jsonParam);
}
