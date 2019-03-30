package com.westar.library_base.preview.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by luoyz on 2019/3/20 13:32
 * Version : 1.0
 * Last update by luoyz on 2019/3/20 13:32
 * Describe : 图片预览适配器
 */

public class ImagePreviewVpAdapter extends FragmentPagerAdapter {
    private ArrayList<String> mUrlList;
    private ArrayList<ImagePreviewFragment> mFragmentList;

    public ImagePreviewVpAdapter(FragmentManager fm, ArrayList<String> url) {
        super(fm);
        mUrlList = url;
        if (mUrlList != null) {
            mFragmentList = new ArrayList<>();
            for (String str :
                    mUrlList) {
                ImagePreviewFragment fragment = new ImagePreviewFragment();
                Bundle bundle = new Bundle();
                bundle.putString("url", str);
                fragment.setArguments(bundle);
                mFragmentList.add(fragment);
            }
        }

    }

    @Override
    public Fragment getItem(int i) {
        return mFragmentList.get(i);
    }

    @Override
    public int getCount() {
        return mUrlList == null ? 0 : mUrlList.size();
    }
}
