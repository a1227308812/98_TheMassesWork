package com.westar.masseswork_98.fragment;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.ToastUtils;
import com.coorchice.library.SuperTextView;
import com.jakewharton.rxbinding2.view.RxView;
import com.qmuiteam.qmui.widget.QMUITabSegment;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.uuzuche.lib_zxing.DisplayUtil;
import com.westar.Config;
import com.westar.library_base.base.BaseFragment;
import com.westar.library_base.base.BasePresenter;
import com.westar.library_base.common.ArouterPath;
import com.westar.library_base.common.Common;
import com.westar.masseswork_98.R;
import com.westar.masseswork_98.been.MeCardInfo;
import com.westar.masseswork_98.ui.custom.CustomHorizontalScrollView;
import com.westar.masseswork_98.ui.custom.CustomTabSegment;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.functions.Consumer;

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
    CustomTabSegment meChildMenu;
    @BindView(R.id.ll_more_card)
    LinearLayout llMoreCard;
    @BindView(R.id.recyc_me_cards)
    CustomHorizontalScrollView recycMeCards;
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
        //动态加载子menu
        QMUITabSegment.Tab tab1 = new QMUITabSegment.Tab(ContextCompat.getDrawable(mContext, R.drawable.icon_wd_sb),
                ContextCompat.getDrawable(mContext, R.drawable.icon_wd_sb), "我的申办", false);
        QMUITabSegment.Tab tab2 = new QMUITabSegment.Tab(ContextCompat.getDrawable(mContext, R.drawable.icon_bsdt_wyyy),
                ContextCompat.getDrawable(mContext, R.drawable.icon_bsdt_wyyy), "我的预约", false);
        QMUITabSegment.Tab tab3 = new QMUITabSegment.Tab(ContextCompat.getDrawable(mContext, R.drawable.icon_bsdt_wycx),
                ContextCompat.getDrawable(mContext, R.drawable.icon_bsdt_wycx), "我的办件", false);
        QMUITabSegment.Tab tab4 = new QMUITabSegment.Tab(ContextCompat.getDrawable(mContext, R.drawable.icon_bsdt_wyts),
                ContextCompat.getDrawable(mContext, R.drawable.icon_bsdt_wyts), "我的投诉", false);
        QMUITabSegment.Tab tab5 = new QMUITabSegment.Tab(ContextCompat.getDrawable(mContext, R.drawable.icon_bsdt_znwd),
                ContextCompat.getDrawable(mContext, R.drawable.icon_bsdt_znwd), "我的咨询", false);

        //批量初始化tab
        initChildTab(tab1, tab2, tab3, tab4, tab5);

        meChildMenu.addTab(tab1)
                .addTab(tab2)
                .addTab(tab3)
                .addTab(tab4)
                .addTab(tab5)
                .setTabTextSize(DisplayUtil.dip2px(mContext, 12));

        meChildMenu.notifyDataChanged();//刷新


        recycMeCards.addChildView(new MeCardInfo().setTitle("电子身份证明")
                        .setDescrible("身份证")
                        .setDescrible2("511300199911223344")
                        .setAuthenticationStatus("已认证")
                        .setTypeUrl("http://image.baidu.com/search/detail?ct=503316480&z=&tn=baiduimagedetail&ipn=d&word=%E5%B0%8F%E5%9B%BE%E7%89%87&step_word=&ie=utf-8&in=&cl=2&lm=-1&st=-1&hd=&latest=&copyright=&cs=158094193,417757867&os=1574650139,1035232813&simid=3400835474,357383001&pn=12&rn=1&di=78210&ln=859&fr=&fmq=1556616716009_R&ic=&s=undefined&se=&sme=&tab=0&width=&height=&face=undefined&is=0,0&istype=2&ist=&jit=&bdtype=0&spn=0&pi=0&gsm=0&objurl=http%3A%2F%2Fimg.alicdn.com%2Fimgextra%2Fi2%2F1134343704%2FTB2D.RZp3JlpuFjSspjXXcT.pXa_%2521%25211134343704.gif&rpstart=0&rpnum=0&adpicid=0&force=undefined"),
                new CustomHorizontalScrollView.ItemViewClick() {
                    @Override
                    public void onClick(MeCardInfo meCardInfo) {
                        ToastUtils.showShort(meCardInfo.getTitle());
                    }
                }).addChildView(new MeCardInfo().setTitle("社会保障卡")
                        .setDescrible("成都市人力资源和社会保障厅")
                        .setDescrible2("社保查询、医保支付")
                        .setAuthenticationStatus("未关联")
                        .setTypeUrl("http://img0.imgtn.bdimg.com/it/u=1111126966,1527244034&fm=26&gp=0.jpg"),
                new CustomHorizontalScrollView.ItemViewClick() {
                    @Override
                    public void onClick(MeCardInfo meCardInfo) {
                        ToastUtils.showShort(meCardInfo.getTitle());
                    }
                }).addChildView(new MeCardInfo().setTitle("驾驶证")
                        .setDescrible("成都市交通管理局")
                        .setDescrible2("违章缴费，到期换证")
                        .setAuthenticationStatus("已认证")
                        .setTypeUrl("http://img3.imgtn.bdimg.com/it/u=1736640203,2595736136&fm=26&gp=0.jpg"),
                new CustomHorizontalScrollView.ItemViewClick() {
                    @Override
                    public void onClick(MeCardInfo meCardInfo) {
                        ToastUtils.showShort(meCardInfo.getTitle());
                    }
                });

        initListener();
    }

    /**
     * 初始化tab参数
     *
     * @param tabs
     */
    private void initChildTab(QMUITabSegment.Tab... tabs) {
        for (QMUITabSegment.Tab tab : tabs) {
            tab.getNormalIcon().setBounds(0, 0, getResources().getDimensionPixelSize(R.dimen.dp_25), getResources().getDimensionPixelSize(R.dimen.dp_25));
            tab.getSelectedIcon().setBounds(0, 0, getResources().getDimensionPixelSize(R.dimen.dp_25), getResources().getDimensionPixelSize(R.dimen.dp_25));
            tab.setTextColor(ContextCompat.getColor(mContext, R.color.white), ContextCompat.getColor(mContext, R.color.white));
        }
    }

    private void initListener() {

        meChildMenu.setOnTabClickListener(new QMUITabSegment.OnTabClickListener() {
            @Override
            public void onTabClick(int index) {
                switch (index) {
                    case 0:
                        ToastUtils.showShort("跳转我的申办");
                        break;
                    case 1:
                        ToastUtils.showShort("跳转我的预约");
                        break;
                    case 2:
                        ToastUtils.showShort("跳转我的办件");
                        break;
                    case 3:
                        ToastUtils.showShort("跳转我的投诉");
                        break;
                    case 4:
                        ToastUtils.showShort("跳转我的咨询");
                        break;
                }
            }
        });

        addSubscribe(RxView.clicks(stvUserPhoto).throttleFirst(Config.WINDOWDURATION, TimeUnit.SECONDS).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                ToastUtils.showShort("跳转个人信息界面");
            }
        }));
        addSubscribe(RxView.clicks(stvDjrz).throttleFirst(Config.WINDOWDURATION, TimeUnit.SECONDS).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                ToastUtils.showShort("跳转认证界面");
            }
        }));
        addSubscribe(RxView.clicks(llMoreCard).throttleFirst(Config.WINDOWDURATION, TimeUnit.SECONDS).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                ToastUtils.showShort("跳转更多证照界面");
            }
        }));
        addSubscribe(RxView.clicks(llWdsc).throttleFirst(Config.WINDOWDURATION, TimeUnit.SECONDS).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                ToastUtils.showShort("跳转我的收藏界面");
            }
        }));
        addSubscribe(RxView.clicks(llDzgl).throttleFirst(Config.WINDOWDURATION, TimeUnit.SECONDS).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                ToastUtils.showShort("跳转地址管理界面");
            }
        }));
        addSubscribe(RxView.clicks(llWdkd).throttleFirst(Config.WINDOWDURATION, TimeUnit.SECONDS).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                ToastUtils.showShort("跳转我的快递界面");
            }
        }));
        addSubscribe(RxView.clicks(llWdzf).throttleFirst(Config.WINDOWDURATION, TimeUnit.SECONDS).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                ToastUtils.showShort("跳转我的支付界面");
            }
        }));
        addSubscribe(RxView.clicks(llGdsz).throttleFirst(Config.WINDOWDURATION, TimeUnit.SECONDS).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                ARouter.getInstance().build(ArouterPath.APP_SETTING_ACTIVITY).navigation();
            }
        }));
        addSubscribe(RxView.clicks(llTcdl).throttleFirst(Config.WINDOWDURATION, TimeUnit.SECONDS).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                ToastUtils.showShort("退出登录");
                QMUIDialog.MessageDialogBuilder builder = new QMUIDialog.MessageDialogBuilder(mContext);
                builder.setMessage("确定退出当前账号吗？")
                        .addAction("确定", new QMUIDialogAction.ActionListener() {
                            @Override
                            public void onClick(QMUIDialog dialog, int index) {
                                dialog.dismiss();
//                                //清空所有activity
//                                BaseApplication.getIns().finishAllActivity();
                                // TODO: 2019/4/30 清空当前账号信息，跳转登录页面
                                ARouter.getInstance().build(ArouterPath.MODULE_LOGIN_LOGIN_ACTIVITY).navigation();
                            }
                        })
                        .addAction("取消", new QMUIDialogAction.ActionListener() {
                            @Override
                            public void onClick(QMUIDialog dialog, int index) {
                                dialog.cancel();
                            }
                        })
                        .setCancelable(true)
                        .setCanceledOnTouchOutside(false)
                        .show();
            }
        }));
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
                stvDjrz.setTextColor(Color.parseColor("#3f4b59"));
                stvDjrz.setTextSize(getResources().getDimension(R.dimen.text_12));
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
