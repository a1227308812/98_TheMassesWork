package com.westar.library_base.base;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by ZWP on 2019/3/30 14:19.
 * 描述：非可拖拽列表布局基类adapter
 */
public abstract class CustomBaseAdapter<T> extends BaseQuickAdapter<T,BaseViewHolder> {

    public CustomBaseAdapter(@LayoutRes int layoutResId, @Nullable List<T> data) {
        super(layoutResId, data);
    }

    public CustomBaseAdapter(@Nullable List<T> data) {
        super(data);
    }

    public CustomBaseAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, T item) {
    }
}
