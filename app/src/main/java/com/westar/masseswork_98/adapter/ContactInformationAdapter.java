package com.westar.masseswork_98.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseViewHolder;
import com.westar.library_base.base.SingleBaseAdapter;
import com.westar.masseswork_98.R;
import com.westar.masseswork_98.been.ContactInfo;

import java.util.List;


public class ContactInformationAdapter extends SingleBaseAdapter<ContactInfo>{

    private List<ContactInfo> data;

    public ContactInformationAdapter(Context context, List<ContactInfo> data) {
        super(context, data);
        this.data = data;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_contact_info_white;
    }

    @Override
    protected void convert(BaseViewHolder helper, ContactInfo contactInfo) {
        super.convert(helper, contactInfo);
        helper.setText(R.id.tv_deal_dep_name, contactInfo.getDepName());
        helper.setText(R.id.tv_phone, contactInfo.getPhone());
        if (data != null && data.size() != 0) {
            if (data.size() % 2 == 0)
                helper.setBackgroundColor(R.id.rl_contact_info_white, 0xf0f3f7);
            else
                helper.setBackgroundColor(R.id.rl_contact_info_white, 0xfff);
        }
    }
}
