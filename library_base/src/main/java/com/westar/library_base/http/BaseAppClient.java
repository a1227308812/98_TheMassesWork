package com.westar.library_base.http;

/**
 * Created by ZWP on 2019/3/11.
 * 描述：
 * 1、创建Retrofit
 * 2、设置Retrofit基础参数和功能支持
 * 3、关联rxjava2
 */
public class BaseAppClient extends AppClientContract<BaseApi> {

    //静态初始化器，由JVM来保证线程安全
    private static class RequestManagerHodler {
        private static BaseAppClient instance = new BaseAppClient();
    }

    public static BaseAppClient getInstance() {
        return BaseAppClient.RequestManagerHodler.instance;
    }

    //私有化构造方法
    private BaseAppClient() {
    }


    @Override
    public Class<BaseApi> getApiClass() {
        return BaseApi.class;
    }

    @Override
    public String getBaseUrl() {
        return baseUrl;
    }

    @Override
    public BaseApi creatAPI() {
        return createRequestApi();
    }


}
