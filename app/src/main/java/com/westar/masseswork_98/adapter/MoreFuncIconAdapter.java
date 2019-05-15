package com.westar.masseswork_98.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.chad.library.adapter.base.BaseViewHolder;
import com.westar.library_base.base.SingleBaseAdapter;
import com.westar.masseswork_98.R;
import com.westar.masseswork_98.been.MoreFuncIconItem;
import com.westar.masseswork_98.been.PlaceItem;

import java.util.List;

public class MoreFuncIconAdapter extends SingleBaseAdapter<MoreFuncIconItem>{

    public MoreFuncIconAdapter(Context context , List<MoreFuncIconItem> data) {
        super(context, data);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_more_func_icon;
    }

    @Override
    protected void convert(BaseViewHolder helper, MoreFuncIconItem data) {
        super.convert(helper, data);
        helper.setText(R.id.tv_more_func_title, data.getName());
        helper.setImageDrawable(R.id.im_more_func_icon, data.getDrawable());

    }
}
