package com.westar.module_woyaochaxun;

import android.graphics.Color;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.westar.library_base.base.BasePresenter;
import com.westar.library_base.base.ToolbarActivity;
import com.westar.library_base.common.ArouterPath;

/**
 * Created by ZWP on 2019/4/8 13:10.
 * 描述：办件查询界面
 */
@Route(path = ArouterPath.MODULE_WOYAOCHAXUN_BAN_JIAN_CHA_XUN_ACTIVITY)
public class BanJianChaXunActivity extends ToolbarActivity {


    TextInputEditText etSearch;
    TextView btnSearch;
    RecyclerView recyDocuments;



    @Override
    protected int getLayoutID() {
        return R.layout.activity_ban_jian_cha_xun;
    }

    @Override
    protected void findId() {
        etSearch = findViewById(R.id.et_search);
        btnSearch = findViewById(R.id.btn_search);
        recyDocuments = findViewById(R.id.recy_documents);
    }

    @Override
    protected void initView() {

        topBarLayout.addLeftImageButton(R.drawable.icon_other_fh, -1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        topBarLayout.addRightImageButton(R.drawable.icon_top_sys, -1);
        topBarLayout.setTitle("办件查询").setTextColor(Color.parseColor("#333333"));
    }

    @Override
    protected void initData() {

    }

    @Override
    public String setBarTitle() {
        return "办件查询";
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
