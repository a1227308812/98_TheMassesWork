package com.westar.masseswork_98.ui;


import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.util.QMUIResHelper;
import com.qmuiteam.qmui.widget.QMUIPagerAdapter;
import com.qmuiteam.qmui.widget.QMUITabSegment;
import com.westar.library_base.base.BaseActivity;
import com.westar.library_base.common.ArouterPath;
import com.westar.masseswork_98.R;
import com.westar.masseswork_98.adapter.MenuFragmentAdapter;
import com.westar.masseswork_98.fragment.ConvenientServiceFragment;
import com.westar.masseswork_98.fragment.NewsInformationFragment;
import com.westar.masseswork_98.fragment.OfficeHallFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by ZWP on 2019/4/8 18:30.
 * 描述：首页
 */
@Route(path = ArouterPath.APP_HOMEGROUP_ACTIVITY)
public class HomeGroupActivity extends BaseActivity {

    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.qmui_tab)
    QMUITabSegment qmuiTab;


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
        qmuiTab.setupWithViewPager(vp);
        initTabs();
    }

    private void initTabs() {

//        int normalColor = QMUIResHelper.getAttrColor(mContext, R.attr.qmui_config_color_gray_6);
//        int selectColor = QMUIResHelper.getAttrColor(mContext, R.attr.qmui_config_color_blue);
        QMUITabSegment.Tab tab1 = new QMUITabSegment.Tab("第一个");
        QMUITabSegment.Tab tab2 = new QMUITabSegment.Tab("第二个");
        QMUITabSegment.Tab tab3 = new QMUITabSegment.Tab("第三个");
        qmuiTab.addTab(tab1)
                .addTab(tab2)
                .addTab(tab3);
//        tab.setTextColor(normalColor, selectColor)
//                .set(1.6f)
//                .setTextSize(QMUIDisplayHelper.sp2px(mContext, 13), QMUIDisplayHelper.sp2px(mContext, 15))
//                .setDynamicChangeIconColor(false);
//        QMUITab component = tab
//                .setNormalDrawable(ContextCompat.getDrawable(mContext, R.mipmap.icon_tabbar_component))
//                .setSelectedDrawable(ContextCompat.getDrawable(mContext, R.mipmap.icon_tabbar_component_selected))
//                .setText("Components")
//                .build();
//        QMUITab util = tab
//                .setNormalDrawable(ContextCompat.getDrawable(mContext, R.mipmap.icon_tabbar_util))
//                .setSelectedDrawable(ContextCompat.getDrawable(mContext, R.mipmap.icon_tabbar_util_selected))
//                .setText("Helper")
//                .build();
//        QMUITab lab = tab
//                .setNormalDrawable(ContextCompat.getDrawable(mContext, R.mipmap.icon_tabbar_lab))
//                .setSelectedDrawable(ContextCompat.getDrawable(mContext, R.mipmap.icon_tabbar_lab_selected))
//                .setText("Lab")
//                .build();
//
//        mTabSegment.addTab(component)
//                .addTab(util)
//                .addTab(lab);
    }

    @Override
    protected void initData() {

    }

}
