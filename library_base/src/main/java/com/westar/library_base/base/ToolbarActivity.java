package com.westar.library_base.base;

import android.support.constraint.ConstraintLayout;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.westar.library_base.utils.LLog;
import com.westar.library_base.view.TopBarLayout;
import com.westar.masseswork_98.library_base.R;

/**
 * Created by ZWP on 2019/3/28 17:17.
 * 描述：带头部的activity超类
 */
public abstract class ToolbarActivity extends BaseActivity {
    public TopBarLayout topBarLayout;

    @Override
    public void setContentView(int layoutResID) {
        ViewGroup rootView = (ViewGroup) LayoutInflater.from(this).inflate(R.layout.activity_toolbar, null);
        ViewGroup childView = (ViewGroup) LayoutInflater.from(this).inflate(layoutResID, null);
        rootView.addView(childView);
        topBarLayout = rootView.findViewById(R.id.toolbar_layout);
        if (topBarLayout != null) {
            topBarLayout.setTitle(setBarTitle());
//            topBarLayout.post(new Runnable() {
//                @Override
//                public void run() {
//                    int statueBarHight = QMUIStatusBarHelper.getStatusbarHeight(mContext);
//                    int toolbarlayoutHight = topBarLayout.getHeight();
//                    //动态配置topbar的高度
//                    ViewGroup.LayoutParams layoutParams =  topBarLayout.getLayoutParams();
//                    layoutParams.height = toolbarlayoutHight + statueBarHight;
//                    topBarLayout.setLayoutParams(layoutParams);
//
//                    ViewGroup.LayoutParams topParams = topBarLayout.getmTopBar().getLayoutParams();
//                    topParams.height = toolbarlayoutHight;
//                    topBarLayout.getmTopBar().setLayoutParams(topParams);
//                }
//            });
            DisplayMetrics metrics = QMUIDisplayHelper.getDisplayMetrics(mContext);
            LLog.e("ccc", "--------161  density-------------  " + metrics.density);
            LLog.e("ccc", "--------161  scaledDensity-------------  " + metrics.scaledDensity);
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
