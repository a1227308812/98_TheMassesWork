package com.westar.library_base.view.shadowView;

import android.graphics.*;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by ZWP on 2019/4/12 11:04.
 * 描述：画背景
 */
public class ShadowViewDrawable extends Drawable {
    /**
     * 背景图片
     */
    Drawable imageresource;
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

    ShadowProperty shadowProperty;

    public ShadowViewDrawable(ShadowProperty shadowProperty) {

        //画图片的笔
        drawablePaint = new Paint();
        drawablePaint.setStyle(Paint.Style.FILL);
        drawablePaint.setAntiAlias(true);//开启抗锯齿
        drawablePaint.setDither(true);//开启防抖动


        //画阴影的笔
        shadowPaint = new Paint();
        shadowPaint.setColor(Color.WHITE);
        shadowPaint.setStyle(Paint.Style.FILL);
        shadowPaint.setAntiAlias(true);//开启抗锯齿
        shadowPaint.setDither(true);//开启防抖动

        if (shadowProperty != null) {
            this.shadowProperty = shadowProperty;
            imageresource = shadowProperty.getLayoutBg();
            if (shadowProperty.getLayoutWidth() > 0 && shadowProperty.getLayoutHitht() > 0) {
                drawableRectF = new RectF();
                //1、确定图片绘制范围
                drawableRectF.left = shadowProperty.getShadowRadius();
                drawableRectF.top = shadowProperty.getShadowRadius();
                drawableRectF.right = shadowProperty.getLayoutWidth() - shadowProperty.getShadowRadius();
                drawableRectF.bottom = shadowProperty.getLayoutHitht() - shadowProperty.getShadowRadius();
            }
            if (shadowProperty.getShadowRadius() != 0)
                shadowPaint.setShadowLayer(shadowProperty.getShadowRadius(), shadowProperty.getShadowDx(), shadowProperty.getShadowDy(), shadowProperty.getShadowColor());
        }
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        if (drawableRectF != null){
            //2、画带阴影的圆角矩形区域
            canvas.drawRoundRect(drawableRectF, shadowProperty.getRoundwWidth(), shadowProperty.getRoundwWidth(), shadowPaint);
            //3、判断是否画阴影
            if (imageresource != null) {
                Bitmap bitmap = drawable2Bitmap(imageresource);
                //3.1、画图片
                canvas.drawBitmap(bitmap, null, drawableRectF, drawablePaint);
//            //释放bitmap对象
//            bitmap.recycle();
            }
            //4、保存画布
            canvas.save();
        }
    }

    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {

    }

    /**
     * drawable转bitmap
     *
     * @param drawable
     * @return
     */
    private Bitmap drawable2Bitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        } else if (drawable instanceof NinePatchDrawable) {
            Bitmap bitmap = Bitmap
                    .createBitmap(
                            drawable.getIntrinsicWidth(),
                            drawable.getIntrinsicHeight(),
                            drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                                    : Bitmap.Config.RGB_565);
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
                    drawable.getIntrinsicHeight());
            drawable.draw(canvas);
            return bitmap;
        } else {
            return null;
        }
    }

    @Override
    public int getOpacity() {
        return PixelFormat.UNKNOWN;
    }

}
