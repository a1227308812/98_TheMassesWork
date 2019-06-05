package com.westar.masseswork_98.ui.activity;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
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
import com.westar.library_base.glide.GlideApp;
import com.westar.library_base.http.been.HttpRequest;
import com.westar.masseswork_98.R;
import com.westar.masseswork_98.been.MeCardInfo;
import com.westar.masseswork_98.mvp.contract.CardListContract;
import com.westar.masseswork_98.mvp.presenter.CardListPresenter;

import java.util.List;

import butterknife.BindView;

/**
 * Created by ZWP on 2019/5/9 10:17.
 * 描述：证照列表界面
 */
@Route(path = ArouterPath.CARD_LIST_ACTIVITY)
public class CardListActivity extends ToolbarActivity implements CardListContract.View {


    @BindView(R.id.recyc_list)
    RecyclerView recycList;
    @BindView(R.id.smartrefresh_layout)
    SmartRefreshLayout smartrefreshLayout;

    CardListPresenter presenter;

    int pageNum;
    CardListAdapter adapter;

    @Override
    protected BasePresenter createPresenter() {
        return presenter = new CardListPresenter();
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_card_list;
    }

    @Override
    protected void findId() {

    }

    @Override
    protected void initView() {

        adapter = new CardListAdapter(mContext, null);

        recycList.setLayoutManager(new LinearLayoutManager(mContext));
        recycList.setAdapter(adapter);

        smartrefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                initData();
            }
        });

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (adapter.getData().get(position) == null) return;
                MeCardInfo meCardInfo = (MeCardInfo) adapter.getData().get(position);
                Bundle bundle = new Bundle();
                bundle.putSerializable("MeCardInfo", meCardInfo);
                skipActivity(CardDetailActivity.class, bundle);
            }
        });
    }

    @Override
    protected void initData() {
        presenter.cardList(new HttpRequest());
    }

    @Override
    public String setBarTitle() {
        return "我的证照";
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
        if (data != null) {
            List<MeCardInfo> cardInfoList = (List<MeCardInfo>) data;
            adapter.setNewData(cardInfoList);
        }
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public LifecycleTransformer bindViewToLifecycle() {
        return this.bindToLifecycle();
    }

    /**
     * 适配器
     */
    class CardListAdapter extends SingleBaseAdapter<MeCardInfo> {

        /**
         * 带默认空布局显示的
         *
         * @param context
         * @param data
         */
        public CardListAdapter(Context context, @Nullable List<MeCardInfo> data) {
            super(context, data);
        }

        @Override
        public int getLayoutResId() {
            return R.layout.item_card_list;
        }

        @Override
        protected void convert(BaseViewHolder helper, MeCardInfo item) {
            super.convert(helper, item);
            helper.setText(R.id.tv_titlle, item.getTitle());
            helper.setText(R.id.tv_describle, item.getDescrible());
            helper.setText(R.id.tv_describle2, item.getDescrible2());
            helper.setText(R.id.tv_authentication_status, item.getAuthenticationStatus());
            AppCompatImageView imageView = helper.getView(R.id.iv_card_type);
            GlideApp.with(mContext).load(item.getTypeUrl()).into(imageView);


            ConstraintLayout layout = helper.getView(R.id.cl_card_menu_layout);
            // 创建渐变的shape drawable
            String[] colors = item.getColors();
            int colorsRes[] = new int[colors.length];
            for (int i = 0; i < colors.length; i++) {
                colorsRes[i] = Color.parseColor(colors[i]);
            }
            GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, colorsRes);
            gradientDrawable.setCornerRadius(DisplayUtil.dip2px(mContext, 10));

            layout.setBackground(gradientDrawable);
        }
    }

}
