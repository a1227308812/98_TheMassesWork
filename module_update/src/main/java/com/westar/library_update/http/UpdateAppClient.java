package com.westar.library_update.http;

import com.westar.library_base.http.AppClientContract;

/**
 * Created by ZWP on 2019/3/11.
 * 描述：
 * 1、创建Retrofit
 * 2、设置Retrofit基础参数和功能支持
 * 3、关联rxjava2
 */
public class UpdateAppClient extends AppClientContract<UpdateApi> {


    //静态初始化器，由JVM来保证线程安全
    private static class RequestManagerHodler {
        private static UpdateAppClient instance = new UpdateAppClient();
    }

    public static UpdateAppClient getInstance() {
        return UpdateAppClient.RequestManagerHodler.instance;
    }

    //私有化构造方法
    private UpdateAppClient() {
    }


    @Override
    public Class<UpdateApi> getApiClass() {
        return UpdateApi.class;
    }

    @Override
    public String getBaseUrl() {
        return AppClientContract.baseUrl;
    }

    @Override
    public UpdateApi creatAPI() {
        return createRequestApi();
    }


}
