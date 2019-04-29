package com.westar.masseswork_98.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.coorchice.library.SuperTextView;
import com.qmuiteam.qmui.widget.QMUITabSegment;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.uuzuche.lib_zxing.DisplayUtil;
import com.westar.library_base.base.BaseFragment;
import com.westar.library_base.base.BasePresenter;
import com.westar.library_base.common.Common;
import com.westar.library_base.glide.GlideApp;
import com.westar.masseswork_98.R;
import com.westar.masseswork_98.ui.HomeGroupActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by ZWP on 2019/4/29 15:19.
 * 描述：已经登录并认证通过的侧边fragment
 */
public class NormalSolideFragment extends BaseFragment {
    @BindView(R.id.stv_user_photo)
    SuperTextView stvUserPhoto;
    @BindView(R.id.stv_djrz)
    SuperTextView stvDjrz;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.me_child_menu)
    QMUITabSegment meChildMenu;
    @BindView(R.id.ll_more_card)
    LinearLayout llMoreCard;
    @BindView(R.id.recyc_me_cards)
    RecyclerView recycMeCards;
    @BindView(R.id.ll_wdsc)
    LinearLayout llWdsc;
    @BindView(R.id.ll_dzgl)
    LinearLayout llDzgl;
    @BindView(R.id.ll_wdkd)
    LinearLayout llWdkd;
    @BindView(R.id.ll_wdzf)
    LinearLayout llWdzf;
    @BindView(R.id.ll_gdsz)
    LinearLayout llGdsz;
    @BindView(R.id.ll_tcdl)
    LinearLayout llTcdl;

    @Override
    protected void lazyLoadShow(boolean isLoad) {

    }

    @Override
    protected void lazyLoadHide(boolean isLoad) {

    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_slide_normal;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        if (getArguments() != null) {
            boolean hadAuthentication = getArguments().getBoolean(Common.AUTHENTICATION_TYPE, false);
            if (hadAuthentication) {
                //已认证
                stvDjrz.setDrawableAsBackground(true);
                stvDjrz.setDrawable(R.drawable.icon_wd_rz);
                stvDjrz.setWidth(getResources().getDimensionPixelSize(R.dimen.dp_14));
                stvDjrz.setHeight(getResources().getDimensionPixelSize(R.dimen.dp_14));
            } else {
                //未认证
                stvDjrz.setSolid(R.color.white);
                stvDjrz.setCorner(getResources().getDimension(R.dimen.dp_20));
                stvDjrz.setText("点击认证");
                stvDjrz.setPadding(getResources().getDimensionPixelSize(R.dimen.dp_7),
                        getResources().getDimensionPixelSize(R.dimen.dp_3),
                        getResources().getDimensionPixelSize(R.dimen.dp_7),
                        getResources().getDimensionPixelSize(R.dimen.dp_3));
            }
        }
        stvUserPhoto.setUrlImage("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=328179059,3377101288&fm=27&gp=0.jpg");
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
    public Context getBaseContext() {
        return mContext;
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
