package com.westar.masseswork_98.ui.activity;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.qmuiteam.qmui.alpha.QMUIAlphaImageButton;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.westar.been.User;
import com.westar.library_base.base.BasePresenter;
import com.westar.library_base.base.ToolbarActivity;
import com.westar.library_base.http.ObserverManager;
import com.westar.library_base.http.been.HttpResult;
import com.westar.library_base.rxjava.RxScheduler;
import com.westar.masseswork_98.AppClient;
import com.westar.masseswork_98.R;
import com.westar.masseswork_98.adapter.ContactInformationAdapter;
import com.westar.masseswork_98.been.ContactInfo;
import com.westar.masseswork_98.util.Utils;

import java.util.List;

import static android.util.TypedValue.COMPLEX_UNIT_SP;

/**
 * 联系方式界面
 * Created by lgy on 19/4/28
 */

public class ContactInformationActivity extends ToolbarActivity {

    private QMUIAlphaImageButton rightTopbar; //右侧定位
    private TextView tvArea; //定位区域
    private ContactInformationAdapter adapter;
    private List<ContactInfo> contactInfoList;
    private RecyclerView recyclerView;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_contact_information;
    }

    @Override
    protected void findId() {
        recyclerView = (RecyclerView) findViewById(R.id.rv_contact_information);
    }

    @Override
    protected void initView() {
        initTvArea();
        adapter = new ContactInformationAdapter(getBaseContext(), null);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        //TODO 服务器好了再开
//        getAdapterList();//获取界面数据
        showLoading();
    }

    @Override
    protected void initData() {

    }

    //初始化定位区域
    private void initTvArea() {
        tvArea = new TextView(mContext);
        //根据获取到的地区设置
        tvArea.setText("青羊区");
        tvArea.setTextColor(Color.parseColor("#828899"));
        tvArea.setTextSize(14);
        Drawable drawable = getResources().getDrawable(R.drawable.icon_top_dz_hs);
        drawable.setBounds(0, 0, Utils.dip2px(mContext, 17), Utils.dip2px(mContext, 20));
        tvArea.setCompoundDrawables(drawable, null, null, null);
        tvArea.setCompoundDrawablePadding(Utils.dip2px(mContext, 9));
        RelativeLayout.LayoutParams rl = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rl.setMargins(0, 0, Utils.dip2px(mContext, 37), 0);
        rl.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        topBarLayout.addRightView(tvArea, 12, rl);
        tvArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseArea();
            }
        });
    }

    //更改定位区域
    private void chooseArea() {
        //TODO 处理定位逻辑
        //根据获取到的地区设置
        tvArea.setText("金牛区");

    }

    //获取数据列表
    private void getAdapterList() {
        AppClient.getInstance()
                .creatAPI()
                .contactInfo(new Gson().toJson(new User()))
                .compose(bindViewToLifecycle())
                .compose(RxScheduler.rxObservableSchedulerHelper())
                .subscribe(new ObserverManager<List<ContactInfo>>(getBaseContext()) {
                    @Override
                    protected void onOther(HttpResult<List<ContactInfo>> httpResult) {

                    }

                    @Override
                    protected void onSuccess(List<ContactInfo> data) {
                        if (data != null && data.size() != 0) {
                            contactInfoList = data;
                            adapter.setNewData(data);
                        }

                    }

                    @Override
                    protected void onFailure(Throwable e) {

                    }

                    @Override
                    protected void onFinish() {
                        hideLoading();
                    }
                });
    }

    @Override
    public String setBarTitle() {
        return "联系方式";
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
