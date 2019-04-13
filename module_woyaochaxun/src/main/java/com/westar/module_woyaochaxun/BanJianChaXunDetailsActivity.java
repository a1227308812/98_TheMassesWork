package com.westar.module_woyaochaxun;

import android.view.View;

import com.qmuiteam.qmui.alpha.QMUIAlphaImageButton;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.westar.library_base.base.BasePresenter;
import com.westar.library_base.base.ToolbarActivity;

/**
 * 办件详情(我的办件详情)界面
 * Created by zb on 2019/4/11.
 */

public class BanJianChaXunDetailsActivity extends ToolbarActivity {

    //1 "我的办件"   else  "办件详情"
    String type;

    //右上角按钮
    QMUIAlphaImageButton rightImgBtn;

    @Override
    public String setBarTitle() {
        if (type.equals("1")){
            return "我的办件";
        }else {
            return "办件详情";
        }
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_ban_jian_cha_xun_details;
    }

    @Override
    protected void findId() {

    }

    @Override
    protected void initView() {
        if (type.equals("1")){
           rightImgBtn = topbar.addRightImageButton(R.drawable.icon_top_ls,-1);
        }

        setListener();
    }

    private void setListener() {
        rightImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                skipActivity(BanJianHistoryListActivity.class,null);
            }
        });
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
