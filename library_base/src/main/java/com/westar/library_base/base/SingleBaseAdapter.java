package com.westar.library_base.base;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.util.MultiTypeDelegate;
import com.westar.masseswork_98.library_base.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZWP on 2019/3/30 14:18.
 * 描述：单一布局的BaseAdapterDao（可扩展为多布局）
 */
public abstract class SingleBaseAdapter<T> extends CustomBaseAdapter<T> {

    public Context context;
    public List<T> mData;

    /**
     * 带默认空布局显示的
     *
     * @param context
     * @param data
     */
    public SingleBaseAdapter(Context context, @Nullable List<T> data) {
        super(data);
        this.context = context;

        if (data != null) {
            mData = data;
        } else {
            mData = new ArrayList<>();
        }
        if (mData.size() == 0) {
            View emptyView = LayoutInflater.from(this.context).inflate(R.layout.view_empty, null, false);
            // 没有数据的时候默认显示该布局
            this.setEmptyView(emptyView);
        }
        //设置布局的类型type
        setMultiTypeDelegate(new MultiTypeDelegate<T>() {
            @Override
            protected int getItemType(Object mainMsgEntity) {
                return 0;
            }
        });
        //设置对应type的布局
        getMultiTypeDelegate().registerItemType(0, getLayoutResId());
    }

    /**
     * itemview数据绑定
     *
     * @param helper
     * @param item
     */
    @Override
    protected void convert(BaseViewHolder helper, T item) {
        super.convert(helper, item);
//        int position = helper.getAdapterPosition();

//        if (helper.getView(R.id.item_header_space) != null) {
//            if (position == 0) {
//                helper.getView(R.id.item_header_space).setVisibility(View.VISIBLE);
//            }else {
//                helper.getView(R.id.item_header_space).setVisibility(View.GONE);
//            }
//        }

//        if (helper.getView(R.id.item_footer_space) != null) {
//            if (position == getData().size() - 1) {
//                helper.getView(R.id.item_footer_space).setVisibility(View.VISIBLE);
//            }else {
//                helper.getView(R.id.item_footer_space).setVisibility(View.GONE);
//            }
//        }
    }

    /**
     * 设置item布局
     *
     * @return
     */
    public abstract int getLayoutResId();

}
