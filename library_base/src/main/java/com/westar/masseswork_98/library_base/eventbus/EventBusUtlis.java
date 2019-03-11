package com.westar.masseswork_98.library_base.eventbus;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by ZWP on 2019/3/1.
 * 描述：EventBus工具类
 */
public class EventBusUtlis {
    /**
     * 注册
     *
     * @param subscriber
     */
    public static void register(Object subscriber) {
        EventBus.getDefault().register(subscriber);
    }

    /**
     * 反注册
     *
     * @param subscriber
     */
    public static void unregister(Object subscriber) {

        EventBus.getDefault().unregister(subscriber);
    }

    /**
     * 发送普通事件
     *
     * @param event
     */
    public static void sendEvent(Event event) {
        EventBus.getDefault().post(event);
    }

    /**
     * 发送粘性事件
     *
     * @param event
     */
    public static void sendStickyEvent(Event event) {
        EventBus.getDefault().postSticky(event);
    }

    /**
     * 移除粘性事件
     *
     * @param event
     */
    public static void removeStickyEvent(Event event) {
        EventBus.getDefault().removeStickyEvent(event);
    }
}
