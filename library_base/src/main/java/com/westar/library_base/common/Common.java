package com.westar.library_base.common;

/**
 * Created by ZWP on 2019/4/29 17:28.
 * 描述：通用的公共参数
 */
public interface Common {
    /**
     * 未登录
     */
    public final static int NOT_LOGIN = 1;
    /**
     * 已登录未认证
     */
    public final static int NOT_AUTHENTICATION = 2;
    /**
     * 已登录已认证
     */
    public final static int HAD_AUTHENTICATION = 3;
    /**
     * 认证状态传参key
     */
    public final static String AUTHENTICATION_TYPE = "authentication_type";
}
