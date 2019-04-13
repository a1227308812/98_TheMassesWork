package com.westar.masseswork_98.ui.custom;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.westar.masseswork_98.R;
import com.westar.masseswork_98.adapter.AddressAdapter;
import com.westar.masseswork_98.been.AddressNode;
import com.westar.masseswork_98.interfaces.IChoiceAddressClick;

import java.util.List;


/**
 * Created by luoyz on 2019/4/12 11:49
 * Version : 1.0
 * Last update by luoyz on 2019/4/12 11:49
 * Describe : 地址选择控件
 */

public class AddressLayout extends LinearLayout {
    private RecyclerView rvAddress;
    private TextView tvLabel;//标签名
    private View vLine;
    private AddressAdapter mAdapter;
    private Context mContext;
    private IChoiceAddressClick mClick;
    private boolean mExpand = true;//是否默认展开

    public AddressLayout(Context context) {
        super(context);
        init(context);
    }

    public AddressLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public AddressLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    /**
     * 初始化
     *
     * @param context
     */
    private void init(Context context) {
        mContext = context;
        View view = LayoutInflater.from(context).inflate(R.layout.address_layout, null);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(params);
        rvAddress = view.findViewById(R.id.rvAddress);
        tvLabel = view.findViewById(R.id.tvLabelName);
        vLine = view.findViewById(R.id.vLine);
        this.addView(view);

        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(context);
        layoutManager.setFlexDirection(FlexDirection.ROW);
        layoutManager.setJustifyContent(JustifyContent.FLEX_START);
        rvAddress.setLayoutManager(layoutManager);
        mAdapter = new AddressAdapter(mContext, null);
        rvAddress.setAdapter(mAdapter);
        setClickListener();
    }

    /**
     * 设置监听
     */
    private void setClickListener() {
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (mClick != null) {
                    mClick.itemClick(position);
                }
            }
        });
    }

    /**
     * 是否展开
     *
     * @param b
     */
    public void expand(boolean b) {
        if (b) {
            vLine.setVisibility(VISIBLE);
            rvAddress.setVisibility(VISIBLE);
            animatorExpand(rvAddress);
        } else {
            vLine.setVisibility(GONE);
            rvAddress.setVisibility(GONE);
            animatorClose(rvAddress);
        }
    }

    /**
     * 加载视图
     *
     * @param list
     * @param click
     */
    public void loadView(@NonNull String labelName, @NonNull List<AddressNode> list, @NonNull IChoiceAddressClick click) {
        mClick = click;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getParentId() == -1) {
                list.get(i).setExpand(true);
            }
        }
        tvLabel.setText(labelName);
        mAdapter.setNewData(list);
        if (mExpand) {
            expand(true);
        }
    }

    /**
     * 刷新
     *
     * @param list
     */
    public void notifyDataSetChanged(@NonNull List<AddressNode> list) {
        mAdapter.setNewData(list);
    }

    /**
     * 刷新
     *
     * @param lableName
     * @param list
     */
    public void notifyDataSetChanged(String lableName, @NonNull List<AddressNode> list) {
        tvLabel.setText(lableName);
        mAdapter.setNewData(list);
    }

    /**
     * 展开淡入淡出动画
     *
     * @param target
     */
    private void animatorExpand(View target) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(target, "alpha", 0f, 1f);
        animator.setDuration(1000L);
        animator.start();
    }

    /**
     * 关闭淡入淡出动画
     *
     * @param target
     */
    private void animatorClose(View target) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(target, "alpha", 1f, 0f);
        animator.setDuration(1000L);
        animator.start();
    }

    public void setExpand(boolean mExpand) {
        this.mExpand = mExpand;
    }
}
