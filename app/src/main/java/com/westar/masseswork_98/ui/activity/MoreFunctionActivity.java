package com.westar.masseswork_98.ui.activity;

import android.Manifest;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.auto.common.MoreElements;
import com.qmuiteam.qmui.alpha.QMUIAlphaImageButton;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.westar.library_base.base.BasePresenter;
import com.westar.library_base.base.ToolbarActivity;
import com.westar.library_base.common.ArouterPath;
import com.westar.masseswork_98.R;
import com.westar.masseswork_98.adapter.MoreFuncIconAdapter;
import com.westar.masseswork_98.been.MoreFuncIconItem;
import com.westar.masseswork_98.util.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 更多功能界面
 * Created by lgy on 19/4/28
 */
@Route(path = ArouterPath.APP_MOREFUNCTION_ACTIVITY)
public class MoreFunctionActivity extends ToolbarActivity {

    private RecyclerView rvTotalFunc; //所有功能列表
    private MoreFuncIconAdapter moreFuncIconAdapter;
    private List<MoreFuncIconItem> moreFuncIconItems;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_more_function;
    }

    @Override
    protected void findId() {
        rvTotalFunc = (RecyclerView) findViewById(R.id.rv_total_function);
    }

    @Override
    protected void initView() {
        initRvTotalFuncData();
        initRvTotalFunc();
    }

    @Override
    protected void initData() {

    }

    private void initRvTotalFunc() {
        rvTotalFunc.setLayoutManager(new GridLayoutManager(this, 4));
        moreFuncIconAdapter = new MoreFuncIconAdapter(getBaseContext(), moreFuncIconItems);
        moreFuncIconAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                switch (position) {
                    case 2:
                        if (Utils.checkPermission(MoreFunctionActivity.this, Manifest.permission.ACCESS_FINE_LOCATION, 1)) {
                            skipActivity(CenterNavigationActivity.class, null);
                        }
                        break;
                    case 3:
                        skipActivity(ContactInformationActivity.class, null);
                        break;
                    default:
                        ToastUtils.showShort("u click the" + position);
                        break;
                }
            }
        });
        rvTotalFunc.setAdapter(moreFuncIconAdapter);
    }

    private void initRvTotalFuncData() {
        moreFuncIconItems = new ArrayList<>();
        MoreFuncIconItem moreFuncIconItem1 = new MoreFuncIconItem("热点事项", getResources().getDrawable(R.drawable.icon_gdgn_rdsx));
        MoreFuncIconItem moreFuncIconItem2 = new MoreFuncIconItem("便民服务", getResources().getDrawable(R.drawable.icon_gdgn_bmfw));
        MoreFuncIconItem moreFuncIconItem3 = new MoreFuncIconItem("中心导航", getResources().getDrawable(R.drawable.icon_gdgn_zxdh));
        MoreFuncIconItem moreFuncIconItem4 = new MoreFuncIconItem("联系方式", getResources().getDrawable(R.drawable.icon_gdgn_lxfs));
        MoreFuncIconItem moreFuncIconItem5 = new MoreFuncIconItem("权责清单", getResources().getDrawable(R.drawable.icon_gdgn_qzqd));

        moreFuncIconItems.add(moreFuncIconItem1);
        moreFuncIconItems.add(moreFuncIconItem2);
        moreFuncIconItems.add(moreFuncIconItem3);
        moreFuncIconItems.add(moreFuncIconItem4);
        moreFuncIconItems.add(moreFuncIconItem5);
    }

    @Override
    public String setBarTitle() {
        return "更多功能";
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
