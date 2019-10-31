package com.westar.module_woyaochaxun.adapter;

import android.content.Context;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseViewHolder;
import com.westar.library_base.base.SingleBaseAdapter;
import com.westar.module_woyaochaxun.R;
import com.westar.module_woyaochaxun.model.DealItem;

import java.util.List;

/**
 * 办件查询列表页
 * Created by zb on 2019/4/9.
 */

public class BanJianChaXunAdpter extends SingleBaseAdapter<DealItem> {
    /**
     * 带默认空布局显示的
     *
     * @param context
     * @param data
     */
    public BanJianChaXunAdpter(Context context, @Nullable List<DealItem> data) {
        super(context, data);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_banjian_recy;
    }

    @Override
    protected void convert(BaseViewHolder helper, DealItem dealItem) {
        super.convert(helper, dealItem);
        helper.setText(R.id.tv_title_name, dealItem.getItemName());
        helper.setText(R.id.tv_deal_no, dealItem.getDealNo());
        helper.setText(R.id.tv_application_date, dealItem.getApplicationDate());
        helper.setText(R.id.tv_verrify_state, dealItem.getVerifyState());
    }
}
