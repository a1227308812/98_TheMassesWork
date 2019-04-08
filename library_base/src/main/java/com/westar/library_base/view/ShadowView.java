package com.westar.library_base.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.westar.masseswork_98.library_base.R;


/**
 * Created by ZWP on 2019/4/1 16:38.
 * 描述：自定义阴影view
 */
public class ShadowView extends View {
    /**
     * 阴影宽度 默认10dp
     */
    private int shadowWidth = 10;
    /**
     * 阴影颜色  默认颜色
     */
    private int shadowColor = Color.LTGRAY;
    /**
     * 背景图片
     */
    int imageresource = -1;
    /**
     * 画背景图片的笔
     */
    Paint drawablePaint;
    /**
     * 画阴影的笔
     */
    Paint shadowPaint;
    /**
     * 图片绘制区域
     */
    RectF drawableRectF;

    public ShadowView(Context context) {
        this(context, null);
    }

    public ShadowView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShadowView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        //关闭硬件加速
        setLayerType(LAYER_TYPE_SOFTWARE, null);//对单独的View在运行时阶段禁用硬件加速
        //画图片的笔
        drawablePaint = new Paint();
        drawablePaint.setStyle(Paint.Style.FILL);
        drawablePaint.setAntiAlias(true);//开启抗锯齿
        drawablePaint.setDither(true);//开启防抖动
        drawableRectF = new RectF();

        //画阴影的笔
        shadowPaint = new Paint();
        shadowPaint.setColor(Color.WHITE);
        shadowPaint.setStyle(Paint.Style.FILL);
        shadowPaint.setAntiAlias(true);//开启抗锯齿
        shadowPaint.setDither(true);//开启防抖动
//        shadowPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));
        shadowPaint.setShadowLayer(shadowWidth, 0, 0, shadowColor);

        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ShadowView);
            if (a.hasValue(R.styleable.ShadowView_shadowSrc)) {
                imageresource = a.getResourceId(R.styleable.ShadowView_shadowSrc, -1);
            }
            shadowWidth = a.getDimensionPixelSize(R.styleable.ShadowView_shadowRound, shadowWidth);
            if (a.hasValue(R.styleable.ShadowView_shadowColor)) {
                shadowColor = a.getColor(R.styleable.ShadowView_shadowColor, Color.LTGRAY);
            }
        } else {
            float density = context.getResources().getDisplayMetrics().density;
            shadowWidth = (int) (shadowWidth * density);
        }

    }

//    /**
//     * 设置背景图片
//     *
//     * @param resId
//     */
//    public void setImageResource(@DrawableRes int resId) {
//        imageresource = resId;
//    }
//
//
//    /**
//     * 设置阴影颜色
//     *
//     * @param color
//     */
//    public void setImageShadowColor(@ColorInt int color) {
//        this.shadowColor = color;
//    }
//
//    /**
//     * 设置阴影宽度
//     *
//     * @param radius
//     */
//    public void setImageRadius(int radius) {
//        this.shadowWidth = radius;
//    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        //确定图片绘制范围
        drawableRectF.left = shadowWidth;
        drawableRectF.top = shadowWidth;
        drawableRectF.right = getWidth() - shadowWidth;
        drawableRectF.bottom = getHeight() - shadowWidth;
        //画带阴影的圆角矩形区域
        canvas.drawRoundRect(drawableRectF, shadowWidth, shadowWidth, shadowPaint);
        if (imageresource != -1){
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), imageresource);
            //画图片
            canvas.drawBitmap(bitmap, null, drawableRectF, drawablePaint);
            //释放bitmap对象
            bitmap.recycle();
        }
        //保存画布
        canvas.save();
    }

    /**
     * 把rgb色值转化为int型色值
     *
     * @param color
     * @return
     */
    public int getDarkerColor(int color) {
        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv);
        hsv[1] = hsv[1] + 0.1f;
        hsv[2] = hsv[2] - 0.1f;
        int darkerColor = Color.HSVToColor(hsv);
        return darkerColor;
    }
}