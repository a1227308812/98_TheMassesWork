package com.westar.masseswork_98.library_base.http;

import retrofit2.Retrofit;

/**
 * Created by ZWP on 2019/3/20.
 * 描述：
 */
public interface AppClientImp<T> {

    Class<T> getApiClass();

    Retrofit creatRetrofit();

    String getBaseUrl();

    T creatAPI();
}
