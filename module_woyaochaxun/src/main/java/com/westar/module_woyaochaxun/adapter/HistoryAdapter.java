package com.westar.module_woyaochaxun.adapter;

import android.content.Context;
import android.support.annotation.Nullable;

import com.westar.library_base.base.SingleBaseAdapter;
import com.westar.module_woyaochaxun.R;

import java.util.List;

/**
 * 我的办件历史
 * Created by zb on 2019/4/12.
 */

public class HistoryAdapter extends SingleBaseAdapter {
    /**
     * 带默认空布局显示的
     *
     * @param context
     * @param data
     */
    public HistoryAdapter(Context context, @Nullable List data) {
        super(context, data);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_history_banjian;
    }
}
