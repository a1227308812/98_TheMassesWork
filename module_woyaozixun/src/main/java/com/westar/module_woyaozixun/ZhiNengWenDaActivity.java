package com.westar.module_woyaozixun;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.qmuiteam.qmui.alpha.QMUIAlphaImageButton;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.westar.library_base.base.BasePresenter;
import com.westar.library_base.base.ToolbarActivity;
import com.westar.library_base.common.ArouterPath;
import com.westar.module_woyaozixun.adapter.MyRecyclerAdapter;
import com.westar.module_woyaozixun.util.Utils;
import com.westar.module_woyaozixun.widget.MyPopWindow;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZWP on 2019/4/8 11:24.
 * 描述：智能问答界面
 */
@Route(path = ArouterPath.ZHINENGWENDA_ACTIVITY)
public class ZhiNengWenDaActivity extends ToolbarActivity {

    private MyPopWindow myPopWindow; //自定义弹出菜单
    private RecyclerView mRecyclerView; //对话界面
    private List<Integer> typeList = new ArrayList<>();
    MyRecyclerAdapter adapter = new MyRecyclerAdapter(this, typeList);
    private EditText etvInput;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_zhi_neng_wen_da;
    }

    @Override
    protected void findId() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        etvInput = (EditText) findViewById(R.id.etv_zixun_input);
    }

    @Override
    protected void initView() {
        //myPopWindow = new MyPopWindow(this, Utils.dip2px(mContext, 120), Utils.dip2px(mContext, 80));
        myPopWindow = new MyPopWindow(this);
        isTopBarBackButton(); //是否有toolbar的返回键和右上方“+”监听
        initRecyclerView();
        findViewById(R.id.stv_zixun_send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.addData(typeList.size());
                adapter.addData(typeList.size());
                mRecyclerView.smoothScrollToPosition(typeList.size());
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    public String setBarTitle() {
        return "智能问答";
    }

    //是否有toolbar的返回键和右上方“+”监听
    private void isTopBarBackButton() {
        final QMUIAlphaImageButton rightTopbar = topBarLayout.addRightImageButton(R.drawable.ic_add, getLayoutID());
        rightTopbar.setChangeAlphaWhenPress(false);
        rightTopbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myPopWindow.showAtLocation(rightTopbar, Gravity.NO_GRAVITY, Utils.dip2px(mContext, 240), Utils.dip2px(mContext, 56));
            }
        });
    }

    private void initFirstData() {
        typeList.add(1);
    }

    private void initRecyclerView() {
        initFirstData();
        mRecyclerView= (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);
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
}
