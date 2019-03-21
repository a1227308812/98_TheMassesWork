package com.westar.masseswork_98.library_base.http;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.westar.masseswork_98.library_base.BuildConfig;

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
public class BaseAppClient<T> implements AppClientImp<BaseApi> {
    //默认超时时间 1分钟
    private static long DEFAULT_TIMEOUT = 1;

    @Override
    public Class<BaseApi> getApiClass() {
        return BaseApi.class;
    }

    @Override
    public Retrofit creatRetrofit() {
        //设置gson解析，不然在某种情况会报解析错误
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        return new Retrofit.Builder()
                .baseUrl(getBaseUrl())
                // RxJava2
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                // 字符串
                .addConverterFactory(ScalarsConverterFactory.create())
                // 设置数据解析器
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(initOkhttpClient())
                .build();
    }

    @Override
    public String getBaseUrl() {
        return "http://192.168.1.182:8888";
    }

    @Override
    public BaseApi creatAPI() {
        return createRequestApi(getApiClass());
    }


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



    /**
     * 关联api和Retrofit
     *
     * @param apiClass
     * @param <T>
     * @return
     */
    protected <T> T createRequestApi(Class<T> apiClass) {
        return creatRetrofit().create(apiClass);
    }


    /**
     * 每次请求都会走拦截器
     * <p>
     * 只需要修改Constants.TOKEN就可以
     */
    private OkHttpClient initOkhttpClient() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.e("网络请求--拦截器--", message);
            }
        });
        // 开发模式记录整个body，否则只记录基本信息如返回200，http协议版本等
        if (BuildConfig.DEBUG) {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        }
        builder.addInterceptor(loggingInterceptor);
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.MINUTES).
                readTimeout(DEFAULT_TIMEOUT, TimeUnit.MINUTES).
                writeTimeout(DEFAULT_TIMEOUT, TimeUnit.MINUTES);
        return builder.build();
    }


}
