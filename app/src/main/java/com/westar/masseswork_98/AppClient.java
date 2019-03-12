package com.westar.masseswork_98;

import android.util.Log;

import com.westar.masseswork_98.library_base.http.BaseApi;
import com.westar.masseswork_98.library_base.http.BaseAppClient;

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
 */
public class AppClient {
    //默认超时时间 1分钟
    private static long DEFAULT_TIMEOUT = 1;
    private Retrofit mRetrofit;
    public Api api;
    public String mBaseUrl = "http://192.168.1.182:8888";


    //静态初始化器，由JVM来保证线程安全
    private static class RequestManagerHodler {
        private static AppClient instance = new AppClient();
    }

    public static AppClient getInstance() {
        return AppClient.RequestManagerHodler.instance;
    }

    //私有化构造方法
    private AppClient() {
        mRetrofit = createRetrofit();
        api = createRequestApi(Api.class);
    }


    /**
     * 关联api和Retrofit
     *
     * @param apiClass
     * @param <T>
     * @return
     */
    protected <T> T createRequestApi(Class<T> apiClass) {
        return mRetrofit.create(apiClass);
    }

    /**
     * 创建Retrofit
     *
     * @return
     */
    private Retrofit createRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(mBaseUrl)
                // RxJava2
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                // 字符串
                .addConverterFactory(ScalarsConverterFactory.create())
                // 设置数据解析器
                .addConverterFactory(GsonConverterFactory.create())
                .client(initOkhttpClient())
                .build();
    }

    /**
     * 每次请求都会走拦截器
     * <p>
     * 只需要修改Constants.TOKEN就可以
     */
    private OkHttpClient initOkhttpClient() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor interceptorHttp = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.e("网络请求--拦截器--", message);
            }
        });
        interceptorHttp.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(interceptorHttp);
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.MINUTES).
                readTimeout(DEFAULT_TIMEOUT, TimeUnit.MINUTES).
                writeTimeout(DEFAULT_TIMEOUT, TimeUnit.MINUTES);
        return builder.build();
    }

}
