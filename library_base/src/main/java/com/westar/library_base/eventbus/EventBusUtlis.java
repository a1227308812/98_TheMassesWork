package com.westar.library_base.eventbus;

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
     * @param baseEvent
     */
    public static void sendEvent(BaseEvent baseEvent) {
        EventBus.getDefault().post(baseEvent);
    }

    /**
     * 发送粘性事件
     *
     * @param baseEvent
     */
    public static void sendStickyEvent(BaseEvent baseEvent) {
        EventBus.getDefault().postSticky(baseEvent);
    }

    /**
     * 移除粘性事件
     *
     * @param baseEvent
     */
    public static void removeStickyEvent(BaseEvent baseEvent) {
        EventBus.getDefault().removeStickyEvent(baseEvent);
    }
}
