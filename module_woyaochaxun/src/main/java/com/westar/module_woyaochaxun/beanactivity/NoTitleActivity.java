package com.westar.module_woyaochaxun.beanactivity;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.qmuiteam.qmui.widget.QMUITopBar;
import com.westar.library_base.base.BaseActivity;
import com.westar.module_woyaochaxun.R;

public abstract class NoTitleActivity extends BaseActivity {

    public QMUITopBar topbar;

    @Override
    public void setContentView(int layoutResID) {
        FrameLayout rootView = (FrameLayout) LayoutInflater.from(this).inflate(R.layout.activity_no_title, null);
        View childView = LayoutInflater.from(this).inflate(layoutResID, rootView, false);
        rootView.addView(childView);
        topbar = rootView.findViewById(R.id.topbar);
        super.setContentView(rootView);
    }
}
