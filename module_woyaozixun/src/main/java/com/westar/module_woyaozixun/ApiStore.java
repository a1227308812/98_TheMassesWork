package com.westar.module_woyaozixun;

import com.westar.library_base.http.BaseApi;
import com.westar.library_base.http.been.HttpResult;
import com.westar.module_woyaozixun.databean.MenuItem;
import com.westar.module_woyaozixun.databean.Reply;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by lgy on 2019/6/13
 * 描述：我要咨询模块的api地址集合
 */
public interface ApiStore extends BaseApi{

    /**
     * 我要咨询发送咨询
     *
     * @param jsonParam
     * @return
     */
    @FormUrlEncoded
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    @POST("/woyaozixun/submit")
    Observable<HttpResult<Reply>> sendMsg(@Field("jsonParam") String jsonParam);

    /**
     * 我要咨询提交表单
     *
     * @param jsonParam
     * @return
     */
    @FormUrlEncoded
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    @POST("/woyaozixun/submit")
    Observable<HttpResult<String>> submit(@Field("jsonParam") String jsonParam);

    /**
     * 我要投诉获取投诉主题数据接口
     *
     * @return
     */
    @FormUrlEncoded
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    @POST("/woyaozixun/liuyanTitleList")
    Observable<HttpResult<List<MenuItem>>> liuyanTitleList();
}
