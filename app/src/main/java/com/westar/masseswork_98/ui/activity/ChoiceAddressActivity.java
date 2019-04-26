package com.westar.masseswork_98.ui.activity;

import android.widget.LinearLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.westar.library_base.base.BasePresenter;
import com.westar.library_base.base.ToolbarActivity;
import com.westar.library_base.common.ArouterPath;
import com.westar.masseswork_98.R;
import com.westar.masseswork_98.been.AddressNode;
import com.westar.masseswork_98.interfaces.IChoiceAddressClick;
import com.westar.masseswork_98.ui.custom.AddressLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 选择地址界面
 */
@Route(path = ArouterPath.APP_CHOICE_ADDRESS_ACTIVITY)
public class ChoiceAddressActivity extends ToolbarActivity {
    private LinearLayout llChoiceAddress;
    List<AddressNode> lv1List;
    List<AddressNode> lv2List;
    AddressLayout lv1Layout;
    AddressLayout lv2Layout;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_choice_address;
    }

    @Override
    protected void findId() {
        llChoiceAddress = findViewById(R.id.llChoiceAddress);
    }

    @Override
    protected void initView() {
        testData();
        lv1Layout = new AddressLayout(this);
        llChoiceAddress.addView(lv1Layout);
        lv1Layout.loadView("成都市", lv1List, new IChoiceAddressClick() {
            @Override
            public void itemClick(int pos) {
                //TODO 这里有很多其它改变状态的方式，到时候自己实现，这里只是临时数据演示
                for (int i = 0; i < lv1List.size(); i++) {
                    lv1List.get(i).setCheck(false);
                }
                lv1List.get(pos).setCheck(true);
                lv1Layout.notifyDataSetChanged(lv1List);
                //如果有下一级且是第一次点击，创建下一级布局
                if (lv2Layout == null) {
                    createLv2Layout(lv1List.get(pos).getNodeName());
                } else {
                    //重新获取lv2List的数据，刷新组件
                    lv2Layout.notifyDataSetChanged(lv1List.get(pos).getNodeName(), lv2List);
                }
                //TODO 若有第三级，此处在 切换的时候应该关闭第三级  lv3.expand(false)

            }
        });
    }

    @Override
    protected void initData() {

    }

    /**
     * 测试数据
     */
    private void testData() {
        lv1List = new ArrayList<>();
        lv1List.add(new AddressNode(1, "成都市", -1, false));
        lv1List.add(new AddressNode(2, "高新区", -1, false));
        lv1List.add(new AddressNode(3, "高新区", -1, false));
        lv1List.add(new AddressNode(4, "高新区", -1, false));
        lv1List.add(new AddressNode(5, "高新区", -1, false));
        lv1List.add(new AddressNode(6, "高新区", -1, false));


        lv2List = new ArrayList<>();
        lv2List.add(new AddressNode(1, "高新区", 1, false));
        lv2List.add(new AddressNode(2, "XX街道", 1, false));
        lv2List.add(new AddressNode(2, "XXX街道", 1, false));
        lv2List.add(new AddressNode(2, "XXXXXX街道", 1, false));
    }

    @Override
    public String setBarTitle() {
        return "选择地址";
    }

    private void createLv2Layout(String labelName) {
        lv2Layout = new AddressLayout(this);
        lv2Layout.loadView(labelName, lv2List, new IChoiceAddressClick() {
            @Override
            public void itemClick(int pos) {

            }
        });
        llChoiceAddress.addView(lv2Layout);
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

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }
}
