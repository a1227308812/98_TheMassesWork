package com.westar.library_base.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uuzuche.lib_zxing.DisplayUtil;
import com.westar.masseswork_98.library_base.R;

import java.util.Arrays;
import java.util.List;

/**
 * Created by ZWP on 2019/5/15 9:58.
 * 描述：滑动事件自己处理
 */
public class HorizontalScrollSelfView extends HorizontalScrollView {
    Context mContext;
    int bgRes = -1;
    int textColorRes = -1;
    int textSize = -1;
    int[] textPadding;
    int[] textMargin;


    ChildClick childClick;
    LinearLayout layoutGroup;

    public HorizontalScrollSelfView(Context context) {
        super(context);
        initView(context);
    }

    public HorizontalScrollSelfView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public HorizontalScrollSelfView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        mContext = context;
        layoutGroup = new LinearLayout(mContext);
        layoutGroup.setOrientation(LinearLayout.HORIZONTAL);
        layoutGroup.setGravity(Gravity.CENTER_VERTICAL);
        this.addView(layoutGroup, FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.MATCH_PARENT);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        //请求父类不要拦截触摸事件
        getParent().requestDisallowInterceptTouchEvent(true);
        return super.onInterceptTouchEvent(ev);
    }

    public HorizontalScrollSelfView addChildView(String... childNames) {
        addChildView(Arrays.asList(childNames));
        return this;
    }

    public HorizontalScrollSelfView addChildView(List<String> childNames) {
        for (int i = 0; i < childNames.size(); i++) {
            final String childName = childNames.get(i);
            if (!TextUtils.isEmpty(childName)) {
                TextView textView = new TextView(mContext);
                textView.setText(childName);
                if (textPadding != null) {
                    textView.setPadding(textPadding[0], textPadding[1], textPadding[2], textPadding[3]);
                }
                if (textSize > 0) {
                    textView.setTextSize(textSize);
                }
                if (bgRes > 0) {
                    textView.setBackgroundResource(bgRes);
                }
                if (textColorRes >= 0) {
                    textView.setTextColor(textColorRes);
                }
                textView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (childClick != null) {
                            childClick.clickPosition(childName);
                        }
                    }
                });
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                if (textMargin != null) {
                    params.setMargins(textMargin[0], textMargin[1], textMargin[2], textMargin[3]);
                }
                layoutGroup.addView(textView, params);
            }
        }
        return this;
    }

    public int getBgRes() {
        return bgRes;
    }

    public HorizontalScrollSelfView setBgRes(int bgRes) {
        this.bgRes = bgRes;
        return this;
    }

    public int getTextColorRes() {
        return textColorRes;
    }

    public HorizontalScrollSelfView setTextColorRes(int textColorRes) {
        this.textColorRes = textColorRes;
        return this;
    }

    public int getTextSize() {
        return textSize;
    }

    public HorizontalScrollSelfView setTextSize(int textSize) {
        this.textSize = textSize;
        return this;
    }

    public int[] getTextPadding() {
        return textPadding;
    }

    public HorizontalScrollSelfView setTextPadding(int[] textPadding) {
        this.textPadding = textPadding;
        return this;
    }

    public int[] getTextMargin() {
        return textMargin;
    }

    public HorizontalScrollSelfView setTextMargin(int[] textMargin) {
        this.textMargin = textMargin;
        return this;
    }

    public HorizontalScrollSelfView addChildClickLisenter(ChildClick childClick) {
        this.childClick = childClick;
        return this;
    }

    public interface ChildClick {
        void clickPosition(String position);
    }
}
