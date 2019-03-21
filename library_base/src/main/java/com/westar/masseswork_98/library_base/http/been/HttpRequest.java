package com.westar.masseswork_98.library_base.http.been;

import java.io.Serializable;

/**
 * Created by ZWP on 2019/3/11.
 * 描述：请求参数基类
 */
public class HttpRequest<T> implements Serializable {
    T data;

    public T getData() {
        return data;
    }

    public HttpRequest<T> setData(T data) {
        this.data = data;
        return this;
    }
}
