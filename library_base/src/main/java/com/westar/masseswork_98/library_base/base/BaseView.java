package com.westar.masseswork_98.library_base.base;

import android.content.Context;

import com.trello.rxlifecycle2.LifecycleTransformer;


/**
 * Created by ZWP on 2019/3/1.
 * 描述：公共方法超类
 */
public interface BaseView {

    /**
     * 显示加载中
     */
    void showLoading();

    /**
     * 隐藏加载
     */
    void hideLoading();

    /**
     * 上下文
     *
     * @return
     */
    Context getBaseContext();

    /**
     * 请求成功其他返回值
     *
     * @param data
     */
    void onOther(Object data);

    /**
     * 回调正确状态的data数据
     *
     * @param data
     */
    void onSuccess(Object data);

    /**
     * 回调错误方法
     *
     * @param e
     */
    void onError(Throwable e);

    /**
     * 防止RxJva 内存泄漏
     * @return
     */
    LifecycleTransformer bindViewToLifecycle();
}
