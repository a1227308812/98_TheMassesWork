package com.westar.library_base.view;


import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.annotation.LayoutRes;
import android.support.v4.app.ActivityCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.blankj.utilcode.util.Utils;

/**
 * Created by ZWP on 2019/6/12 13:29.
 * 描述：
 * 脑壳痛 还是自己写的封装安逸些
 * 1、满足自由设置显示和隐藏的动画
 * 2、满足自由设置弹出窗体的宽高
 * 3、满足自由设置弹出位置
 * 4、满足点击事件传递
 * 5、满足自由控制弹出窗体内容
 */
public class BasePopup {
    Context mContext;
    PopupWindow mPopupWindow;
    View rootView;
    int popWidth = getScreenWidth() * 3 / 4;
    int popHight = getScreenHeight() / 2;

    public PopupWindow getmPopupWindow() {
        return mPopupWindow;
    }

    public void setmPopupWindow(PopupWindow mPopupWindow) {
        this.mPopupWindow = mPopupWindow;
    }

    public BasePopup(Context mContext) {
        this.mContext = mContext;
    }

    public View getRootView() {
        return rootView;
    }

    public BasePopup setRootView(View rootView) {
        this.rootView = rootView;
        return this;
    }

    public BasePopup setRootView(@LayoutRes int rootViewRes) {
        this.rootView = LayoutInflater.from(mContext).inflate(rootViewRes, null, false);
        return this;
    }

    public int getPopWidth() {
        return popWidth;
    }

    public BasePopup setPopWidth(int popWidth) {
        this.popWidth = popWidth;
        return this;
    }

    public int getPopHight() {
        return popHight;
    }

    public BasePopup setPopHight(int popHight) {
        this.popHight = popHight;
        return this;
    }

    public BasePopup setBackground(Drawable drawable) {
        if (null != rootView) {
            rootView.setBackground(drawable);
        }
        return this;
    }

    public BasePopup creatPop() {
        if (null != rootView) {
            mPopupWindow = new PopupWindow(rootView, popWidth, popHight, true);
            mPopupWindow.setOutsideTouchable(true);
            mPopupWindow.setTouchable(true);
            mPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        return this;
    }

    /**
     * 显示提供了两种形式：
     　　showAtLocation()显示在指定位置，有两个方法重载：
     public void showAtLocation(View parent, int gravity, int x, int y)
     public void showAtLocation(IBinder token, int gravity, int x, int y)

     　　showAsDropDown()显示在一个参照物View的周围，有三个方法重载：
     public void showAsDropDown(View anchor)
     public void showAsDropDown(View anchor, int xoff, int yoff)
     public void showAsDropDown(View anchor, int xoff, int yoff, int gravity)
     　　最后一种带Gravity参数的方法是API 19新引入的。
     */

    /**
     * 相对父控件自定义位置显示pop
     *
     * @param popParentView PopupWindow的父View
     * @param popGravity    PopupWindow相对父View的位置
     */
    public PopupWindow show(View popParentView, int popGravity) {
        LayoutGravity layoutGravity = new LayoutGravity(popGravity);
        mPopupWindow.showAsDropDown(popParentView,
                layoutGravity.getOffset(popParentView, mPopupWindow)[0],
                layoutGravity.getOffset(popParentView, mPopupWindow)[1]);
        return mPopupWindow;
    }

    /**
     * 居中显示pop
     */
    public PopupWindow showCenter(View popParentView) {
        mPopupWindow.showAtLocation(popParentView, Gravity.CENTER, 0, 0);
        return mPopupWindow;
    }

    /**
     * Return the width of screen, in pixel.
     *
     * @return the width of screen, in pixel
     */
    private int getScreenWidth() {
        WindowManager wm = (WindowManager) Utils.getApp().getSystemService(Context.WINDOW_SERVICE);
        Point point = new Point();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            //noinspection ConstantConditions
            wm.getDefaultDisplay().getRealSize(point);
        } else {
            //noinspection ConstantConditions
            wm.getDefaultDisplay().getSize(point);
        }
        return point.x;
    }

    /**
     * Return the height of screen, in pixel.
     *
     * @return the height of screen, in pixel
     */
    private int getScreenHeight() {
        WindowManager wm = (WindowManager) Utils.getApp().getSystemService(Context.WINDOW_SERVICE);
        Point point = new Point();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            //noinspection ConstantConditions
            wm.getDefaultDisplay().getRealSize(point);
        } else {
            //noinspection ConstantConditions
            wm.getDefaultDisplay().getSize(point);
        }
        return point.y;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    private int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    private int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * pop的位置控制类
     */
    public static class LayoutGravity {
        private int layoutGravity;
        //位置常量
        public static final int ALIGN_LEFT = 0x1;
        public static final int ALIGN_ABOVE = 0x2;
        public static final int ALIGN_RIGHT = 0x4;
        public static final int ALIGN_BOTTOM = 0x8;
        public static final int TO_LEFT = 0x10;
        public static final int TO_ABOVE = 0x20;
        public static final int TO_RIGHT = 0x40;
        public static final int TO_BOTTOM = 0x80;
        public static final int CENTER_HORI = 0x100;
        public static final int CENTER_VERT = 0x200;

        public LayoutGravity(int gravity) {
            layoutGravity = gravity;
        }

        public int getLayoutGravity() {
            return layoutGravity;
        }

        public void setLayoutGravity(int gravity) {
            layoutGravity = gravity;
        }

        public void setHoriGravity(int gravity) {
            layoutGravity &= (0x2 + 0x8 + 0x20 + 0x80 + 0x200);
            layoutGravity |= gravity;
        }

        public void setVertGravity(int gravity) {
            layoutGravity &= (0x1 + 0x4 + 0x10 + 0x40 + 0x100);
            layoutGravity |= gravity;
        }

        public boolean isParamFit(int param) {
            return (layoutGravity & param) > 0;
        }

        public int getHoriParam() {
            for (int i = 0x1; i <= 0x100; i = i << 2)
                if (isParamFit(i))
                    return i;
            return ALIGN_LEFT;
        }

        public int getVertParam() {
            for (int i = 0x2; i <= 0x200; i = i << 2)
                if (isParamFit(i))
                    return i;
            return TO_BOTTOM;
        }

        public int[] getOffset(View anchor, PopupWindow window) {
            int anchWidth = anchor.getWidth();
            int anchHeight = anchor.getHeight();

            int winWidth = window.getWidth();
            int winHeight = window.getHeight();
            View view = window.getContentView();
            if (winWidth <= 0)
                winWidth = view.getWidth();
            if (winHeight <= 0)
                winHeight = view.getHeight();

            int xoff = 0;
            int yoff = 0;

            switch (getHoriParam()) {
                case ALIGN_LEFT:
                    xoff = 0;
                    break;
                case ALIGN_RIGHT:
                    xoff = anchWidth - winWidth;
                    break;
                case TO_LEFT:
                    xoff = -winWidth;
                    break;
                case TO_RIGHT:
                    xoff = anchWidth;
                    break;
                case CENTER_HORI:
                    xoff = (anchWidth - winWidth) / 2;
                    break;
                default:
                    break;
            }
            switch (getVertParam()) {
                case ALIGN_ABOVE:
                    yoff = -anchHeight;
                    break;
                case ALIGN_BOTTOM:
                    yoff = -winHeight;
                    break;
                case TO_ABOVE:
                    yoff = -anchHeight - winHeight;
                    break;
                case TO_BOTTOM:
                    yoff = 0;
                    break;
                case CENTER_VERT:
                    yoff = (-winHeight - anchHeight) / 2;
                    break;
                default:
                    break;
            }
            return new int[]{xoff, yoff};
        }
    }
}
