package com.westar.masseswork_98.ui;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.qmuiteam.qmui.widget.QMUITabSegment;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.uuzuche.lib_zxing.DisplayUtil;
import com.westar.library_base.base.BaseActivity;
import com.westar.library_base.base.BasePresenter;
import com.westar.library_base.common.ArouterPath;
import com.westar.library_base.common.Common;
import com.westar.library_base.eventbus.BaseEvent;
import com.westar.masseswork_98.R;
import com.westar.masseswork_98.adapter.MenuFragmentAdapter;
import com.westar.library_base.eventbus.OpenSolideFragmentEvent;
import com.westar.library_base.eventbus.SolideTypeEvent;
import com.westar.masseswork_98.fragment.ConvenientServiceFragment;
import com.westar.masseswork_98.fragment.NewsInformationFragment;
import com.westar.masseswork_98.fragment.NormalSolideFragment;
import com.westar.masseswork_98.fragment.NotLoginSolideFragment;
import com.westar.masseswork_98.fragment.OfficeHallFragment;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by ZWP on 2019/4/8 18:30.
 * 描述：首页
 */
@Route(path = ArouterPath.HOMEGROUP_ACTIVITY)
public class HomeGroupActivity extends BaseActivity {

    @BindView(R.id.drawerlayout)
    DrawerLayout drawerlayout;
    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.qmui_tab)
    QMUITabSegment tabSegment;


    MenuFragmentAdapter pagerAdapter;

    List<Fragment> fragmentList;


    OfficeHallFragment officeHallFragment;
    NewsInformationFragment newsInformationFragment;
    ConvenientServiceFragment convenientServiceFragment;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_home_group;
    }

    @Override
    protected void findId() {

    }

    @Override
    protected void initView() {

        officeHallFragment = new OfficeHallFragment();
        newsInformationFragment = new NewsInformationFragment();
        convenientServiceFragment = new ConvenientServiceFragment();

        fragmentList = new ArrayList<>();
        fragmentList.add(officeHallFragment);
        fragmentList.add(newsInformationFragment);
        fragmentList.add(convenientServiceFragment);

        pagerAdapter = new MenuFragmentAdapter(getSupportFragmentManager(), fragmentList);
        vp.setAdapter(pagerAdapter);
        vp.setOffscreenPageLimit(fragmentList.size());
        vp.setCurrentItem(0);
        tabSegment.setupWithViewPager(vp, false);

        initTabs();

    }

    private void initTabs() {

        QMUITabSegment.Tab tab1 = new QMUITabSegment.Tab(getResources().getDrawable(R.drawable.icon_menu_bsdt_normal),
                getResources().getDrawable(R.drawable.icon_menu_bsdt_focuse), "办事大厅", false);
        tab1.setTextSize(DisplayUtil.dip2px(mContext, 10));
        tab1.setTextColor(Color.parseColor("#b6c2d2"), Color.parseColor("#4a6dd5"));
        QMUITabSegment.Tab tab2 = new QMUITabSegment.Tab(getResources().getDrawable(R.drawable.icon_menu_xwzx_normal),
                getResources().getDrawable(R.drawable.icon_menu_xwzx_focuse), "新闻资讯", false);
        tab2.setTextSize(DisplayUtil.dip2px(mContext, 10));
        tab2.setTextColor(Color.parseColor("#b6c2d2"), Color.parseColor("#4a6dd5"));
        QMUITabSegment.Tab tab3 = new QMUITabSegment.Tab(getResources().getDrawable(R.drawable.icon_menu_bmfw_normal),
                getResources().getDrawable(R.drawable.icon_menu_bmfw_focuse), "便民服务", false);
        tab3.setTextSize(DisplayUtil.dip2px(mContext, 10));
        tab3.setTextColor(Color.parseColor("#b6c2d2"), Color.parseColor("#4a6dd5"));

        tabSegment.addTab(tab1)
                .addTab(tab2)
                .addTab(tab3);
        tabSegment.notifyDataChanged();//刷新
    }

    @Override
    protected void initData() {

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

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void onEventBusCome(BaseEvent baseEvent) {
        super.onEventBusCome(baseEvent);
    }


    /**
     * evenbus粘性事件接收
     *
     * @param solideTypeEvent
     */
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void initSolideFragment(SolideTypeEvent solideTypeEvent) {
        // TODO: 2019/4/29 根据登录信息判断是否已经实名注册，根据是否实名注册显示不同的我的页面状态
        if (solideTypeEvent != null) {
            if (solideTypeEvent.getCode() == Common.NOT_LOGIN) {
                NotLoginSolideFragment notLoginSolideFragment = new NotLoginSolideFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.slide_fragment, notLoginSolideFragment).commit();
            } else if (solideTypeEvent.getCode() == Common.NOT_AUTHENTICATION) {
                NormalSolideFragment normalSolideFragment = new NormalSolideFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.slide_fragment, normalSolideFragment).commit();
            } else {
                NormalSolideFragment normalSolideFragment = new NormalSolideFragment();
                Bundle bundle = new Bundle();
                bundle.putBoolean(Common.AUTHENTICATION_TYPE, true);
                normalSolideFragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.slide_fragment, normalSolideFragment).commit();
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void openSolideFragment(OpenSolideFragmentEvent openSolideFragmentEvent) {
        //滑出侧边栏
        drawerlayout.openDrawer(GravityCompat.START);
    }

    @Override
    public void onBackPressed() {
        if (drawerlayout.isDrawerOpen(GravityCompat.START)) {
            drawerlayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
