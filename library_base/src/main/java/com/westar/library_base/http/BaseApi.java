package com.westar.library_base.http;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.adapter.rxjava2.Result;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by ZWP on 2019/3/11.
 * 描述：接口地址
 */
public interface BaseApi {


    @GET()
    Call<String> get(@Url String url, @QueryMap Map<String, String> params, @HeaderMap Map<String, String> headers);

    @FormUrlEncoded
    @POST()
    Call<String> post(@Url String url, @FieldMap Map<String, String> params, @HeaderMap Map<String, String> headers);

    @GET()
    Observable<String> Obget(@Url String url, @QueryMap Map<String, String> params, @HeaderMap Map<String, String> headers);

    @FormUrlEncoded
    @POST()
    Observable<String> Obpost(@Url String url, @FieldMap Map<String, String> params, @HeaderMap Map<String, String> headers);

    @FormUrlEncoded
    @PUT
    Observable<String> Obput(@Url String url, @FieldMap Map<String, String> params, @HeaderMap Map<String, String> headers);


    //下载
    @Streaming
    @GET()
    Observable<ResponseBody> Obdownload(@HeaderMap Map<String, String> headers, @Url String url, @QueryMap Map<String, String> params);
    //下载
    @Streaming
    @GET()
    Call<ResponseBody> download(@HeaderMap Map<String, String> headers, @Url String url, @QueryMap Map<String, String> params);

    /*附件上传接口 一次上传单个文件*/
    @Multipart
    @POST()
//    Call<ResponseBody> uploadOneFile(@Url String url, @Part("description") RequestBody description, @Part MultipartBody.Part file);
    Call<Result> uploadOneFile(@Url String url, @Part MultipartBody.Part file);

    /*附件上传接口 一次上传多个文件*/
    @Multipart
    @POST()
    Call<Result> uploadMultipleFile(@Url String url, @PartMap Map<String, RequestBody> params);
}
