package com.westar.module_woyaochaxun;

import android.support.v7.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.westar.library_base.base.BasePresenter;
import com.westar.library_base.base.ToolbarActivity;

import butterknife.BindView;

/**
 * 历史办件界面
 * Created by zb on 2019/4/12.
 */

public class BanJianHistoryListActivity extends ToolbarActivity {
    RecyclerView recyList;
    SmartRefreshLayout smartRefresh;

    @Override
    public String setBarTitle() {
        return "历史办件";
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_recy;
    }

    @Override
    protected void findId() {
        recyList = findViewById(R.id.recy_list);
        smartRefresh = findViewById(R.id.smart_refresh);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

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

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public LifecycleTransformer bindViewToLifecycle() {
        return null;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }
}
