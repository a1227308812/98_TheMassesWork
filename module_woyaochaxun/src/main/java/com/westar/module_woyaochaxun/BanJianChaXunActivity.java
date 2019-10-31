package com.westar.module_woyaochaxun;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.qmuiteam.qmui.alpha.QMUIAlphaImageButton;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.westar.been.User;
import com.westar.library_base.base.BasePresenter;
import com.westar.library_base.base.ToolbarActivity;
import com.westar.library_base.callback.IPermissionsCallBack;
import com.westar.library_base.common.ArouterPath;
import com.westar.library_base.http.ObserverManager;
import com.westar.library_base.http.been.HttpResult;
import com.westar.library_base.rxjava.RxScheduler;
import com.westar.module_woyaochaxun.adapter.BanJianChaXunAdpter;
import com.westar.module_woyaochaxun.model.DealItem;

import java.util.List;

/**
 * Created by ZWP on 2019/4/8 13:10.
 * 描述：办件查询界面
 */
@Route(path = ArouterPath.BANJIANCHAXUN_ACTIVITY)
public class BanJianChaXunActivity extends ToolbarActivity {


    TextInputEditText etSearch;
    TextView btnSearch;
    TextView tvMyDeal;
    RecyclerView recyDocuments;
    BanJianChaXunAdpter adapter;
    SmartRefreshLayout smartRefreshLayout;
    User user;

    private final static int REQUEST_CODE_NORMAL = 10000;



    @Override
    protected int getLayoutID() {
        return R.layout.activity_ban_jian_cha_xun;
    }

    @Override
    protected void findId() {
        etSearch = findViewById(R.id.et_search);
        btnSearch = findViewById(R.id.btn_search);
        tvMyDeal = findViewById(R.id.tv_my_deal);
        recyDocuments = findViewById(R.id.recy_documents);
        smartRefreshLayout = findViewById(R.id.smart_refresh);
    }

    @Override
    protected void initView() {
        isTopBarBackButton(); //是否有toolbar的返回键和设置右上方扫描监听
        initListener();
        adapter = new BanJianChaXunAdpter(getBaseContext(), null);
        recyDocuments.setLayoutManager(new LinearLayoutManager(this));
        recyDocuments.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        //权限申请
        requestPermissions(new String[]{Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_PHONE_STATE},
                true,
                true,
                new IPermissionsCallBack() {
                    @Override
                    public void permissionErro(String name) {
                        ToastUtils.showShort("获取权限失败！");
                    }

                    @Override
                    public void permissionSuccess(String name) {

                    }
                });

    }

    //是否有toolbar的返回键和设置右上方扫描监听
    private void isTopBarBackButton() {
        final QMUIAlphaImageButton rightTopbar = topBarLayout.addRightImageButton(R.drawable.icon_top_sys, getLayoutID());
        rightTopbar.setChangeAlphaWhenPress(false);
        rightTopbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                skipActivityForResult(SaoYiSaoActivity.class, REQUEST_CODE_NORMAL, null);
            }
        });
    }

    private void initRecyclerData() {

    }

    public void initListener() {
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (etSearch.getText().toString().trim().isEmpty()) {
                    ToastUtils.showShort("请输入身份证/手机号码/办件编号");
                    return;
                } else {
                    showLoading();
                    AppClient.getInstance()
                            .creatAPI()
                            .chaxunDealItemList(etSearch.getText().toString().trim())
                            .compose(bindViewToLifecycle())
                            .compose(RxScheduler.rxObservableSchedulerHelper())
                            .subscribe(new ObserverManager<List<DealItem>>(getBaseContext()) {
                                @Override
                                protected void onOther(HttpResult<List<DealItem>> httpResult) {

                                }

                                @Override
                                protected void onSuccess(List<DealItem> data) {
                                    hideLoading();
                                    if (data != null && data.size() != 0) {
                                        adapter.setNewData(data);
                                    }
                                }

                                @Override
                                protected void onFailure(Throwable e) {

                                }

                                @Override
                                protected void onFinish() {

                                }
                            });
                }
            }
        });

        //TODO 跳转我的办件
        tvMyDeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showShort("跳转我的办件");
            }
        });

        //TODO item跳转
//        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                DealItem dealItem = (DealItem)adapter.getData().get(position);
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("dealItem", dealItem);
//                skipActivity(BanJianChaXunDetailsActivity.class, bundle);
//            }
//        });

        smartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {

            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_NORMAL) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    ToastUtils.showShort("解析结果:" + result);
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    ToastUtils.showShort("解析二维码失败");
                }
            }
        }
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
        List<DealItem> dealItems = (List<DealItem>)data;
        adapter = new BanJianChaXunAdpter(getBaseContext(), dealItems);
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
