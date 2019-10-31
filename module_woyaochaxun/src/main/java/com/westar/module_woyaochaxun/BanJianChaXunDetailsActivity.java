package com.westar.module_woyaochaxun;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.westar.library_base.base.BasePresenter;
import com.westar.library_base.base.ToolbarActivity;
import com.westar.module_woyaochaxun.adapter.TimeLineAdapter;
import com.westar.module_woyaochaxun.model.DealItem;
import com.westar.module_woyaochaxun.model.TimeLineNodeModel;

import java.util.List;

/**
 * 办件详情界面
 * Created by zb on 2019/4/11.
 */

public class BanJianChaXunDetailsActivity extends ToolbarActivity {

    ConstraintLayout consTop; //事项版面
    TextView tvTitleName; //事项名称
    TextView tvDealDepName; //办理部门
    TextView tvDealNo; //受理编号
    TextView tvDoEndDate; //承若办结日期
    TextView tvVerifyState; //审核状态
    ConstraintLayout consMid; //申请人版面
    TextView tvPersonName; //申请人姓名
    TextView tvIdCard; //身份证
    ImageView ivQRCode; //二维码
    RecyclerView rvTimeNode; //时间轴
    TimeLineAdapter adapter; //时间轴适配器
    DealItem dealItem = new DealItem();
    List<TimeLineNodeModel> timeLineNodeModels;
    @Override
    public String setBarTitle() {
            return "办件详情";
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_ban_jian_cha_xun_details;
    }

    @Override
    protected void findId() {
        consTop = findViewById(R.id.cons_top);
        tvTitleName = findViewById(R.id.tv_title_name);
        tvDealDepName = findViewById(R.id.tv_deal_dep_name);
        tvDealNo = findViewById(R.id.tv_deal_no);
        tvDoEndDate = findViewById(R.id.tv_do_end_date);
        tvVerifyState = findViewById(R.id.tv_verrify_state);
        consMid = findViewById(R.id.cons_mid);
        tvPersonName = findViewById(R.id.tv_person_name);
        tvIdCard = findViewById(R.id.tv_id_card);
        ivQRCode = findViewById(R.id.iv_QR_code);
        rvTimeNode = findViewById(R.id.rv_time_node);
    }

    @Override
    protected void initView() {
        showInitView(); //加载初始化界面
        //TODO layoutResl
//        adapter = new TimeLineAdapter(null, timeLineNodeModels, getBaseContext());
        rvTimeNode.setLayoutManager(new LinearLayoutManager(this));
        rvTimeNode.setAdapter(adapter);
        setListener();
    }

    private void setListener() {

    }

    @Override
    protected void initData() {
        //事项版面跳转
        consTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
            }
        });

        //申请人版面跳转
        consMid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
            }
        });
    }

    //加载初始化界面
    private void showInitView() {
        dealItem = (DealItem)getIntent().getSerializableExtra("dealItem");
        tvTitleName.setText(dealItem.getItemName());
        tvDealDepName.setText(dealItem.getDepName());
        tvDealNo.setText(dealItem.getDealNo());
        tvDoEndDate.setText(dealItem.getFinishDate());
        if ("0".equals(dealItem.getVerifyState())) {
            tvVerifyState.setText("审核中");
        }else if ("1".equals(dealItem.getVerifyState())) {
            tvVerifyState.setText("已审核");
        }
        tvPersonName.setText(dealItem.getName());
        tvIdCard.setText(dealItem.getIdentityId());
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
