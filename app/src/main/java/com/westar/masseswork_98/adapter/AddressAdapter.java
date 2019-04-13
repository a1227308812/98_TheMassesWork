package com.westar.masseswork_98.adapter;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.westar.library_base.base.SingleBaseAdapter;
import com.westar.masseswork_98.R;
import com.westar.masseswork_98.been.AddressNode;

import java.util.List;

/**
 * Created by luoyz on 2019/4/12 13:22
 * Version : 1.0
 * Last update by luoyz on 2019/4/12 13:22
 * Describe :
 */

public class AddressAdapter extends SingleBaseAdapter<AddressNode> {
    /**
     * 带默认空布局显示的
     *
     * @param context
     * @param data
     */
    public AddressAdapter(Context context, @Nullable List<AddressNode> data) {
        super(context, data);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.rv_address_layout;
    }

    @Override
    protected void convert(BaseViewHolder helper, AddressNode item) {
        super.convert(helper, item);
        TextView label = helper.getView(R.id.tvLabelName);
        label.setText(item.getNodeName());
        if (item.isCheck()) {
            label.setTextColor(mContext.getResources().getColor(R.color.white));
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1) {
                label.setBackground(mContext.getResources().getDrawable(R.drawable.shape_address_focus));
            } else {
                label.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.shape_address_focus));
            }
        } else {
            label.setTextColor(Color.parseColor("#828899"));
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1) {
                label.setBackground(mContext.getResources().getDrawable(R.drawable.shape_address_normal));
            } else {
                label.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.shape_address_normal));
            }
        }
    }
}
