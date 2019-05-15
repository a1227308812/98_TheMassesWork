package com.westar.module_woyaobanshi.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.Group;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qmuiteam.qmui.widget.QMUITabSegment;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.uuzuche.lib_zxing.DisplayUtil;
import com.westar.been.ItemInfo;
import com.westar.library_base.base.BasePresenter;
import com.westar.library_base.base.SingleBaseAdapter;
import com.westar.library_base.base.ToolbarActivity;
import com.westar.library_base.common.ArouterPath;
import com.westar.library_base.http.been.HttpRequest;
import com.westar.library_base.view.CustomHorizonTabLayout;
import com.westar.library_base.view.HorizontalScrollSelfView;
import com.westar.module_woyaobanshi.R;
import com.westar.module_woyaobanshi.mvp.contract.ItemListContract;
import com.westar.module_woyaobanshi.mvp.presenter.ItemListPresenter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.zhanghai.android.materialratingbar.MaterialRatingBar;

/**
 * Created by ZWP on 2019/4/8 11:02.
 * 描述：我要办事的事项列表
 */
@Route(path = ArouterPath.MODULE_WOYAOBANSHI_ITEM_LIST_ACTIVITY)
public class ItemListActivity extends ToolbarActivity implements ItemListContract.View {

    QMUITabSegment qmuiTab;
    AppCompatEditText etSearch;
    AppCompatImageView ivSearch;
    CustomHorizonTabLayout qmuiFilterTab;
    RecyclerView recycList;
    SmartRefreshLayout smartrefreshLayout;

    ItemListAdapter adapter;

    ItemListPresenter presenter;

    int pageNum;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_item_list;
    }

    @Override
    protected void findId() {
        qmuiTab = findViewById(R.id.qmui_tab);
        etSearch = findViewById(R.id.et_search);
        ivSearch = findViewById(R.id.iv_search);
        qmuiFilterTab = findViewById(R.id.qmui_filter_tab);
        recycList = findViewById(R.id.recyc_list);
        smartrefreshLayout = findViewById(R.id.smartrefresh_layout);
    }

    @Override
    protected void initView() {
        //动态加载子menu
        QMUITabSegment.Tab typeTab1 = new QMUITabSegment.Tab("个人办事");
        QMUITabSegment.Tab typeTab2 = new QMUITabSegment.Tab("法人办事");
        qmuiTab.setDefaultSelectedColor(Color.parseColor("#333333"));
        qmuiTab.setDefaultNormalColor(Color.parseColor("#999999"));
        qmuiTab.addTab(typeTab1)
                .addTab(typeTab2)
                .setTabTextSize(DisplayUtil.dip2px(mContext, 14));
        qmuiTab.notifyDataChanged();

        //动态加载子menu
        CustomHorizonTabLayout.Tab filterTab1 = new CustomHorizonTabLayout.Tab("按部门");
        CustomHorizonTabLayout.Tab filterTab2 = new CustomHorizonTabLayout.Tab("按主题");
        CustomHorizonTabLayout.Tab filterTab3 = new CustomHorizonTabLayout.Tab("按生命周期");
        CustomHorizonTabLayout.Tab filterTab4 = new CustomHorizonTabLayout.Tab("按特殊对象");
        qmuiFilterTab.addTab(filterTab1)
                .addTab(filterTab2)
                .addTab(filterTab3)
                .addTab(filterTab4)
                .setTextPadding(new int[]{DisplayUtil.dip2px(mContext, 10), DisplayUtil.dip2px(mContext, 10), DisplayUtil.dip2px(mContext, 10), DisplayUtil.dip2px(mContext, 10)})
                .setTextMargin(new int[]{DisplayUtil.dip2px(mContext, 5), DisplayUtil.dip2px(mContext, 5), DisplayUtil.dip2px(mContext, 5), DisplayUtil.dip2px(mContext, 5)})
                .setChildViewBackgroundRes(R.drawable.bg_filter)
                .setTabTextSize(DisplayUtil.dip2px(mContext, 12));
        qmuiFilterTab.notifyDataChanged();

        adapter = new ItemListAdapter(mContext, null);
        recycList.setLayoutManager(new LinearLayoutManager(mContext));
        recycList.setAdapter(adapter);


        smartrefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                presenter.getItemList(new HttpRequest());
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageNum = 0;
                presenter.getItemList(new HttpRequest());
            }
        });
    }

    @Override
    protected void initData() {
        presenter.getItemList(new HttpRequest());
    }

    @Override
    public String setBarTitle() {
        return "办事指南";
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
        smartrefreshLayout.finishRefresh();
        smartrefreshLayout.finishLoadMore();
        if (data != null) {
            pageNum++;
            List<ItemInfo> itemInfoList = (List<ItemInfo>) data;
            adapter.setNewData(itemInfoList);
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
    protected BasePresenter createPresenter() {
        return presenter = new ItemListPresenter();
    }


    class ItemListAdapter extends SingleBaseAdapter<ItemInfo> {
        //用于记录展开状态
        Map<Integer, Boolean> openState;

        /**
         * 带默认空布局显示的
         *
         * @param context
         * @param data
         */
        public ItemListAdapter(Context context, @Nullable List<ItemInfo> data) {
            super(context, data);
            openState = new HashMap<>();
        }

        @Override
        public int getLayoutResId() {
            return R.layout.item_item_list;
        }

        @Override
        protected void convert(final BaseViewHolder helper, final ItemInfo item) {
            super.convert(helper, item);

            helper.setText(R.id.tv_title, item.getItemTitle());
            helper.setText(R.id.tv_bljg, item.getBljg());
            helper.setText(R.id.tv_zxdh, item.getZxdh());

            MaterialRatingBar ratingBar = helper.getView(R.id.ratingbar);
            ratingBar.setRating(item.getStar());

            HorizontalScrollSelfView hsvType = helper.getView(R.id.hsv_type);
            hsvType.setTextColorRes(Color.parseColor("#b6c2d2"))
                    .setTextSize(10)
                    .setTextPadding(new int[]{DisplayUtil.dip2px(mContext, 10), DisplayUtil.dip2px(mContext, 6), DisplayUtil.dip2px(mContext, 10), DisplayUtil.dip2px(mContext, 6)})
                    .setTextMargin(new int[]{0, 0, DisplayUtil.dip2px(mContext, 10), 0})
                    .setBgRes(R.drawable.bg_item_type)
                    .addChildView(item.getTypeList());

            HorizontalScrollSelfView hsvFunction = helper.getView(R.id.hsv_function);
            hsvFunction.setTextColorRes(Color.parseColor("#ffffff"))
                    .setTextSize(10)
                    .setTextPadding(new int[]{DisplayUtil.dip2px(mContext, 14), DisplayUtil.dip2px(mContext, 7), DisplayUtil.dip2px(mContext, 14), DisplayUtil.dip2px(mContext, 7)})
                    .setTextMargin(new int[]{0, 0, DisplayUtil.dip2px(mContext, 10), 0})
                    .setBgRes(R.drawable.bg_item_function)
                    .addChildView(item.getFunctionList());
            hsvFunction.addChildClickLisenter(new HorizontalScrollSelfView.ChildClick() {
                @Override
                public void clickPosition(String position) {
                    ToastUtils.showShort(position);
                }
            });
            helper.setOnClickListener(R.id.civ_call, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!TextUtils.isEmpty(item.getZxdh())) {
                        Intent intent = new Intent();
//                                    intent.setAction(Intent.ACTION_CALL);//直接打电话
                        intent.setAction(Intent.ACTION_DIAL);//渠道拨号界面，电话由用户发起拨打
                        intent.setData(Uri.parse("tel:" + item.getZxdh()));
                        startActivity(intent);
                    }
                }
            });

            final AppCompatImageView openView = helper.getView(R.id.civ_more_open);
            final AppCompatImageView closeView = helper.getView(R.id.civ_more_close);
            final Group moreGroup = helper.getView(R.id.group_more);

            //默认都是非展开的
            if (null == openState.get(helper.getAdapterPosition())) {
                openState.put(helper.getAdapterPosition(), false);
            }
            //初始化展开状态
            if (openState.get(helper.getAdapterPosition())) {
                openView.setVisibility(View.GONE);
                moreGroup.setVisibility(View.VISIBLE);
            } else {
                openView.setVisibility(View.VISIBLE);
                moreGroup.setVisibility(View.GONE);
            }

            openView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (moreGroup.getVisibility() == View.GONE) {
                        openState.put(helper.getAdapterPosition(), true);
                        openView.setVisibility(View.GONE);
                        moreGroup.setVisibility(View.VISIBLE);
                    }
                }
            });

            closeView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (moreGroup.getVisibility() == View.VISIBLE) {
                        openState.put(helper.getAdapterPosition(), false);
                        openView.setVisibility(View.VISIBLE);
                        moreGroup.setVisibility(View.GONE);
                    }
                }
            });
        }
    }

}
