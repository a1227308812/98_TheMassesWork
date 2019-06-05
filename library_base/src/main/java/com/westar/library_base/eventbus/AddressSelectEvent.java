package com.westar.library_base.eventbus;

/**
 * Created by ZWP on 2019/3/1.
 * 描述：event事件超类
 */
public class AddressSelectEvent<T> {
    private int code;
    private T data;

    public AddressSelectEvent() {
    }

    public AddressSelectEvent(int code) {
        this.code = code;
    }

    public AddressSelectEvent(int code, T data) {
        this.code = code;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public AddressSelectEvent setCode(int code) {
        this.code = code;
        return this;
    }

    public T getData() {
        return data;
    }

    public AddressSelectEvent setData(T data) {
        this.data = data;
        return this;
    }
}
