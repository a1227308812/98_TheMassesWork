package com.westar.library_base.banner;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.qmuiteam.qmui.widget.QMUIRadiusImageView;
import com.uuzuche.lib_zxing.DisplayUtil;
import com.westar.library_base.glide.GlideApp;
import com.westar.masseswork_98.library_base.R;
import com.youth.banner.loader.ImageLoader;
import com.youth.banner.loader.ImageLoaderInterface;

/**
 * Created by ZWP on 2019/4/24 14:46.
 * 描述：banner图片加载器
 */
public class GlideImageLoader implements ImageLoaderInterface<QMUIRadiusImageView> {
    @Override
    public void displayImage(Context context, Object path, QMUIRadiusImageView imageView) {
        /**
         注意：
         1.图片加载器由自己选择，这里不限制，只是提供几种使用方法
         2.返回的图片路径为Object类型，由于不能确定你到底使用的那种图片加载器，
         传输的到的是什么格式，那么这种就使用Object接收和返回，你只需要强转成你传输的类型就行，
         切记不要胡乱强转！
         */
        //Glide 加载图片简单用法
        GlideApp.with(context).load(path).centerCrop()
                .placeholder(R.drawable.image_loading)
                .error(R.drawable.image_load_error)
                .into(imageView);

//        //用fresco加载图片简单用法，记得要写下面的createImageView方法
//        Uri uri = Uri.parse((String) path);
//        imageView.setImageURI(uri);
    }

    @Override
    public QMUIRadiusImageView createImageView(Context context) {
        QMUIRadiusImageView imageView = new QMUIRadiusImageView(context);
        imageView.setCornerRadius(DisplayUtil.dip2px(context, 10));
        return imageView;
    }
}
