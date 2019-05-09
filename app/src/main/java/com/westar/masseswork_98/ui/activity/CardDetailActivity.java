package com.westar.masseswork_98.ui.activity;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.AppCompatImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.coorchice.library.SuperTextView;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.uuzuche.lib_zxing.DisplayUtil;
import com.westar.library_base.base.BasePresenter;
import com.westar.library_base.base.ToolbarActivity;
import com.westar.library_base.common.ArouterPath;
import com.westar.library_base.glide.GlideApp;
import com.westar.masseswork_98.R;
import com.westar.masseswork_98.been.MeCardInfo;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ZWP on 2019/5/9 11:25.
 * 描述：证照详情界面
 */
@Route(path = ArouterPath.APP_CARD_DETAIL_ACTIVITY)
public class CardDetailActivity extends ToolbarActivity {

    @BindView(R.id.iv_qr_code)
    AppCompatImageView ivQrCode;
    @BindView(R.id.tv_tip)
    TextView tvTip;
    @BindView(R.id.iv_card_type)
    AppCompatImageView ivCardType;
    @BindView(R.id.tv_titlle)
    TextView tvTitlle;
    @BindView(R.id.tv_describle)
    TextView tvDescrible;
    @BindView(R.id.tv_describle2)
    TextView tvDescrible2;
    @BindView(R.id.tv_authentication_status)
    SuperTextView tvAuthenticationStatus;
    @BindView(R.id.cl_card_menu_layout)
    ConstraintLayout clCardMenuLayout;

    MeCardInfo meCardInfo;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_card_detail;
    }

    @Override
    protected void findId() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        GlideApp.with(mContext).load(R.drawable.qr_code).into(ivQrCode);
        GlideApp.with(mContext).load(meCardInfo.getTypeUrl()).into(ivCardType);
        tvTip.setText(meCardInfo.getDescrible3());
        tvTitlle.setText(meCardInfo.getTitle());
        tvDescrible.setText(meCardInfo.getDescrible());
        tvDescrible2.setText(meCardInfo.getDescrible2());
        tvAuthenticationStatus.setText(meCardInfo.getAuthenticationStatus());


        // 创建渐变的shape drawable
        String[] colors = meCardInfo.getColors();
        int colorsRes[] = new int[colors.length];
        for (int i = 0; i < colors.length; i++) {
            colorsRes[i] = Color.parseColor(colors[i]);
        }
        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, colorsRes);
        float[] radius = {0, 0, 0, 0,
                DisplayUtil.dip2px(mContext, 10), DisplayUtil.dip2px(mContext, 10),
                DisplayUtil.dip2px(mContext, 10), DisplayUtil.dip2px(mContext, 10)};
        gradientDrawable.setCornerRadii(radius);

        clCardMenuLayout.setBackground(gradientDrawable);
    }

    @Override
    public String setBarTitle() {
        Serializable serializable = getIntentSerializable("MeCardInfo");
        if (serializable != null) {
            meCardInfo = (MeCardInfo) serializable;
            return meCardInfo.getTitle();
        }
        return "我的证照";
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
