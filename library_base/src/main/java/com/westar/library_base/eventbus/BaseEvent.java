package com.westar.library_base.eventbus;

/**
 * Created by ZWP on 2019/3/1.
 * 描述：event事件超类
 */
public class BaseEvent<T> {
    private int code;
    private T data;

    public BaseEvent(int code) {
        this.code = code;
    }

    public BaseEvent(int code, T data) {
        this.code = code;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}