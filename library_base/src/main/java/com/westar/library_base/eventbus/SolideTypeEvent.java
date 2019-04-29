package com.westar.library_base.eventbus;

import com.westar.library_base.eventbus.BaseEvent;

/**
 * Created by ZWP on 2019/4/29 16:22.
 * 描述：侧边栏的显示判断
 */
public class SolideTypeEvent extends BaseEvent {
    public SolideTypeEvent(int code) {
        super(code);
    }
}
