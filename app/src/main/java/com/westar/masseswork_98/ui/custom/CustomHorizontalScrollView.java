package com.westar.masseswork_98.ui.custom;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.widget.ImageViewCompat;
import android.support.v7.widget.AppCompatImageView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;
import com.uuzuche.lib_zxing.DisplayUtil;
import com.westar.Config;
import com.westar.library_base.glide.GlideApp;
import com.westar.masseswork_98.R;
import com.westar.masseswork_98.been.MeCardInfo;

import java.util.concurrent.TimeUnit;

import io.reactivex.functions.Consumer;

/**
 * Created by ZWP on 2019/4/30 15:33.
 * 描述：自定义横向滑动控件，滑动事件自己处理
 */
public class CustomHorizontalScrollView extends HorizontalScrollView {
    Context mContext;

    public CustomHorizontalScrollView(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public CustomHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    public CustomHorizontalScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
    }

    LinearLayout rootLayout;

    private void initView() {
        rootLayout = new LinearLayout(mContext);
        rootLayout.setOrientation(LinearLayout.HORIZONTAL);
        addView(rootLayout);
    }

    /**
     * 初始化背景颜色
     *
     * @param targetView
     */
    private void setChildBackgroundDrawable(View targetView, String[] colors) {
        // 创建渐变的shape drawable
//        int colors[] = {0xff255779, 0xff3e7492, 0xffa6c0cd};//分别为开始颜色，中间夜色，结束颜色
        int colorsRes[] = new int[colors.length];
        for (int i = 0; i < colors.length; i++) {
            colorsRes[i] = Color.parseColor(colors[i]);
        }
//        int colorsRes[] = {Color.parseColor("#5698f0"),
//                Color.parseColor("#5289e7"),
//                Color.parseColor("#4a6dd5")};
        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, colorsRes);
        gradientDrawable.setCornerRadius(DisplayUtil.dip2px(mContext, 10));
        targetView.setBackground(gradientDrawable);
    }

    /**
     * 动态添加子view
     *
     * @param meCardInfo
     * @param itemViewClick
     * @return
     */
    public CustomHorizontalScrollView addChildView(final MeCardInfo meCardInfo, final ItemViewClick itemViewClick) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_me_card_menu, null, false);
        AppCompatImageView cardtypeView = view.findViewById(R.id.iv_card_type);
        TextView titleView = view.findViewById(R.id.tv_titlle);
        TextView tv_describle = view.findViewById(R.id.tv_describle);
        TextView tv_describle2 = view.findViewById(R.id.tv_describle2);
        TextView tv_authentication_status = view.findViewById(R.id.tv_authentication_status);

        GlideApp.with(mContext).load(meCardInfo.getTypeUrl()).into(cardtypeView);
        if (!TextUtils.isEmpty(meCardInfo.getTitle())) {
            titleView.setText(meCardInfo.getTitle());
        }
        if (!TextUtils.isEmpty(meCardInfo.getDescrible())) {
            tv_describle.setText(meCardInfo.getDescrible());
        }
        if (!TextUtils.isEmpty(meCardInfo.getDescrible2())) {
            tv_describle2.setText(meCardInfo.getDescrible2());
        }
        if (!TextUtils.isEmpty(meCardInfo.getAuthenticationStatus())) {
            tv_authentication_status.setText(meCardInfo.getAuthenticationStatus());
        }

        setChildBackgroundDrawable(view, meCardInfo.getColors());

        RxView.clicks(view).throttleFirst(Config.WINDOWDURATION, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        itemViewClick.onClick(meCardInfo);
                    }
                });

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(DisplayUtil.dip2px(mContext, 230), LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMarginEnd(DisplayUtil.dip2px(mContext, 10));
        rootLayout.addView(view, layoutParams);

        return this;
    }

    public void clearChildView() {
        rootLayout.removeAllViews();
        requestLayout();
    }

    public interface ItemViewClick {
        void onClick(MeCardInfo meCardInfo);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        //请求父类不要拦截触摸事件
        getParent().requestDisallowInterceptTouchEvent(true);
        return super.onInterceptTouchEvent(ev);
    }
}
