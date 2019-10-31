package com.westar.module_woyaotousu;

import com.westar.library_base.http.AppClientContract;

/**
 * Created by lgy on 2019/6/13
 * 描述：我要投诉模块的网络请求客户端
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
