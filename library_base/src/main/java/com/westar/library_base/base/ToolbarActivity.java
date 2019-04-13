package com.westar.library_base.base;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.widget.QMUICollapsingTopBarLayout;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.westar.library_base.utils.LLog;
import com.westar.masseswork_98.library_base.R;

/**
 * Created by ZWP on 2019/3/28 17:17.
 * 描述：带头部的activity超类
 */
public abstract class ToolbarActivity extends BaseActivity {
    public QMUITopBarLayout topbar;

    @Override
    public void setContentView(int layoutResID) {
        LinearLayout rootView = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.activity_toolbar, null);
        View childView = LayoutInflater.from(this).inflate(layoutResID, rootView, false);
        rootView.addView(childView);
        topbar = rootView.findViewById(R.id.topbar);
        if (topbar != null) {
            topbar.setPadding(0, QMUIStatusBarHelper.getStatusbarHeight(mContext), 0, 0);
            topbar.setTitle(setBarTitle());
            DisplayMetrics metrics = QMUIDisplayHelper.getDisplayMetrics(mContext);
            LLog.e("ccc","--------161  density-------------  " + metrics.density);
            LLog.e("ccc","--------161  scaledDensity-------------  " + metrics.scaledDensity);
        }
        super.setContentView(rootView);

    }


    /**
     * 设置标题
     *
     * @return
     */
    public abstract String setBarTitle();
}
