package com.westar.library_base.view;

import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by ZWP on 2019/5/5 15:02.
 * 描述：防止自己消费点击事件，导致父类无法接收点击事件
 * 原因在于EditText的onTouchEvent始终返回true,尽管已经setEnable(false),这样事件在EditText这里就已经消费掉了，不会再传给父容器了。
 */
public class CustomTextInputEditText extends TextInputEditText {
    public CustomTextInputEditText(Context context) {
        super(context);
    }

    public CustomTextInputEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomTextInputEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return isEnabled() && super.onTouchEvent(event);
    }

}
