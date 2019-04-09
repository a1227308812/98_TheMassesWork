package com.westar.masseswork_98.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZWP on 2019/4/8 20:08.
 * 描述：主页fragment适配器
 */
public class MenuFragmentAdapter extends FragmentStatePagerAdapter {
    List<Fragment> fragmentList;
    private String[] titles;

    public MenuFragmentAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        if (null == fragmentList) {
            this.fragmentList = new ArrayList<>();
        } else {
            this.fragmentList = fragmentList;
        }
    }

    /**
     * 配合TabLayout
     *
     * @param fm
     * @param list
     * @param titles titles[]
     */
    public MenuFragmentAdapter(FragmentManager fm, List<Fragment> list, String[] titles) {
        super(fm);
        this.fragmentList = list;
        this.titles = titles;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (titles != null && position < titles.length) {
            return titles[position];
        }
        return super.getPageTitle(position);
    }


    @Override
    public Fragment getItem(int position) {
        return fragmentList == null ? null : fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList == null ? 0 : fragmentList.size();
    }
}
