package com.westar.module_woyaobanshi.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.gesture.GestureStroke;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.net.Uri;
import android.support.design.chip.Chip;
import android.support.design.chip.ChipGroup;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.NestedScrollView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.jakewharton.rxbinding2.view.RxView;
import com.qmuiteam.qmui.widget.QMUIFloatLayout;
import com.qmuiteam.qmui.widget.popup.QMUIPopup;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.uuzuche.lib_zxing.DisplayUtil;
import com.westar.Config;
import com.westar.library_base.base.BasePresenter;
import com.westar.library_base.base.ToolbarActivity;
import com.westar.library_base.callback.IPermissionsCallBack;
import com.westar.library_base.http.been.HttpRequest;
import com.westar.library_base.view.BasePopup;
import com.westar.library_base.view.CustomHorizonTabLayout;
import com.westar.library_base.view.HorizonScrollSelfTabSegment;
import com.westar.library_base.view.ListPopup;
import com.westar.module_woyaobanshi.R;
import com.westar.module_woyaobanshi.mvp.contract.ItemDetailContract;
import com.westar.module_woyaobanshi.mvp.presenter.ItemDetailPresenter;
import com.westar.module_woyaobanshi.ui.fragment.ApplicationMaterialFragment;
import com.westar.module_woyaobanshi.ui.fragment.ChargeStandardFragment;
import com.westar.module_woyaobanshi.ui.fragment.ItemBaseInfoFragment;
import com.westar.module_woyaobanshi.ui.fragment.ResultsSampleFragment;
import com.westar.module_woyaobanshi.ui.fragment.SettingBasisFragment;
import com.westar.module_woyaobanshi.ui.fragment.WorkFlowChartFragment;

import java.util.concurrent.TimeUnit;

import io.reactivex.functions.Consumer;

/**
 * Created by ZWP on 2019/5/30 14:34.
 * 描述：办事指南详情页面
 */
public class ItemDetailActivity extends ToolbarActivity implements ItemDetailContract.View {
    FrameLayout flJbxx;
    FrameLayout flBslct;
    FrameLayout flSqcl;
    FrameLayout flSfbz;
    FrameLayout flJgyb;
    FrameLayout flSdyj;
    HorizonScrollSelfTabSegment childMenu;
    HorizonScrollSelfTabSegment childMenuStick;
    NestedScrollView nsvItemDetail;
    LinearLayout llQuickButtom;
    LinearLayout llPhoneNum;
    TextView tvPhoneNum;
    TextView tvZxsb;

    QMUIFloatLayout hsv_type;

    ItemDetailPresenter presenter;
    String[] childMenuTitles = {"基本信息", "办事流程图", "申请材料", "收费标准", "结果样本", "设定依据"};

    String[] rightTopMenu = {"预约", "咨询", "收藏", "评价"};

    @Override
    public String setBarTitle() {
        return "办事指南";
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_item_detail;
    }

    @Override
    protected void findId() {
        flJbxx = findViewById(R.id.fl_jbxx);
        flBslct = findViewById(R.id.fl_bslct);
        flSqcl = findViewById(R.id.fl_sqcl);
        flSfbz = findViewById(R.id.fl_sfbz);
        flJgyb = findViewById(R.id.fl_jgyb);
        flSdyj = findViewById(R.id.fl_sdyj);
        childMenu = findViewById(R.id.hsv_item_menu);
        childMenuStick = findViewById(R.id.hsv_item_menu_stick);
        nsvItemDetail = findViewById(R.id.nsv_item_detail);

        llQuickButtom = findViewById(R.id.ll_quick_buttom);
        llPhoneNum = findViewById(R.id.ll_phone_num);
        tvPhoneNum = findViewById(R.id.tv_phone_num);
        tvZxsb = findViewById(R.id.tv_zxsb);

        hsv_type = findViewById(R.id.hsv_type);

    }

    @Override
    protected void initView() {
        // TODO: 2019/6/13 动态添加标签
        hsv_type.setChildHorizontalSpacing(DisplayUtil.dip2px(mContext, 10));
        hsv_type.setChildVerticalSpacing(DisplayUtil.dip2px(mContext, 10));
        for (int i = 0; i < 3; i++) {
            TextView chip = new TextView(mContext);
            chip.setText("测试" + i);
            chip.setTextSize(12);
            chip.setTextColor(Color.parseColor("#4a6dd5"));
            chip.setPadding(DisplayUtil.dip2px(mContext, 8), DisplayUtil.dip2px(mContext, 4),
                    DisplayUtil.dip2px(mContext, 8), DisplayUtil.dip2px(mContext, 4));
            chip.setBackground(getResources().getDrawable(R.drawable.bg_radius_stock_blue));
            hsv_type.addView(chip);
        }
        //添加右上角发起按钮
        final ImageView rightView = new ImageView(mContext);
        rightView.setImageResource(R.drawable.icon_other_tj);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(DisplayUtil.dip2px(mContext, 24), DisplayUtil.dip2px(mContext, 24));
        layoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
        layoutParams.setMarginEnd(DisplayUtil.dip2px(mContext, 10));
        topBarLayout.addRightView(rightView, R.id.top_right_menu, layoutParams);

        addSubscribe(RxView.clicks(rightView).throttleFirst(Config.WINDOWDURATION, TimeUnit.SECONDS).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                // TODO: 2019/6/6 弹出发起操作框
                final ListPopup qmuiPopup = new ListPopup(mContext, QMUIPopup.DIRECTION_BOTTOM);
                qmuiPopup.setmAdapter(new RightTopMenuAdapter());
                qmuiPopup.create(DisplayUtil.dip2px(mContext, 120), DisplayUtil.dip2px(mContext, 160), new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        ToastUtils.showShort(rightTopMenu[position]);
                        qmuiPopup.dismiss();
                        switch (position) {
                            case 0:
                                // TODO: 2019/6/13 跳转预约 
                                ToastUtils.showShort("跳转预约");
                                break;
                            case 1:
                                // TODO: 2019/6/13 跳转咨询
                                ToastUtils.showShort("跳转咨询");
                                break;
                            case 2:
                                // TODO: 2019/6/13 添加收藏
                                ToastUtils.showShort("添加收藏");
                                break;
                            case 3:
                                View dialogView = LayoutInflater.from(mContext).inflate(R.layout.popup_evaluation, null);
                                EditText editContent = dialogView.findViewById(R.id.et_content);
                                TextView tv_cancel = dialogView.findViewById(R.id.tv_cancel);
                                TextView tv_confirm = dialogView.findViewById(R.id.tv_confirm);
                                final PopupWindow popupWindow = new BasePopup(mContext)
                                        .setRootView(dialogView)
                                        .creatPop()
                                        .showCenter(decorvView);
                                tv_cancel.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        popupWindow.dismiss();
                                    }
                                });
                                tv_confirm.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        // TODO: 2019/6/13 获取评价信息上传评价数据
                                        ToastUtils.showShort("获取评价信息上传评价数据");
                                        popupWindow.dismiss();
                                    }
                                });

                                popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                                    @Override
                                    public void onDismiss() {
                                        WindowManager.LayoutParams lp = getWindow().getAttributes();
                                        lp.alpha = 1.0f;
                                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                                        getWindow().setAttributes(lp);
                                    }
                                });

                                WindowManager.LayoutParams lp = getWindow().getAttributes();
                                lp.alpha = 0.3f;
                                getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                                getWindow().setAttributes(lp);
                                break;
                        }
                    }
                });
                qmuiPopup.show(rightView);
            }
        }));
        addSubscribe(RxView.clicks(llPhoneNum).throttleFirst(Config.WINDOWDURATION, TimeUnit.SECONDS).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                requestPermissions(new String[]{Manifest.permission.CALL_PHONE},
                        true,
                        true,
                        new IPermissionsCallBack() {
                            @Override
                            public void permissionErro(String name) {
                                ToastUtils.showShort("获取权限失败！");
                            }

                            @Override
                            public void permissionSuccess(String name) {
                                if (!TextUtils.isEmpty(tvPhoneNum.getText().toString())) {
                                    Intent intent = new Intent();
//                                    intent.setAction(Intent.ACTION_CALL);//直接打电话
                                    intent.setAction(Intent.ACTION_DIAL);//渠道拨号界面，电话由用户发起拨打
                                    intent.setData(Uri.parse("tel:" + tvPhoneNum.getText().toString()));
                                    startActivity(intent);
                                }
                            }
                        });

            }
        }));
        addSubscribe(RxView.clicks(tvZxsb).throttleFirst(Config.WINDOWDURATION, TimeUnit.SECONDS).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                ToastUtils.showShort("在线申报");
            }
        }));

        initTabViewAndStickView(childMenu);
        initStickTabViewAndStickView(childMenuStick);
        //滑动到顶部
        nsvItemDetail.fullScroll(ScrollView.FOCUS_UP);
        nsvItemDetail.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView nestedScrollView, int x, int y, int oldx, int oldy) {
                //判断滑动到指定位置时的tab的显示与隐藏
                if (y > childMenu.getY() + 1) {
                    childMenuStick.setVisibility(View.VISIBLE);
                } else {
                    childMenuStick.setVisibility(View.GONE);
                }
                //滑动判断是否显示底部快捷咨询电话和在线申报
                if (y < oldy) {
                    llQuickButtom.setVisibility(View.VISIBLE);
                } else {
                    llQuickButtom.setVisibility(View.GONE);
                }

                Log.e("ddd", "flJbxx.getY() - childMenuStick.getMeasuredHeight() = " + (flJbxx.getY() - childMenuStick.getMeasuredHeight()));
                Log.e("ddd", "y = " + y);
                Log.e("ddd", "flJbxx.getY() = " + flJbxx.getY());
                Log.e("ddd", "childMenuStick.getMeasuredHeight() = " + childMenuStick.getMeasuredHeight());
//                //滑动联动tab todo 脑壳痛 没想好怎么来搞
//                if (y >= flJbxx.getY() - childMenuStick.getMeasuredHeight() && y < flBslct.getY()) {
//                    childMenu.getAdapter().getViews().get(0).updateDecoration(childMenu.getAdapter().getItem(0), true);
//                    childMenu.layoutIndicator(childMenu.getAdapter().getItem(0),true);
//                } else if (y >= flBslct.getY() - childMenuStick.getMeasuredHeight() && y < flSqcl.getY()) {
//                    childMenu.getAdapter().getViews().get(1).updateDecoration(childMenu.getAdapter().getItem(1), true);
//                    childMenu.layoutIndicator(childMenu.getAdapter().getItem(1),true);
//                } else if (y >= flSqcl.getY() - childMenuStick.getMeasuredHeight() && y < flSfbz.getY()) {
//                    childMenu.getAdapter().getViews().get(2).updateDecoration(childMenu.getAdapter().getItem(2), true);
//                    childMenu.layoutIndicator(childMenu.getAdapter().getItem(2),true);
//                } else if (y >= flSfbz.getY() - childMenuStick.getMeasuredHeight() && y < flJgyb.getY()) {
//                    childMenu.getAdapter().getViews().get(3).updateDecoration(childMenu.getAdapter().getItem(3), true);
//                    childMenu.layoutIndicator(childMenu.getAdapter().getItem(3),true);
//                } else if (y >= flJgyb.getY() - childMenuStick.getMeasuredHeight() && y < flSdyj.getY()) {
//                    childMenu.getAdapter().getViews().get(4).updateDecoration(childMenu.getAdapter().getItem(4), true);
//                    childMenu.layoutIndicator(childMenu.getAdapter().getItem(4),true);
//                } else if (y >= flSdyj.getY() - childMenuStick.getMeasuredHeight() && y < nsvItemDetail.getMeasuredHeight()) {
//                    childMenu.getAdapter().getViews().get(5).updateDecoration(childMenu.getAdapter().getItem(5), true);
//                    childMenu.layoutIndicator(childMenu.getAdapter().getItem(5),true);
//                }
            }
        });

        //动态添加fragment
        FragmentManager manager = getSupportFragmentManager();

        addFragment(manager, flJbxx.getId(), new ItemBaseInfoFragment());
        addFragment(manager, flBslct.getId(), new WorkFlowChartFragment());
        addFragment(manager, flSqcl.getId(), new ApplicationMaterialFragment());
        addFragment(manager, flSfbz.getId(), new ChargeStandardFragment());
        addFragment(manager, flJgyb.getId(), new ResultsSampleFragment());
        addFragment(manager, flSdyj.getId(), new SettingBasisFragment());
    }

    /**
     * 初始化tab和隐藏的stickTab
     *
     * @param menu
     */
    private void initTabViewAndStickView(final HorizonScrollSelfTabSegment menu) {
        //动态加载子menu
        for (String menuTitle : childMenuTitles) {
            HorizonScrollSelfTabSegment.Tab tab = new HorizonScrollSelfTabSegment.Tab(menuTitle);
            menu.addTab(tab).setTabTextSize(DisplayUtil.dip2px(mContext, 16));
        }
        menu.setIndicatorDrawable(new ColorDrawable(Color.parseColor("#4a6dd5")));
        menu.setDefaultNormalColor(Color.parseColor("#828899"));
        menu.setDefaultSelectedColor(Color.parseColor("#303545"));
        menu.notifyDataChanged();//刷新

        menu.addOnTabSelectedListener(new CustomHorizonTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(final int index) {
                if (childMenuStick.getSelectedIndex() != index) {
                    childMenuStick.selectTab(index);
                }
                menu.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        switch (index) {
                            case 0:
                                nsvItemDetail.scrollTo((int) nsvItemDetail.getY(), (int) flJbxx.getY() - childMenuStick.getMeasuredHeight());
                                break;
                            case 1:
                                nsvItemDetail.scrollTo((int) nsvItemDetail.getY(), (int) flBslct.getY() - childMenuStick.getMeasuredHeight());
                                break;
                            case 2:
                                nsvItemDetail.scrollTo((int) nsvItemDetail.getY(), (int) flSqcl.getY() - childMenuStick.getMeasuredHeight());
                                break;
                            case 3:
                                nsvItemDetail.scrollTo((int) nsvItemDetail.getY(), (int) flSfbz.getY() - childMenuStick.getMeasuredHeight());
                                break;
                            case 4:
                                nsvItemDetail.scrollTo((int) nsvItemDetail.getY(), (int) flJgyb.getY() - childMenuStick.getMeasuredHeight());
                                break;
                            case 5:
                                nsvItemDetail.scrollTo((int) nsvItemDetail.getY(), (int) flSdyj.getY() - childMenuStick.getMeasuredHeight());
                                break;
                        }
                    }
                }, 100);

            }

            @Override
            public void onTabUnselected(int index) {

            }

            @Override
            public void onTabReselected(int index) {

            }

            @Override
            public void onDoubleTap(int index) {

            }
        });
    }

    /**
     * 初始化tab和隐藏的stickTab
     *
     * @param menu
     */
    private void initStickTabViewAndStickView(final HorizonScrollSelfTabSegment menu) {
        //动态加载子menu
        for (String menuTitle : childMenuTitles) {
            HorizonScrollSelfTabSegment.Tab tab = new HorizonScrollSelfTabSegment.Tab(menuTitle);
            menu.addTab(tab).setTabTextSize(DisplayUtil.dip2px(mContext, 16));
        }
        menu.setIndicatorDrawable(new ColorDrawable(Color.parseColor("#4a6dd5")));
        menu.setDefaultNormalColor(Color.parseColor("#828899"));
        menu.setDefaultSelectedColor(Color.parseColor("#303545"));
        menu.notifyDataChanged();//刷新

        menu.addOnTabSelectedListener(new CustomHorizonTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int index) {
                childMenu.selectTab(index);
            }

            @Override
            public void onTabUnselected(int index) {

            }

            @Override
            public void onTabReselected(int index) {

            }

            @Override
            public void onDoubleTap(int index) {

            }
        });
    }

    private void addFragment(FragmentManager manager, int layoutId, Fragment fragment) {
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(layoutId, fragment);
        transaction.show(fragment);
        transaction.commit();
    }

    @Override
    protected void initData() {
        presenter.getData(new HttpRequest());
    }

    @Override
    protected BasePresenter createPresenter() {
        return presenter = new ItemDetailPresenter();
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

    public class RightTopMenuAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return rightTopMenu.length;
        }

        @Override
        public Object getItem(int position) {
            return rightTopMenu[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ItemViewHolder viewHolder;
            if (null == convertView) {
                viewHolder = new ItemViewHolder();
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_right_top_list, parent, false);
                viewHolder.titleView = convertView.findViewById(R.id.tv_title);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ItemViewHolder) convertView.getTag();
            }

            viewHolder.titleView.setText(rightTopMenu[position]);
            return convertView;
        }

        class ItemViewHolder {
            TextView titleView;
        }
    }

}
