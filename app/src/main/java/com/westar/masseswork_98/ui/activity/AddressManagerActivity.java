package com.westar.masseswork_98.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseViewHolder;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.uuzuche.lib_zxing.DisplayUtil;
import com.westar.library_base.base.BasePresenter;
import com.westar.library_base.base.SingleBaseAdapter;
import com.westar.library_base.base.ToolbarActivity;
import com.westar.library_base.common.ArouterPath;
import com.westar.library_base.http.been.HttpRequest;
import com.westar.masseswork_98.R;
import com.westar.masseswork_98.been.AddressInfo;
import com.westar.masseswork_98.mvp.contract.AddressManagerContract;
import com.westar.masseswork_98.mvp.presenter.AddressManagerPresenter;

import java.util.List;

import butterknife.BindView;

/**
 * Created by ZWP on 2019/5/9 15:54.
 * 描述：地址管理界面
 */
@Route(path = ArouterPath.ADDRESS_MANAGER_ACTIVITY)
public class AddressManagerActivity extends ToolbarActivity implements AddressManagerContract.View {


    @BindView(R.id.recyc_list)
    RecyclerView recycList;
    @BindView(R.id.smartrefresh_layout)
    SmartRefreshLayout smartRefreshLayout;


    AddressManagerPresenter presenter;

    AddressManagerAdapter adapter;

    public static final int REQ_ADD_ADDRESS = 0x00001000;

    @Override
    protected BasePresenter createPresenter() {
        return presenter = new AddressManagerPresenter();
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_address_manager;
    }

    @Override
    protected void findId() {

    }

    @Override
    protected void initView() {

        AppCompatImageView imageView = new AppCompatImageView(mContext);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(DisplayUtil.dip2px(mContext, 24), DisplayUtil.dip2px(mContext, 24));
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT | RelativeLayout.CENTER_VERTICAL);
        layoutParams.setMarginEnd(DisplayUtil.dip2px(mContext, 10));
        imageView.setLayoutParams(layoutParams);
        imageView.setBackgroundResource(R.drawable.icon_other_tj);
        topBarLayout.addRightView(imageView, R.id.iv_add_address);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                skipActivityForResult(AddAddressActivity.class, REQ_ADD_ADDRESS, null);
            }
        });


        adapter = new AddressManagerAdapter(mContext, null);
        recycList.setLayoutManager(new LinearLayoutManager(mContext));
        recycList.setAdapter(adapter);

        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                initData();
            }
        });

    }

    @Override
    protected void initData() {
        presenter.getAddressList(new HttpRequest());
    }

    @Override
    public String setBarTitle() {
        return "地址管理";
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onOther(Object data) {

    }

    @Override
    public void onSuccess(Object data) {
        if (null != data) {
            List<AddressInfo> addressInfoList = (List<AddressInfo>) data;
            adapter.setNewData(addressInfoList);
        }
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public LifecycleTransformer bindViewToLifecycle() {
        return this.bindToLifecycle();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_ADD_ADDRESS) {
            initData();
        }
    }

    /**
     * 地址管理适配器
     */
    class AddressManagerAdapter extends SingleBaseAdapter<AddressInfo> {

        /**
         * 带默认空布局显示的
         *
         * @param context
         * @param data
         */
        public AddressManagerAdapter(Context context, @Nullable List<AddressInfo> data) {
            super(context, data);
        }

        @Override
        public int getLayoutResId() {
            return R.layout.item_address_manager;
        }

        @Override
        protected void convert(BaseViewHolder helper, AddressInfo item) {
            super.convert(helper, item);
            helper.setText(R.id.tv_name, item.getName());
            helper.setText(R.id.tv_address_detail, item.getAddress());
            helper.setText(R.id.tv_phone, item.getTel());
            helper.addOnClickListener(R.id.iv_edit);
            if (item.isSelected()) {
                helper.setVisible(R.id.iv_state, true);
                helper.setImageResource(R.id.iv_state, R.drawable.image_load_error);
            } else {
                helper.setVisible(R.id.iv_state, false);
            }
        }
    }
}
