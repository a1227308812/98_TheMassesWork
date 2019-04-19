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
                    if (shadowProperty != null) {
                        int shadowRadiu = shadowProperty.getShadowRadius();
                        int newPaddingBottom = 0;
                        int newPaddingTop = 0;
                        int newPaddingLift = 0;
                        int newPaddingRight = 0;
                        if (shadowRadiu > 0 && view.getPaddingBottom() > shadowRadiu) {
                            newPaddingBottom = view.getPaddingBottom() - shadowRadiu;
                        }
                        if (shadowRadiu > 0 && view.getPaddingTop() > shadowRadiu) {
                            newPaddingTop = view.getPaddingTop() - shadowRadiu;
                        }
                        if (shadowRadiu > 0 && view.getPaddingLeft() > shadowRadiu) {
                            newPaddingLift = view.getPaddingLeft() - shadowRadiu;
                        }
                        if (shadowRadiu > 0 && view.getPaddingRight() > shadowRadiu) {
                            newPaddingRight = view.getPaddingRight() - shadowRadiu;
                        }
                        view.setPadding(newPaddingLift, newPaddingTop, newPaddingRight, newPaddingBottom);
                    }
                    shadowProperty.setLayoutWidth(view.getWidth());
                    shadowProperty.setLayoutHitht(view.getHeight());
                    view.setBackgroundDrawable(new ShadowViewDrawable(shadowProperty));
                }
            });
        }
    }
}
