package com.westar.library_base.supertext;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
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

        GlideApp.with(context).asDrawable().load(url).into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                // 主要是通过callback返回Drawable对象给SuperTextView
                callback.onCompleted(resource);
            }
        });

    }
}