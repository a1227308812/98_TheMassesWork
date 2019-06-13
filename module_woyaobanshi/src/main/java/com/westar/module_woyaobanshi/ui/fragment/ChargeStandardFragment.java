package com.westar.module_woyaobanshi.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.westar.library_base.base.BaseFragment;
import com.westar.library_base.base.BasePresenter;
import com.westar.library_base.base.SingleBaseAdapter;
import com.westar.module_woyaobanshi.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by ZWP on 2019/6/5 9:39.
 * 描述：收费标准
 */
public class ChargeStandardFragment extends BaseFragment {
    RecyclerView recycChargeStandard;
    ChargeStandardAdapter adapter;

    @Override
    protected void lazyLoadShow(boolean isLoad) {

    }

    @Override
    protected void lazyLoadHide(boolean isLoad) {

    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_charge_standard;
    }

    @Override
    protected void initView() {
        recycChargeStandard = rootView.findViewById(R.id.recyc_charge_standard);
        recycChargeStandard.setLayoutManager(new LinearLayoutManager(mContext));
        List<ChargeStandardBeen> beenList = new ArrayList<>();
        beenList.add(new ChargeStandardBeen()
                .setTitle("收费标准")
                .setContent("（1）汽车反光号牌每副100元；\n" +
                        "（2）摩托车反光号牌每副35元；\n" +
                        "（3）挂车反光号牌每面50元；\n" +
                        "（4）三轮汽车、低速货车、拖拉机反光号牌每副40元；\n" +
                        "（5）行驶证工本费10元；\n" +
                        "（6）机动车登记证书工本费10元。"));
        beenList.add(new ChargeStandardBeen()
                .setTitle("收费依据")
                .setContent("《国家发展改革委、财政部关于加强和规范机动车牌证工本费等收费标准管理有关问题的通知》（发改价格〔2004〕2831号文件第一条）。"));
        adapter = new ChargeStandardAdapter(mContext, beenList);
        recycChargeStandard.setAdapter(adapter);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public Context getBaseContext() {
        return getContext();
    }

    @Override
    public void onOther(Object data) {

    }

    @Override
    public void onSuccess(Object data) {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public LifecycleTransformer bindViewToLifecycle() {
        return this.bindToLifecycle();
    }

    class ChargeStandardBeen {
        String title;
        String content;

        public String getTitle() {
            return title;
        }

        public ChargeStandardBeen setTitle(String title) {
            this.title = title;
            return this;
        }

        public String getContent() {
            return content;
        }

        public ChargeStandardBeen setContent(String content) {
            this.content = content;
            return this;
        }
    }

    class ChargeStandardAdapter extends SingleBaseAdapter<ChargeStandardBeen> {

        /**
         * 带默认空布局显示的
         *
         * @param context
         * @param data
         */
        public ChargeStandardAdapter(Context context, @Nullable List<ChargeStandardBeen> data) {
            super(context, data);
            isUseEmpty(false);
        }

        @Override
        public int getLayoutResId() {
            return R.layout.item_charge_standard;
        }

        @Override
        protected void convert(BaseViewHolder helper, ChargeStandardBeen item) {
            super.convert(helper, item);
            helper.setText(R.id.tv_item_charge_title, null == item.getTitle() ? "" : item.getTitle());
            helper.setText(R.id.tv_item_charge_content, null == item.getContent() ? "" : item.getContent());
        }
    }
}
