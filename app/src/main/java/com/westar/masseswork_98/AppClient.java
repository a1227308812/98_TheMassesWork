package com.westar.masseswork_98;

import com.westar.library_base.http.AppClientContract;


/**
 * Created by ZWP on 2019/4/24 13:28.
 * 描述：主页模块的网络请求客户端
 */
public class AppClient extends AppClientContract<ApiStore> {

    @Override
    public Class<ApiStore> getApiClass() {
        return ApiStore.class;
    }

    @Override
    public String getBaseUrl() {
        return baseUrl;
    }

    @Override
    public ApiStore creatAPI() {
        return createRequestApi();
    }

    //静态初始化器，由JVM来保证线程安全
    private static class RequestManagerHodler {
        private static AppClient instance = new AppClient();
    }

    public static AppClient getInstance() {
        return AppClient.RequestManagerHodler.instance;
    }

    //私有化构造方法
    private AppClient() {
    }
}
