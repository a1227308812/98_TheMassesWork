package com.westar.library_base.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.qmuiteam.qmui.widget.QMUITabSegment;

/**
 * Created by ZWP on 2019/4/30 15:27.
 * 描述：滑动事件自己处理
 */
public class HorizonScrollSelfTabSegment extends CustomHorizonTabLayout {
    public HorizonScrollSelfTabSegment(Context context) {
        super(context);
    }

    public HorizonScrollSelfTabSegment(Context context, boolean hasIndicator) {
        super(context, hasIndicator);
    }

    public HorizonScrollSelfTabSegment(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HorizonScrollSelfTabSegment(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        //请求父类不要拦截触摸事件
        getParent().requestDisallowInterceptTouchEvent(true);
        return super.onInterceptTouchEvent(ev);
    }
}
