package com.westar.library_update.http;

import com.westar.library_base.http.BaseApi;
import com.westar.library_base.http.been.HttpResult;
import com.westar.library_update.been.Version;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by ZWP on 2019/3/11.
 * 描述：接口地址
 */
public interface UpdateApi extends BaseApi{

    /**
     * app类型（apkType）
     * <DataDic code="sp" zvalue="工作人员APP"></DataDic>
     * <DataDic code="web" zvalue="申请人app"></DataDic>
     * "device" zvalue="设备壳子app"></DataDic>
     *
     * @param jsonParam
     * @return
     */
    @FormUrlEncoded
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    @POST("app/checkVersion")
    Observable<HttpResult<Version>> update(@Field("jsonParam") String jsonParam);

}
