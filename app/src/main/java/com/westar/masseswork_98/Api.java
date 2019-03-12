package com.westar.masseswork_98;

import com.westar.masseswork_98.library_base.http.been.HttpRequest;
import com.westar.masseswork_98.library_base.http.been.HttpResult;


import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by ZWP on 2019/3/11.
 * 描述：
 */
public interface Api {
    /**
     * app类型（apkType）
     * <DataDic code="sp" zvalue="工作人员APP"></DataDic>
     * <DataDic code="web" zvalue="申请人app"></DataDic>
     * "device" zvalue="设备壳子app"></DataDic>
     *
     * @param httpRequest
     * @return
     */
    @POST("app/checkVersion")
    Observable<HttpResult> update(@Body HttpRequest httpRequest);
}
