package com.westar.library_base.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.ViewGroup;

import com.google.android.flexbox.FlexboxLayoutManager;

/**
 * Created by ZWP on 2019/4/28 15:29.
 * 描述：自定义自适应manager
 */
public class CustomFlexboxLayoutManager extends FlexboxLayoutManager {

    public CustomFlexboxLayoutManager(Context context) {
        super(context);
    }

    public CustomFlexboxLayoutManager(Context context, int flexDirection) {
        super(context, flexDirection);
    }

    public CustomFlexboxLayoutManager(Context context, int flexDirection, int flexWrap) {
        super(context, flexDirection, flexWrap);
    }

    public CustomFlexboxLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    /**
     * 增加FlexboxLayoutManager兼容性
     *
     * @param lp
     * @return
     */
    @Override
    public RecyclerView.LayoutParams generateLayoutParams(ViewGroup.LayoutParams lp) {
        if (lp instanceof RecyclerView.LayoutParams) {
            return new LayoutParams((RecyclerView.LayoutParams) lp);
        } else if (lp instanceof ViewGroup.MarginLayoutParams) {
            return new LayoutParams((ViewGroup.MarginLayoutParams) lp);
        } else {
            return new LayoutParams(lp);
        }
    }
}