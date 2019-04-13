package com.westar.module_login.ui;

import android.os.Bundle;

import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.westar.library_base.base.BasePresenter;
import com.westar.library_base.base.ToolbarActivity;
import com.westar.module_login.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ZWP on 2019/4/11 16:43.
 * 描述：确认个人信息界面
 */
public class ConfirmPersonalInformationActivity extends ToolbarActivity {

    QMUIGroupListView itemviewGroup;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_confirm_personal_information;
    }

    @Override
    protected void findId() {
        itemviewGroup = findViewById(R.id.itemview_group);
    }

    @Override
    protected void initView() {
        QMUICommonListItemView nameView = itemviewGroup.createItemView("*姓名");
        nameView.setOrientation(QMUICommonListItemView.VERTICAL);
        nameView.setDetailText("刘江");
        QMUICommonListItemView idCardView = itemviewGroup.createItemView("*身份证号码");
        idCardView.setOrientation(QMUICommonListItemView.VERTICAL);
        idCardView.setDetailText("51221332434343434X");

        QMUIGroupListView.newSection(mContext)
                .addItemView(nameView, null)
                .addItemView(idCardView, null)
                .addTo(itemviewGroup);
    }

    @Override
    protected void initData() {

    }

    @Override
    public String setBarTitle() {
        return "确认个人信息";
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
        return this.bindToLifecycle();
    }
}
