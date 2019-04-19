package com.westar.library_base.supertext;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.coorchice.library.ImageEngine;
import com.coorchice.library.image_engine.Engine;
import com.westar.library_base.glide.GlideApp;

/**
 * Created by ZWP on 2019/4/18 20:53.
 * 描述：SuperTextView的第三方加载框架
 */
public class GlideEngine implements Engine {

    private Context context;

    public GlideEngine(Context context) {
        this.context = context;
    }

    @Override
    public void load(String url, final ImageEngine.Callback callback) {

        GlideApp.with(context).asDrawable().load(url).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                // 主要是通过callback返回Drawable对象给SuperTextView
                callback.onCompleted(context.getResources().getDrawable(com.chad.library.R.drawable.sample_footer_loading));
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                // 主要是通过callback返回Drawable对象给SuperTextView
                callback.onCompleted(resource);
                return false;
            }
        });
    }
}