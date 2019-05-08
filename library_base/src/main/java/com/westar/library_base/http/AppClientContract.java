package com.westar.library_base.http;

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
 * Created by ZWP on 2019/3/25 17:14.
 * 描述：
 */
public abstract class AppClientContract<T> {
    //默认超时时间 1分钟
    private static long DEFAULT_TIMEOUT = 1;
    protected static String baseUrl = "192.168.1.44:80";

    /**
     * 获取自定义的apiclass
     *
     * @return
     */
    public abstract Class<T> getApiClass();

    /**
     * 设置baseurl
     *
     * @return
     */
    public abstract String getBaseUrl();

    public abstract T creatAPI();


    /**
     * 创建Retrofit
     *
     * @return
     */
    private Retrofit creatRetrofit() {
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

    /**
     * 关联请求api
     *
     * @return
     */
    protected T createRequestApi() {
        return creatRetrofit().create(getApiClass());
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
