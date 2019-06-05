package com.westar.module_woyaobanshi.ui.fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.westar.library_base.base.BaseFragment;
import com.westar.library_base.base.BasePresenter;
import com.westar.library_base.base.SingleBaseAdapter;
import com.westar.library_base.callback.IPermissionsCallBack;
import com.westar.module_woyaobanshi.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by ZWP on 2019/5/30 14:36.
 * 描述：
 */
public class ItemBaseInfoFragment extends BaseFragment {
    final int DEFULT_SPANSIZE = 2;
    RecyclerView recycList;
    BaseInfoAdapter adapter;

    @Override
    protected void lazyLoadShow(boolean isLoad) {

    }

    @Override
    protected void lazyLoadHide(boolean isLoad) {

    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_item_base_info;
    }

    @Override
    protected void initView() {
        recycList = rootView.findViewById(R.id.recyc_list);

        final List<BaseInfoModle> modleList = new ArrayList<>();
        modleList.add(new BaseInfoModle("办件类型：", "承诺件"));
        modleList.add(new BaseInfoModle("是否收费：", "是"));
        modleList.add(new BaseInfoModle("服务对象：", "自然人"));
        modleList.add(new BaseInfoModle("实施机构：", "交通局"));
        modleList.add(new BaseInfoModle("监督电话：", "028-6669999").setAction(1));
        modleList.add(new BaseInfoModle("咨询电话：", "028-6668888").setAction(1));
        modleList.add(new BaseInfoModle("法定时限：", "20工作日"));
        modleList.add(new BaseInfoModle("承诺时限：", "10工作日"));
        modleList.add(new BaseInfoModle("办理时间：", "上午08:30-12:00，下午14:00-17:00（周一至周五）").setSpanSize(DEFULT_SPANSIZE));


        GridLayoutManager manager = new GridLayoutManager(mContext, DEFULT_SPANSIZE);
        recycList.setLayoutManager(manager);

        adapter = new BaseInfoAdapter(mContext, modleList);
        recycList.setAdapter(adapter);

        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int i) {
                return modleList.get(i).getSpanSize();
            }
        });


        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (null != adapter.getData().get(position)) {
                    BaseInfoModle infoModle = (BaseInfoModle) adapter.getData().get(position);
                    final String tel = infoModle.getContent();
                    if (1 == infoModle.getAction()) {
                        // TODO: 2019/5/31 拨打电话
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
                                        if (!TextUtils.isEmpty(tel)) {
                                            Intent intent = new Intent();
//                                    intent.setAction(Intent.ACTION_CALL);//直接打电话
                                            intent.setAction(Intent.ACTION_DIAL);//渠道拨号界面，电话由用户发起拨打
                                            intent.setData(Uri.parse("tel:" + tel));
                                            startActivity(intent);
                                        }
                                    }
                                });


                    }
                }
            }
        });
    }

    @Override
    protected void initData() {

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
        return getContext();
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

    public class BaseInfoModle {
        int spanSize = 1;//默认只占一列
        int action;
        String tip = "";
        String content = "";
        int tipColor = -1;
        int tipSize = -1;
        int contentColor = -1;
        int contentSize = -1;

        public BaseInfoModle(String tip, String content) {
            this.tip = tip;
            this.content = content;
        }

        public int getSpanSize() {
            return spanSize;
        }

        public BaseInfoModle setSpanSize(int spanSize) {
            this.spanSize = spanSize;
            return this;
        }

        public int getAction() {
            return action;
        }

        public BaseInfoModle setAction(int action) {
            this.action = action;
            return this;
        }

        public String getTip() {
            return tip;
        }

        public BaseInfoModle setTip(String tip) {
            this.tip = tip;
            return this;
        }

        public String getContent() {
            return content;
        }

        public BaseInfoModle setContent(String content) {
            this.content = content;
            return this;
        }

        public int getTipColor() {
            return tipColor;
        }

        public BaseInfoModle setTipColor(int tipColor) {
            this.tipColor = tipColor;
            return this;
        }

        public int getTipSize() {
            return tipSize;
        }

        public BaseInfoModle setTipSize(int tipSize) {
            this.tipSize = tipSize;
            return this;
        }

        public int getContentColor() {
            return contentColor;
        }

        public BaseInfoModle setContentColor(int contentColor) {
            this.contentColor = contentColor;
            return this;
        }

        public int getContentSize() {
            return contentSize;
        }

        public BaseInfoModle setContentSize(int contentSize) {
            this.contentSize = contentSize;
            return this;
        }
    }

    /**
     * 适配器
     */
    class BaseInfoAdapter extends SingleBaseAdapter<BaseInfoModle> {

        /**
         * 带默认空布局显示的
         *
         * @param context
         * @param data
         */
        public BaseInfoAdapter(Context context, @Nullable List<BaseInfoModle> data) {
            super(context, data);
            isUseEmpty(false);
        }

        @Override
        public int getLayoutResId() {
            return R.layout.child_base_info;
        }

        @Override
        protected void convert(BaseViewHolder helper, BaseInfoModle item) {
            super.convert(helper, item);
            helper.setText(R.id.tv_tip, item.getTip());
            helper.setText(R.id.tv_content, item.getContent());
            if (-1 != item.getContentColor()) {
                helper.setTextColor(R.id.tv_content, item.getContentColor());
            }
        }
    }
}
