package com.westar.masseswork_98.fragment;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseViewHolder;
import com.coorchice.library.SuperTextView;
import com.jakewharton.rxbinding2.view.RxView;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;
import com.qmuiteam.qmui.widget.QMUITabSegment;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.uuzuche.lib_zxing.DisplayUtil;
import com.westar.Config;
import com.westar.been.User;
import com.westar.library_base.base.BaseApplication;
import com.westar.library_base.base.BaseFragment;
import com.westar.library_base.base.BasePresenter;
import com.westar.library_base.base.SingleBaseAdapter;
import com.westar.library_base.common.ArouterPath;
import com.westar.library_base.eventbus.EventBusUtlis;
import com.westar.library_base.eventbus.UpdataUserInfoEvent;
import com.westar.library_base.glide.GlideApp;
import com.westar.library_base.http.been.HttpRequest;
import com.westar.library_base.view.TopBarLayout;
import com.westar.masseswork_98.R;
import com.westar.masseswork_98.been.NewsInformationTabs;
import com.westar.library_base.eventbus.OpenSolideFragmentEvent;
import com.westar.masseswork_98.mvp.contract.NewsInformationContract;
import com.westar.masseswork_98.mvp.presenter.NewsInformationPresenter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.functions.Consumer;

/**
 * Created by ZWP on 2019/4/8 20:24.
 * 描述：新闻资讯
 */
public class NewsInformationFragment extends BaseFragment implements NewsInformationContract.View {

    @BindView(R.id.toolbar_layout)
    TopBarLayout toolbarLayout;
    @BindView(R.id.tab_zx)
    QMUITabSegment tabZx;
    @BindView(R.id.tab_zx_hide)
    QMUITabSegment tabZxHide;
    @BindView(R.id.recyc_list)
    RecyclerView recycList;
    @BindView(R.id.nsv_news_information)
    NestedScrollView nsvNewsInformation;

    NewsInformationPresenter presenter;
    NewsInformationAdapter adapter;

    List<NewsInformationTabs> tabs;

    QMUIRadiusImageView leftView;

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public Context getBaseContext() {
        return mContext;
    }

    @Override
    public void onOther(Object data) {

    }

    @Override
    public void onSuccess(Object data) {
        if (null != data) {
            tabs.clear();
            tabs.addAll((List<NewsInformationTabs>) data);
            //初始化资讯信息
            initInformationMenu(tabs);
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
        return presenter = new NewsInformationPresenter();
    }

    @Override
    protected void lazyLoadShow(boolean isLoad) {

    }

    @Override
    protected void lazyLoadHide(boolean isLoad) {

    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_news_information;
    }

    @Override
    protected void initView() {
        //不显示默认返回键
        toolbarLayout.showBackView(false)
                .setTitle("咨询");
        //滚动到顶部
        nsvNewsInformation.fullScroll(ScrollView.FOCUS_UP);

        RelativeLayout.LayoutParams leftParams = new RelativeLayout.LayoutParams(DisplayUtil.dip2px(mContext, 30), DisplayUtil.dip2px(mContext, 30));
        leftParams.addRule(RelativeLayout.CENTER_VERTICAL);
        leftView = new QMUIRadiusImageView(mContext);
        leftView.setCircle(true);
        leftView.setLayoutParams(leftParams);

        initUserInfo();

        View rightView = LayoutInflater.from(mContext).inflate(R.layout.top_right_view, null);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_VERTICAL);
        rightView.setLayoutParams(params);

        toolbarLayout.addLeftView(leftView, R.id.top_left_address);
        toolbarLayout.addRightView(rightView, R.id.top_right_menu);

        recycList.setLayoutManager(new LinearLayoutManager(mContext));
        adapter = new NewsInformationAdapter(mContext, null);
        recycList.setAdapter(adapter);

        tabZx.addOnTabSelectedListener(new QMUITabSegment.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int index) {
                if (tabZxHide.getSelectedIndex() != index) {
                    tabZxHide.selectTab(index);
                }
                NewsInformationTabs newsInformationTabs = tabs.get(index);
                List<String> stringList = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    stringList.add(newsInformationTabs.getTabTitle() + i);
                }
                adapter.setNewData(stringList);
            }

            @Override
            public void onTabUnselected(int index) {
            }

            @Override
            public void onTabReselected(int index) {
            }

            @Override
            public void onDoubleTap(int index) {
            }
        });
        tabZxHide.addOnTabSelectedListener(new QMUITabSegment.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int index) {
                tabZx.selectTab(index);
            }

            @Override
            public void onTabUnselected(int index) {
            }

            @Override
            public void onTabReselected(int index) {
            }

            @Override
            public void onDoubleTap(int index) {
            }
        });

        nsvNewsInformation.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView nestedScrollView, int x, int y, int oldx, int oldy) {
                //判断滑动到指定位置时的tab的显示与隐藏
                if (y > tabZx.getY() + 1) {
                    tabZxHide.setVisibility(View.VISIBLE);
                } else {
                    tabZxHide.setVisibility(View.GONE);
                }
            }
        });


        addSubscribe(RxView.clicks(leftView)
                .throttleFirst(Config.WINDOWDURATION, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        ToastUtils.showShort("展开我的页面");
                        EventBusUtlis.sendEvent(new OpenSolideFragmentEvent());
                    }
                }));

        addSubscribe(RxView.clicks(rightView.findViewById(R.id.iv_more))
                .throttleFirst(Config.WINDOWDURATION, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        ARouter.getInstance().build(ArouterPath.MOREFUNCTION_ACTIVITY).navigation();
                    }
                }));

        addSubscribe(RxView.clicks(rightView.findViewById(R.id.iv_search))
                .throttleFirst(Config.WINDOWDURATION, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        ToastUtils.showShort("跳转搜索");
                        ARouter.getInstance().build(ArouterPath.SEARCH_ACTIVITY).navigation();
                    }
                }));


//        smartrefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
//            @Override
//            public void onLoadMore(RefreshLayout refreshLayout) {
//                ToastUtils.showShort("加载更多");
//            }
//
//            @Override
//            public void onRefresh(RefreshLayout refreshLayout) {
//                ToastUtils.showShort("刷新");
//            }
//        });

    }


    @Override
    protected void initData() {
        tabs = new ArrayList<>();
        presenter.getTabData(new HttpRequest());
    }

    /**
     * 初始化资讯信息tabs
     *
     * @param tabs
     */
    private void initInformationMenu(final List<NewsInformationTabs> tabs) {

        for (NewsInformationTabs newsInformationTabs : tabs) {
            QMUITabSegment.Tab tab1 = new QMUITabSegment.Tab(newsInformationTabs.getTabTitle());
            tab1.setTextSize(DisplayUtil.dip2px(mContext, 16));
            tab1.setTextColor(Color.parseColor("#828899"), Color.parseColor("#303545"));
            tabZx.addTab(tab1);
            tabZxHide.addTab(tab1);
        }

        tabZx.notifyDataChanged();//刷新
        tabZx.selectTab(0);
        tabZxHide.notifyDataChanged();//刷新
        tabZxHide.selectTab(0);
    }

    private void initUserInfo() {
        User user = BaseApplication.getIns().getUser();
        if (user != null) {
            GlideApp.with(mContext).load(user.getPhotoUrl()).into(leftView);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onUpdataUserInfoStickyEventBusCome(UpdataUserInfoEvent event) {
        if (event != null) {
            initUserInfo();
        }
    }


    /**
     * 热点适配器
     */
    class NewsInformationAdapter extends SingleBaseAdapter<String> {

        /**
         * 带默认空布局显示的
         *
         * @param context
         * @param data
         */
        public NewsInformationAdapter(Context context, @Nullable List<String> data) {
            super(context, data);
        }

        @Override
        public int getLayoutResId() {
            return R.layout.item_news_infomation;
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            super.convert(helper, item);
            SuperTextView superTextView = helper.getView(R.id.iv_news_photo);
            superTextView.setUrlImage("http://img3.imgtn.bdimg.com/it/u=2194421417,3617952188&fm=26&gp=0.jpg");
            helper.setText(R.id.news_title, item == null ? "" : item);
            helper.setText(R.id.dep, "交通局");
            helper.setText(R.id.time, "5小时前");

        }
    }

}
