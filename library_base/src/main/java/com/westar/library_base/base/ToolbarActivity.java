package com.westar.library_base.base;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.qmuiteam.qmui.widget.QMUITopBar;
import com.westar.masseswork_98.library_base.R;

/**
 * Created by ZWP on 2019/3/28 17:17.
 * 描述：带头部的activity超类
 */
public abstract class ToolbarActivity extends BaseActivity {
    public QMUITopBar topbar;

    @Override
    public void setContentView(int layoutResID) {
        LinearLayout rootView = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.activity_toolbar, null);
        View childView = LayoutInflater.from(this).inflate(layoutResID, rootView, false);
        rootView.addView(childView);
        topbar = rootView.findViewById(R.id.topbar);
        if (topbar != null) {
            topbar.setTitle(setBarTitle());
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
