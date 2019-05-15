package com.westar.masseswork_98.fragment;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jakewharton.rxbinding2.view.RxView;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.westar.Config;
import com.westar.been.User;
import com.westar.library_base.banner.GlideImageLoader;
import com.westar.library_base.base.BaseApplication;
import com.westar.library_base.base.BaseFragment;
import com.westar.library_base.base.BasePresenter;
import com.westar.library_base.base.SingleBaseAdapter;
import com.westar.library_base.common.ArouterPath;
import com.westar.library_base.eventbus.UpdataUserInfoEvent;
import com.westar.library_base.glide.GlideApp;
import com.westar.library_base.http.been.HttpRequest;
import com.westar.library_base.view.TopBarLayout;
import com.westar.masseswork_98.R;
import com.westar.masseswork_98.been.AccountInfo;
import com.westar.masseswork_98.mvp.contract.OfficeHallContract;
import com.westar.masseswork_98.mvp.presenter.OfficeHallPresenter;
import com.westar.masseswork_98.ui.activity.MoreFunctionActivity;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.functions.Consumer;

/**
 * Created by ZWP on 2019/4/8 20:14.
 * 描述：办事大厅
 */
public class OfficeHallFragment extends BaseFragment implements OfficeHallContract.View {

    @BindView(R.id.toolbar_layout)
    TopBarLayout toolbarLayout;
    @BindView(R.id.qiv_user_photo)
    QMUIRadiusImageView qivUserPhoto;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.tv_message)
    TextView tvMessage;
    @BindView(R.id.btn_doubt)
    QMUIRoundButton btnDoubt;
    @BindView(R.id.home_banner)
    Banner homeBanner;
    @BindView(R.id.ll_wybs)
    LinearLayout llWybs;
    @BindView(R.id.ll_wycx)
    LinearLayout llWycx;
    @BindView(R.id.ll_wyyy)
    LinearLayout llWyyy;
    @BindView(R.id.ll_wyts)
    LinearLayout llWyts;
    @BindView(R.id.ll_wyzx)
    LinearLayout llWyzx;
    @BindView(R.id.recyc_rdsx)
    RecyclerView recycRdsx;

    View leftView;
    View rightView;

    OfficeHallPresenter presenter;

    OfficeHallAdapter adapter;

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
        return presenter = new OfficeHallPresenter(3);
    }

    @Override
    protected void lazyLoadShow(boolean isLoad) {

    }

    @Override
    protected void lazyLoadHide(boolean isLoad) {

    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_office_hall;
    }

    @Override
    protected void initView() {
        recycRdsx.setLayoutManager(new LinearLayoutManager(mContext));
        adapter = new OfficeHallAdapter(mContext, null);
        recycRdsx.setAdapter(adapter);

        //设置banner样式
        homeBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        homeBanner.setImageLoader(new GlideImageLoader());
        //设置banner动画效果
        homeBanner.setBannerAnimation(Transformer.DepthPage);
        //设置自动轮播，默认为true
        homeBanner.isAutoPlay(true);
        //设置轮播时间
        homeBanner.setDelayTime(4000);
        //设置指示器位置（当banner模式中有指示器时）
        homeBanner.setIndicatorGravity(BannerConfig.CENTER);


        //不显示默认返回键
        toolbarLayout.showBackView(false);

        leftView = LayoutInflater.from(mContext).inflate(R.layout.top_left_view, null);
        RelativeLayout.LayoutParams leftParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        leftParams.addRule(RelativeLayout.CENTER_VERTICAL);
        leftView.setLayoutParams(leftParams);

        rightView = LayoutInflater.from(mContext).inflate(R.layout.top_right_view, null);
        RelativeLayout.LayoutParams rightParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        rightParams.addRule(RelativeLayout.CENTER_VERTICAL);
        rightView.setLayoutParams(rightParams);

        toolbarLayout.addLeftView(leftView, R.id.top_left_address);
        toolbarLayout.addRightView(rightView, R.id.top_right_menu);

        initUserInfo();

        initListener();
    }

    private void initListener() {
        addSubscribe(RxView.clicks(leftView)
                .throttleFirst(Config.WINDOWDURATION, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        ARouter.getInstance().build(ArouterPath.APP_CHOICE_ADDRESS_ACTIVITY).navigation();
                    }
                }));
        addSubscribe(RxView.clicks(rightView.findViewById(R.id.iv_more))
                .throttleFirst(Config.WINDOWDURATION, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        ARouter.getInstance().build(ArouterPath.APP_MOREFUNCTION_ACTIVITY).navigation();
                    }
                }));
        addSubscribe(RxView.clicks(rightView.findViewById(R.id.iv_search))
                .throttleFirst(Config.WINDOWDURATION, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        ARouter.getInstance().build(ArouterPath.APP_SEARCH_ACTIVITY).navigation();
                    }
                }));


        addSubscribe(RxView.clicks(rootView.findViewById(R.id.ll_wybs))
                .throttleFirst(Config.WINDOWDURATION, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        ARouter.getInstance().build(ArouterPath.MODULE_WOYAOBANSHI_ITEM_LIST_ACTIVITY).navigation();
                    }
                }));
        addSubscribe(RxView.clicks(rootView.findViewById(R.id.ll_wycx))
                .throttleFirst(Config.WINDOWDURATION, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        ToastUtils.showShort("我要查询");
                    }
                }));
        addSubscribe(RxView.clicks(rootView.findViewById(R.id.ll_wyts))
                .throttleFirst(Config.WINDOWDURATION, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        ToastUtils.showShort("我要投诉");
                    }
                }));
        addSubscribe(RxView.clicks(rootView.findViewById(R.id.ll_wyyy))
                .throttleFirst(Config.WINDOWDURATION, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        ToastUtils.showShort("我要预约");
                    }
                }));
        addSubscribe(RxView.clicks(rootView.findViewById(R.id.ll_wyzx))
                .throttleFirst(Config.WINDOWDURATION, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        ToastUtils.showShort("我要咨询");
                    }
                }));

        /**
         * item的点击事件
         */
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String msg = (String) adapter.getData().get(position);
                ToastUtils.showShort(msg);
            }
        });
    }

    @Override
    protected void initData() {
//        presenter.getBaseData(new HttpRequest());
        presenter.getBannerData(new HttpRequest());
        presenter.getHotData(new HttpRequest());
    }

    @Override
    public void getBaseData(AccountInfo data) {

    }

    @Override
    public void getBannerData(List<String> data) {
        //设置图片集合
        homeBanner.setImages(data);
        //banner设置方法全部调用完毕时最后调用
        homeBanner.start();
    }

    @Override
    public void getHotData(List<String> data) {
        adapter.setNewData(data);
    }

    @Override
    public void onStart() {
        super.onStart();
        homeBanner.stopAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        homeBanner.stopAutoPlay();
    }


    private void initUserInfo() {
        User user = BaseApplication.getIns().getUser();
        tvMessage.setText(user.getNoticeDescribe());
        tvUserName.setText(user.getUserName());
        GlideApp.with(mContext).load(user.getPhotoUrl()).into(qivUserPhoto);
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
    class OfficeHallAdapter extends SingleBaseAdapter<String> {

        /**
         * 带默认空布局显示的
         *
         * @param context
         * @param data
         */
        public OfficeHallAdapter(Context context, @Nullable List<String> data) {
            super(context, data);
        }

        @Override
        public int getLayoutResId() {
            return R.layout.item_office_hall_rdsx;
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            super.convert(helper, item);
            helper.setText(R.id.title, item == null ? "" : item);
            helper.setText(R.id.tv_hot_num, "777");
        }
    }
}
