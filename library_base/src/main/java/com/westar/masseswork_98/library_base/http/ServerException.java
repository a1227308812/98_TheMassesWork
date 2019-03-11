package com.westar.masseswork_98.library_base.http;

/**
 * Created by ZWP on 2019/3/11.
 * 描述：服务器下发的错误
 */
public class ServerException extends RuntimeException {

    public int code;

    public ServerException(int code, String message) {
        super(message);
        this.code = code;
    }
}
