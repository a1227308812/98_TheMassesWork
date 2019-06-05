package com.westar.module_woyaobanshi.ui.activity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.NestedScrollView;
import android.view.View;
import android.widget.FrameLayout;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.uuzuche.lib_zxing.DisplayUtil;
import com.westar.library_base.base.BasePresenter;
import com.westar.library_base.base.ToolbarActivity;
import com.westar.library_base.http.been.HttpRequest;
import com.westar.library_base.view.CustomHorizonTabLayout;
import com.westar.library_base.view.HorizonScrollSelfTabSegment;
import com.westar.module_woyaobanshi.R;
import com.westar.module_woyaobanshi.mvp.contract.ItemDetailContract;
import com.westar.module_woyaobanshi.mvp.presenter.ItemDetailPresenter;
import com.westar.module_woyaobanshi.ui.fragment.ApplicationMaterialFragment;
import com.westar.module_woyaobanshi.ui.fragment.ItemBaseInfoFragment;
import com.westar.module_woyaobanshi.ui.fragment.WorkFlowChartFragment;

/**
 * Created by ZWP on 2019/5/30 14:34.
 * 描述：办事指南详情页面
 */
public class ItemDetailActivity extends ToolbarActivity implements ItemDetailContract.View {
    FrameLayout flJbxx;
    FrameLayout flBslct;
    FrameLayout flSqcl;
    FrameLayout flSfbz;
    FrameLayout flJgyb;
    FrameLayout flSdyj;
    HorizonScrollSelfTabSegment childMenu;
    HorizonScrollSelfTabSegment childMenuStick;
    NestedScrollView nsvItemDetail;

    ItemDetailPresenter presenter;
    String[] childMenuTitles = {"基本信息", "办事流程图", "申请材料", "收费标准", "结果样本", "设定依据"};

    @Override
    public String setBarTitle() {
        return "办事指南";
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_item_detail;
    }

    @Override
    protected void findId() {
        flJbxx = findViewById(R.id.fl_jbxx);
        flBslct = findViewById(R.id.fl_bslct);
        flSqcl = findViewById(R.id.fl_sqcl);
        flSfbz = findViewById(R.id.fl_sfbz);
        flJgyb = findViewById(R.id.fl_jgyb);
        flSdyj = findViewById(R.id.fl_sdyj);
        childMenu = findViewById(R.id.hsv_item_menu);
        childMenuStick = findViewById(R.id.hsv_item_menu_stick);
        nsvItemDetail = findViewById(R.id.nsv_item_detail);

    }

    @Override
    protected void initView() {

        initTabViewAndStickView(childMenu);
        initTabViewAndStickView(childMenuStick);
//        nsvItemDetail.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                float downY = 0;
//                float upY = 0;
//                float moveY = 0;
//                switch (event.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//                        downY = event.getY();
//                        Log.e("ddd","downY" + downY);
//                        break;
//                    case MotionEvent.ACTION_UP:
//                        upY = event.getY();
//                        Log.e("ddd","upY" + upY);
//                        break;
//                    case MotionEvent.ACTION_MOVE:
//                        moveY = event.getY();
//                        Log.e("ddd","moveY" + event.getY());
//                        //向上滑动   隐藏toolbar
//                        if (moveY - downY < 0) {
//                            Log.e("ddd","moveY - downY" + (moveY - downY));
//                            float scale = (float) topBarLayout.getmTopBar().getMeasuredHeight() - Math.abs(moveY - downY);
//                            float alpha = 0;
//                            DecimalFormat df = new DecimalFormat("0.00");//设置保留位数
//                            if (scale > 0) {
//                                alpha = Float.valueOf(df.format(scale / topBarLayout.getmTopBar().getMeasuredHeight()));
//                            }
//                            Log.e("ddd", "scale = " + scale);
//                            Log.e("ddd", "alpha = " + alpha);
//                            topBarLayout.getmTopBar().setAlpha(alpha);
//                        }
//                        //向下滑动  显示toolbar
//                        if (moveY - downY > 0) {
//                            Log.e("ddd","moveY - downY" + (moveY - downY));
//                            float scale = (float) topBarLayout.getmTopBar().getMeasuredHeight() - Math.abs(moveY - downY);
//                            float alpha = 0;
//                            DecimalFormat df = new DecimalFormat("0.00");//设置保留位数
//                            if (scale > 0) {
//                                alpha = Float.valueOf(df.format(Math.abs(moveY - downY) / (float) topBarLayout.getmTopBar().getMeasuredHeight()));
//                            }
//                            Log.e("ddd", "scale = " + scale);
//                            Log.e("ddd", "alpha = " + alpha);
//                            topBarLayout.getmTopBar().setAlpha(alpha);
//                        }
//                        break;
//                }
//                return false;
//            }
//        });
        nsvItemDetail.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView nestedScrollView, int x, int y, int oldx, int oldy) {
                if (y > childMenu.getY() + 1) {
                    childMenuStick.setVisibility(View.VISIBLE);
                    if (childMenu.getSelectedIndex() != -1){
                        childMenuStick.selectTab(childMenu.getSelectedIndex());
                    }
                } else {
                    childMenuStick.setVisibility(View.GONE);
                    if (childMenuStick.getSelectedIndex() != -1){
                        childMenu.selectTab(childMenuStick.getSelectedIndex());
                    }
                }
            }
        });

        //动态添加fragment
        FragmentManager manager = getSupportFragmentManager();

        addFragment(manager, flJbxx.getId(), new ItemBaseInfoFragment());
        addFragment(manager, flBslct.getId(), new WorkFlowChartFragment());
        addFragment(manager, flSqcl.getId(), new ApplicationMaterialFragment());
    }

    /**
     * 初始化tab和隐藏的stickTab
     *
     * @param menu
     */
    private void initTabViewAndStickView(final HorizonScrollSelfTabSegment menu) {
        //动态加载子menu
        for (String menuTitle : childMenuTitles) {
            HorizonScrollSelfTabSegment.Tab tab = new HorizonScrollSelfTabSegment.Tab(menuTitle);
            menu.addTab(tab).setTabTextSize(DisplayUtil.dip2px(mContext, 16));
        }
        menu.setIndicatorDrawable(new ColorDrawable(Color.parseColor("#4a6dd5")));
        menu.setDefaultNormalColor(Color.parseColor("#828899"));
        menu.setDefaultSelectedColor(Color.parseColor("#303545"));
        menu.notifyDataChanged();//刷新

        menu.addOnTabSelectedListener(new CustomHorizonTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int index) {
                switch (index) {
                    case 0:
                        nsvItemDetail.scrollTo(0, (int) flJbxx.getY() - childMenuStick.getHeight());
                        break;
                    case 1:
                        nsvItemDetail.scrollTo(0, (int) flBslct.getY() - childMenuStick.getHeight());
                        break;
                    case 2:
                        nsvItemDetail.scrollTo(0, (int) flSqcl.getY() - childMenuStick.getHeight());
                        break;
                    case 3:
                        nsvItemDetail.scrollTo(0, (int) flSfbz.getY() - childMenuStick.getHeight());
                        break;
                    case 4:
                        nsvItemDetail.scrollTo(0, (int) flJgyb.getY() - childMenuStick.getHeight());
                        break;
                    case 5:
                        nsvItemDetail.scrollTo(0, (int) flSdyj.getY() - childMenuStick.getHeight());
                        break;
                }
            }

            @Override
            public void onTabUnselected(int index) {

            }

            @Override
            public void onTabReselected(int index) {

            }

            @Override
            public void onDoubleTap(int index) {

            }
        });
    }

    private void addFragment(FragmentManager manager, int layoutId, Fragment fragment) {
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(layoutId, fragment);
        transaction.show(fragment);
        transaction.commit();
    }

    @Override
    protected void initData() {
        presenter.getData(new HttpRequest());
    }

    @Override
    protected BasePresenter createPresenter() {
        return presenter = new ItemDetailPresenter();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onOther(Object data) {

    }

    @Override
    public void onSuccess(Object data) {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public LifecycleTransformer bindViewToLifecycle() {
        return this.bindToLifecycle();
    }

}
