package com.westar.library_base.callback;

/**
 * Created by ZWP on 2019/3/1.
 * 描述：权限请求回调接口
 */
public interface IPermissionsCallBack {
    /**
     * 权限请求失败
     * @param name
     */
    void permissionErro(String name);

    /**
     * 权限请求成功
     * @param name
     */
    void permissionSuccess(String name);
}
