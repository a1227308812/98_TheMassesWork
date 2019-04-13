package com.westar.library_base.view.shadowView;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;

import java.io.Serializable;

/**
 * Created by ZWP on 2019/4/12 17:04.
 * 描述：阴影参数对象
 */
public class ShadowProperty implements Serializable {
    public static final int ALL = 0x1111;
    public static final int LEFT = 0x0001;
    public static final int TOP = 0x0010;
    public static final int RIGHT = 0x0100;
    public static final int BOTTOM = 0x1000;

    /**
     * 阴影颜色
     */
    private int shadowColor = Color.WHITE;
    /**
     * 阴影半径 默认0dp
     */
    private int shadowRadius;
    /**
     * 圆角宽度 默认0dp
     */
    private int roundwWidth;
    /**
     * 阴影x偏移
     */
    private int shadowDx;
    /**
     * 阴影y偏移
     */
    private int shadowDy;

    /**
     * 阴影边  还未开始使用 未写逻辑代码
     */
    private int shadowSide = ALL;
    /**
     * 控件宽度
     */
    private float layoutWidth;
    /**
     * 控件高度
     */
    private float layoutHitht;

    /**
     * 背景图片
     */
    private Drawable layoutBg;

    public int getRoundwWidth() {
        return roundwWidth;
    }

    public ShadowProperty setRoundwWidth(int roundwWidth) {
        if (roundwWidth > 0)
            this.roundwWidth = dp2px(roundwWidth);
        return this;
    }

    public float getLayoutWidth() {
        return layoutWidth;
    }

    public ShadowProperty setLayoutWidth(float layoutWidth) {
        this.layoutWidth = layoutWidth;
        return this;
    }

    public float getLayoutHitht() {
        return layoutHitht;
    }

    public ShadowProperty setLayoutHitht(float layoutHitht) {
        this.layoutHitht = layoutHitht;
        return this;
    }

    public Drawable getLayoutBg() {
        return layoutBg;
    }

    public ShadowProperty setLayoutBg(Drawable layoutBg) {
        this.layoutBg = layoutBg;
        return this;
    }

    public int getShadowSide() {
        return shadowSide;
    }

    public ShadowProperty setShadowSide(int shadowSide) {
        if (shadowSide > 0)
            this.shadowSide = dp2px(shadowSide);
        return this;
    }

    public int getShadowOffset() {
        return getShadowOffsetHalf() * 2;
    }

    public int getShadowOffsetHalf() {
        return 0 >= shadowRadius ? 0 : Math.max(shadowDx, shadowDy) + shadowRadius;
    }

    public int getShadowColor() {
        return shadowColor;
    }

    public ShadowProperty setShadowColor(int shadowColor) {
        this.shadowColor = shadowColor;
        return this;
    }

    public int getShadowRadius() {
        return shadowRadius;
    }

    /**
     * @param shadowRadius 单位dp
     * @return
     */
    public ShadowProperty setShadowRadius(int shadowRadius) {
        if (shadowRadius > 0)
            this.shadowRadius = dp2px(shadowRadius);
        return this;
    }

    public int getShadowDx() {
        return shadowDx;
    }

    public ShadowProperty setShadowDx(int shadowDx) {
        if (shadowDx > 0)
            this.shadowDx = dp2px(shadowDx);
        return this;
    }

    public int getShadowDy() {
        return shadowDy;
    }

    public ShadowProperty setShadowDy(int shadowDy) {
        if (shadowDy > 0)
            this.shadowDy = dp2px(shadowDy);
        return this;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     *
     * @param dpValue 虚拟像素
     * @return 像素
     */
    private int dp2px(float dpValue) {
        return (int) (0.5f + dpValue * Resources.getSystem().getDisplayMetrics().density);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     *
     * @param pxValue 像素
     * @return 虚拟像素
     */
    private float px2dp(int pxValue) {
        return (pxValue / Resources.getSystem().getDisplayMetrics().density);
    }

    /**
     * 把rgb色值转化为int型色值
     *
     * @param color
     * @return
     */
    private int getDarkerColor(int color) {
        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv);
        hsv[1] = hsv[1] + 0.1f;
        hsv[2] = hsv[2] - 0.1f;
        int darkerColor = Color.HSVToColor(hsv);
        return darkerColor;
    }

}
