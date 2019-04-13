package com.westar.module_woyaochaxun.adapter;

import android.content.Context;
import android.support.annotation.Nullable;

import com.westar.library_base.base.SingleBaseAdapter;
import com.westar.module_woyaochaxun.R;

import java.util.List;

/**
 * 办件查询列表页
 * Created by zb on 2019/4/9.
 */

public class BanJianChaXunAdpter extends SingleBaseAdapter {
    /**
     * 带默认空布局显示的
     *
     * @param context
     * @param data
     */
    public BanJianChaXunAdpter(Context context, @Nullable List data) {
        super(context, data);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_banjian_recy;
    }
}
