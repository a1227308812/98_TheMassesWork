package com.westar.masseswork_98.library_update.http;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.westar.masseswork_98.library_base.BuildConfig;
import com.westar.masseswork_98.library_base.http.AppClientContract;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

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
