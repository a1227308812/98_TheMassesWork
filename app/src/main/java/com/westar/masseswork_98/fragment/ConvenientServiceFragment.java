package com.westar.masseswork_98.fragment;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.ToastUtils;
import com.jakewharton.rxbinding2.view.RxView;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;
import com.qmuiteam.qmui.widget.QMUITabSegment;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.uuzuche.lib_zxing.DisplayUtil;
import com.westar.Config;
import com.westar.library_base.base.BaseFragment;
import com.westar.library_base.base.BaseMvpFragment;
import com.westar.library_base.base.BasePresenter;
import com.westar.library_base.common.ArouterPath;
import com.westar.library_base.glide.GlideApp;
import com.westar.library_base.view.TopBarLayout;
import com.westar.masseswork_98.R;
import com.westar.masseswork_98.been.NewsInformationTabs;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.functions.Consumer;

/**
 * Created by ZWP on 2019/4/8 20:28.
 * 描述：便民服务
 */
public class ConvenientServiceFragment extends BaseFragment {
    @BindView(R.id.toolbar_layout)
    TopBarLayout toolbarLayout;

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
        return null;
    }

    @Override
    protected void lazyLoadShow(boolean isLoad) {

    }

    @Override
    protected void lazyLoadHide(boolean isLoad) {

    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_convenien_service;
    }

    @Override
    protected void initView() {
        //不显示默认返回键
        toolbarLayout.showBackView(false)
                .setTitle("便民服务");
        RelativeLayout.LayoutParams leftParams = new RelativeLayout.LayoutParams(DisplayUtil.dip2px(mContext, 30), DisplayUtil.dip2px(mContext, 30));
        leftParams.addRule(RelativeLayout.CENTER_VERTICAL);
        QMUIRadiusImageView leftView = new QMUIRadiusImageView(mContext);
        leftView.setCircle(true);
        leftView.setLayoutParams(leftParams);
        GlideApp.with(mContext).load("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=328179059,3377101288&fm=27&gp=0.jpg").into(leftView);

        View rightView = LayoutInflater.from(mContext).inflate(R.layout.top_right_view, null);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_VERTICAL);
        rightView.setLayoutParams(params);

        toolbarLayout.addLeftView(leftView, R.id.top_left_address);
        toolbarLayout.addRightView(rightView, R.id.top_right_menu);


        addSubscribe(RxView.clicks(leftView)
                .throttleFirst(Config.WINDOWDURATION, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        ToastUtils.showShort("跳转我的页面");
                    }
                }));

        addSubscribe(RxView.clicks(rightView.findViewById(R.id.iv_more))
                .throttleFirst(Config.WINDOWDURATION, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        ToastUtils.showShort("跳转更多功能");
                    }
                }));

        addSubscribe(RxView.clicks(rightView.findViewById(R.id.iv_search))
                .throttleFirst(Config.WINDOWDURATION, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        ToastUtils.showShort("跳转搜索");
                        ARouter.getInstance().build(ArouterPath.APP_SEARCH_ACTIVITY).navigation();
                    }
                }));
    }

    @Override
    protected void initData() {

    }
}
