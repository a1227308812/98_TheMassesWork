package com.westar.masseswork_98.ui.activity;

import android.widget.LinearLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.westar.been.AddressNode;
import com.westar.library_base.base.BasePresenter;
import com.westar.library_base.base.ToolbarActivity;
import com.westar.library_base.common.ArouterPath;
import com.westar.library_base.http.been.HttpRequest;
import com.westar.masseswork_98.R;
import com.westar.masseswork_98.interfaces.IChoiceAddressClick;
import com.westar.masseswork_98.mvp.contract.ChoiceAddressContract;
import com.westar.masseswork_98.mvp.presenter.ChoiceAddressPresenter;
import com.westar.masseswork_98.ui.custom.AddressLayout;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * 选择地址界面
 */
@Route(path = ArouterPath.APP_CHOICE_ADDRESS_ACTIVITY)
public class ChoiceAddressActivity extends ToolbarActivity implements ChoiceAddressContract.View {
    private LinearLayout llChoiceAddress;
//    List<AddressNode> lv1List;
//    List<AddressNode> lv2List;
//    AddressLayout lv1Layout;
//    AddressLayout lv2Layout;

    ChoiceAddressPresenter presenter;

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
//        lv1Layout = new AddressLayout(this);
//        llChoiceAddress.addView(lv1Layout);
//        lv1Layout.loadView("成都市", lv1List, new IChoiceAddressClick() {
//            @Override
//            public void itemClick(int pos) {
//                //TODO 这里有很多其它改变状态的方式，到时候自己实现，这里只是临时数据演示
//                for (int i = 0; i < lv1List.size(); i++) {
//                    lv1List.get(i).setCheck(false);
//                }
//                lv1List.get(pos).setCheck(true);
//                lv1Layout.notifyDataSetChanged(lv1List);
//                //如果有下一级且是第一次点击，创建下一级布局
//                if (lv2Layout == null) {
//                    addLeaveLayout(lv1List.get(pos).getNodeName());
//                } else {
//                    //重新获取lv2List的数据，刷新组件
//                    lv2Layout.notifyDataSetChanged(lv1List.get(pos).getNodeName(), lv2List);
//                }
//                //TODO 若有第三级，此处在 切换的时候应该关闭第三级  lv3.expand(false)
//
//            }
//        });
    }

    @Override
    protected void initData() {
        presenter.getAddressList(new HttpRequest());
    }

    @Override
    public String setBarTitle() {
        return "选择地址";
    }

    private void addLeaveLayout(final List<AddressNode> addressNodeList) {
        final AddressNode addressNode = addressNodeList.get(0);
        int parentId = addressNode.getParentId();

        //判断是否需要新增子view的layout
        final AddressLayout leaveLayout = llChoiceAddress.findViewById(parentId);
        if (leaveLayout != null) {
            //刷新数据
            //数据库查询选中的区域的子元素
            Realm realm = Realm.getDefaultInstance();
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    RealmResults<AddressNode> realmResults = realm.where(AddressNode.class).equalTo("parentId", String.valueOf(addressNode.getParentId())).findAll();
                    leaveLayout.notifyDataSetChanged(addressNode.getNodeName(), realmResults);
                }
            });
        } else {
            //新建layout并加载数据
            AddressLayout addressLayout = new AddressLayout(mContext);
            addressLayout.setId(parentId);
            llChoiceAddress.addView(addressLayout);
            if (null != addressNodeList && addressNodeList.size() > 0) {
                addressLayout.loadView(addressNode.getNodeName(), addressNodeList, new IChoiceAddressClick() {
                    @Override
                    public void itemClick(final int pos) {
                        //数据库查询选中的区域的子元素
                        Realm realm = Realm.getDefaultInstance();
                        realm.executeTransaction(new Realm.Transaction() {
                            @Override
                            public void execute(Realm realm) {
                                RealmResults<AddressNode> realmResults = realm.where(AddressNode.class).equalTo("parentId", String.valueOf(addressNodeList.get(pos))).findAll();
                                addLeaveLayout(realmResults);
                            }
                        });
                    }
                });
            }
        }


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
        if (null != data) {
            final List<AddressNode> addressNodes = (List<AddressNode>) data;
            if (null != addressNodes && addressNodes.size() > 0) {
                addLeaveLayout(addressNodes);
            }
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
        return presenter = new ChoiceAddressPresenter();
    }
}
