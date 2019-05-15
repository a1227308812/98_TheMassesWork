package com.westar.masseswork_98.ui.activity;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qmuiteam.qmui.alpha.QMUIAlphaImageButton;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.westar.library_base.base.BasePresenter;
import com.westar.library_base.base.ToolbarActivity;
import com.westar.masseswork_98.R;
import com.westar.masseswork_98.util.Utils;

import static android.util.TypedValue.COMPLEX_UNIT_SP;

/**
 * 联系方式界面
 * Created by lgy on 19/4/28
 */

public class ContactInformationActivity extends ToolbarActivity {

    private QMUIAlphaImageButton rightTopbar; //右侧定位
    private TextView tvArea; //定位区域

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

    }

    @Override
    protected void initView() {
        setTopBarRightNavigation(); //设置右侧定位及监听
    }

    @Override
    protected void initData() {

    }

    //设置右侧定位及监听
    private void setTopBarRightNavigation() {
        rightTopbar = topBarLayout.addRightImageButton(R.drawable.icon_top_dz_ss, 11);
        rightTopbar.setChangeAlphaWhenPress(false);
        rightTopbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initTvArea();
            }
        });
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
        rl.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        topBarLayout.addRightView(tvArea, 12, rl);
        if (!tvArea.getText().toString().isEmpty()) {
            rightTopbar.setVisibility(View.INVISIBLE);
        }
        tvArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseArea();
            }
        });
    }

    //更改定位区域
    private void chooseArea() {
        //处理定位逻辑
        //根据获取到的地区设置
        tvArea.setText("金牛区");

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
