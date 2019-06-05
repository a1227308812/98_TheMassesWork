package com.westar.module_woyaobanshi.ui.fragment;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.westar.library_base.base.BaseFragment;
import com.westar.library_base.base.BasePresenter;
import com.westar.module_woyaobanshi.R;


/**
 * Created by ZWP on 2019/6/4 9:35.
 * 描述：办事流程图
 */
public class WorkFlowChartFragment extends BaseFragment {
    AppCompatImageView ivWorkFlowChart;

    @Override
    protected void lazyLoadShow(boolean isLoad) {

    }

    @Override
    protected void lazyLoadHide(boolean isLoad) {

    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_work_flow_chart;
    }

    @Override
    protected void initView() {
        ivWorkFlowChart = rootView.findViewById(R.id.iv_work_flow_chart);
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

}
