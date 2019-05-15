package com.westar.masseswork_98.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseViewHolder;
import com.westar.library_base.base.SingleBaseAdapter;
import com.westar.masseswork_98.R;
import com.westar.masseswork_98.been.PlaceItem;


import java.util.List;

public class PlaceItemAdapter extends SingleBaseAdapter<PlaceItem> {

    public PlaceItemAdapter(Context context, List<PlaceItem> data) {
        super(context,data);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_place_item;
    }

    @Override
    protected void convert(BaseViewHolder helper, PlaceItem data) {
        super.convert(helper, data);
        helper.setText(R.id.tv_center_list_name, data.getName());
        helper.setText(R.id.tv_center_list_address, data.getAddress());

    }


}
