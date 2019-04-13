package com.westar.library_base.view.shadowView;

import android.view.View;

/**
 * Created by ZWP on 2019/4/13 14:41.
 * 描述：阴影设置帮助类
 */
public class ShadowHelper {

    public static void bindView(final View view, final ShadowProperty shadowProperty) {
        if (shadowProperty != null) {
            view.post(new Runnable() {
                @Override
                public void run() {
                    shadowProperty.setLayoutWidth(view.getWidth());
                    shadowProperty.setLayoutHitht(view.getHeight());
                    view.setBackgroundDrawable(new ShadowViewDrawable(shadowProperty));
                }
            });
        }
    }
}
