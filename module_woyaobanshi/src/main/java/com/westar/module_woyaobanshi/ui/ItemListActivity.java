package com.westar.module_woyaobanshi.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.Group;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jakewharton.rxbinding2.view.RxView;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.uuzuche.lib_zxing.DisplayUtil;
import com.westar.Config;
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
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.functions.Consumer;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;

/**
 * Created by ZWP on 2019/4/8 11:02.
 * 描述：我要办事的事项列表
 */
@Route(path = ArouterPath.MODULE_WOYAOBANSHI_ITEM_LIST_ACTIVITY)
public class ItemListActivity extends ToolbarActivity implements ItemListContract.View {

    CustomHorizonTabLayout qmuiTab;
    AppCompatEditText etSearch;
    AppCompatImageView ivSearch;
    CustomHorizonTabLayout qmuiFilterTab;
    RecyclerView recycList;
    SmartRefreshLayout smartrefreshLayout;
    TextView tvFilterTabSelected;
    TextView reset;
    LinearLayout groupFilterSeleted;

    ItemListAdapter adapter;

    ItemListPresenter presenter;

    int pageNum;

    View rightView;


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
        tvFilterTabSelected = findViewById(R.id.tv_filter_tab_selected);
        reset = findViewById(R.id.tv_reset);
        groupFilterSeleted = findViewById(R.id.group_filter_seleted);

    }

    @Override
    protected void initView() {

        rightView = LayoutInflater.from(mContext).inflate(R.layout.top_left_view, null);
        RelativeLayout.LayoutParams leftParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        leftParams.addRule(RelativeLayout.CENTER_VERTICAL);
        rightView.setLayoutParams(leftParams);

        topBarLayout.addRightView(rightView, R.id.top_right_menu);

        //动态加载子menu
        CustomHorizonTabLayout.Tab typeTab1 = new CustomHorizonTabLayout.Tab("个人办事");
        CustomHorizonTabLayout.Tab typeTab2 = new CustomHorizonTabLayout.Tab("法人办事");
        qmuiTab.setDefaultSelectedColor(Color.parseColor("#333333"));
        qmuiTab.setDefaultNormalColor(Color.parseColor("#999999"));
        qmuiTab.addTab(typeTab1)
                .addTab(typeTab2)
                .setDefaultNormalColor(Color.parseColor("#999999"))
                .setDefaultSelectedColor(Color.parseColor("#333333"))
                .setTabTextSize(DisplayUtil.dip2px(mContext, 14))
                .setHasIndicator(true)
                .setIndicatorDrawable(new ColorDrawable(Color.parseColor("#4a6dd5")))
                .setIndicatorWidthAdjustContent(true);
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
                .setDefaultNormalColor(Color.parseColor("#aaaaaa"))
                .setDefaultSelectedColor(Color.parseColor("#828899"))
                .setTextPadding(new int[]{DisplayUtil.dip2px(mContext, 10), DisplayUtil.dip2px(mContext, 10), DisplayUtil.dip2px(mContext, 10), DisplayUtil.dip2px(mContext, 10)})
                .setTextMargin(new int[]{DisplayUtil.dip2px(mContext, 5), DisplayUtil.dip2px(mContext, 5), DisplayUtil.dip2px(mContext, 5), DisplayUtil.dip2px(mContext, 5)})
                .setChildViewBackgroundRes(R.drawable.selector_filter)
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

        initListener();
    }

    private void initListener() {
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (TextUtils.isEmpty(etSearch.getText().toString())) return false;
                    ToastUtils.showShort(etSearch.getText().toString());
                    presenter.getItemList(new HttpRequest());
                    return true;
                }
                return false;
            }
        });

        addSubscribe(RxView.clicks(rightView).throttleFirst(Config.WINDOWDURATION, TimeUnit.SECONDS).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                ARouter.getInstance().build(ArouterPath.APP_CHOICE_ADDRESS_ACTIVITY).navigation(mContext, new NavCallback() {
                    @Override
                    public void onArrival(Postcard postcard) {
                        ToastUtils.showShort("选择地址返回");
                    }
                });
            }
        }));

        addSubscribe(RxView.clicks(reset).throttleFirst(Config.WINDOWDURATION, TimeUnit.SECONDS).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                ARouter.getInstance().build(ArouterPath.APP_CHOICE_ADDRESS_ACTIVITY).navigation(mContext, new NavCallback() {
                    @Override
                    public void onArrival(Postcard postcard) {
                        groupFilterSeleted.setVisibility(View.GONE);
                        qmuiFilterTab.setVisibility(View.VISIBLE);
                    }
                });
            }
        }));

        addSubscribe(RxView.clicks(ivSearch).throttleFirst(Config.WINDOWDURATION, TimeUnit.SECONDS).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                if (TextUtils.isEmpty(etSearch.getText().toString())) return;
                ToastUtils.showShort(etSearch.getText().toString());
                presenter.getItemList(new HttpRequest());
            }
        }));

        qmuiTab.setOnTabClickListener(new CustomHorizonTabLayout.OnTabClickListener() {
            @Override
            public void onTabClick(int index) {
                switch (index) {
                    case 0:
                        ToastUtils.showShort("个人办事");
                        presenter.getItemList(new HttpRequest());
                        break;
                    case 1:
                        ToastUtils.showShort("法人办事");
                        presenter.getItemList(new HttpRequest());
                        break;
                }
            }
        });

        qmuiFilterTab.setOnTabClickListener(new CustomHorizonTabLayout.OnTabClickListener() {
            @Override
            public void onTabClick(int index) {
                switch (index) {
                    case 0:
                        ToastUtils.showShort("弹出部门选择弹窗");
                        QMUIDialog.MenuDialogBuilder builder = new QMUIDialog.MenuDialogBuilder(mContext);
                        for (int i = 0; i < 10; i++) {
                            final String item = "部门" + i;
                            builder.addItem(item, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                    // TODO: 2019/5/16 设置筛选栏的筛选条件
                                    tvFilterTabSelected.setText(item);
                                    groupFilterSeleted.setVisibility(View.VISIBLE);
                                    qmuiFilterTab.setVisibility(View.GONE);
                                }
                            });
                        }
                        builder.show();
                        break;
                    case 1:
                        ToastUtils.showShort("弹出主题选择弹窗");
                        break;
                    case 2:
                        ToastUtils.showShort("弹出生命周期选择弹窗");
                        break;
                    case 3:
                        ToastUtils.showShort("弹出特殊对象选择弹窗");
                        break;
                }
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
            hsvType.clearChildAllView()
                    .setTextColorRes(Color.parseColor("#b6c2d2"))
                    .setTextSize(10)
                    .setTextPadding(new int[]{DisplayUtil.dip2px(mContext, 10), DisplayUtil.dip2px(mContext, 6), DisplayUtil.dip2px(mContext, 10), DisplayUtil.dip2px(mContext, 6)})
                    .setTextMargin(new int[]{0, 0, DisplayUtil.dip2px(mContext, 10), 0})
                    .setBgRes(R.drawable.bg_item_type)
                    .addChildView(item.getTypeList());

            HorizontalScrollSelfView hsvFunction = helper.getView(R.id.hsv_function);
            hsvFunction.clearChildAllView()
                    .setTextColorRes(Color.parseColor("#ffffff"))
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
