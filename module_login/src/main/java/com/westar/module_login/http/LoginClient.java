package com.westar.module_login.http;

import com.westar.library_base.http.AppClientContract;

/**
 * Created by ZWP on 2019/3/25 17:04.
 * 描述：
 */
public class LoginClient extends AppClientContract<LoginApi> {

    @Override
    public Class<LoginApi> getApiClass() {
        return LoginApi.class;
    }

    @Override
    public String getBaseUrl() {
        return AppClientContract.baseUrl;
    }

    @Override
    public LoginApi creatAPI() {
        return createRequestApi();
    }

    //静态初始化器，由JVM来保证线程安全
    private static class RequestManagerHodler {
        private static LoginClient instance = new LoginClient();
    }

    public static LoginClient getInstance() {
        return LoginClient.RequestManagerHodler.instance;
    }

    //私有化构造方法
    private LoginClient() {
    }

}
