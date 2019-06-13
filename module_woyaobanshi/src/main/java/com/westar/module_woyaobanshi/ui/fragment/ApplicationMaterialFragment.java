package com.westar.module_woyaobanshi.ui.fragment;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.constraint.Constraints;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jakewharton.rxbinding2.view.RxView;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.westar.Config;
import com.westar.library_base.base.BaseFragment;
import com.westar.library_base.base.BasePresenter;
import com.westar.library_base.base.SingleBaseAdapter;
import com.westar.module_woyaobanshi.R;
import com.westar.module_woyaobanshi.ui.activity.MateriaDetailActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.functions.Consumer;


/**
 * Created by ZWP on 2019/6/4 10:00.
 * 描述：申请材料
 */
public class ApplicationMaterialFragment extends BaseFragment {
    RecyclerView recycMateria;
    TextView tvMateriaMore;
    Constraints constraintsMeteria;
    MatreriaAdapter adapter;
    int subIndex = 4;

    @Override
    protected void lazyLoadShow(boolean isLoad) {

    }

    @Override
    protected void lazyLoadHide(boolean isLoad) {

    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_application_material;
    }

    @Override
    protected void initView() {
        recycMateria = rootView.findViewById(R.id.recyc_materia);
        tvMateriaMore = rootView.findViewById(R.id.tv_materia_more);
        constraintsMeteria = rootView.findViewById(R.id.constraints_meteria);

        recycMateria.setLayoutManager(new LinearLayoutManager(mContext));
        final List<MateriaBeen> materiaBeenList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            MateriaBeen materiaBeen = new MateriaBeen();
            materiaBeen.setTitle("机动车登记、变更、转移、抵押、注销" + i);
            materiaBeen.setDefaultTableUrl("");
            materiaBeen.setHasDefaultTable(true);
            if (i % 3 == 0) {
                materiaBeen.setHasExampleTable(true);
                materiaBeen.setExampleTableUrl("");
            } else {
                materiaBeen.setHasExampleTable(false);
            }
            materiaBeenList.add(materiaBeen);
        }
        final List<MateriaBeen> subList = new ArrayList<>();
        final List<MateriaBeen> baseOtherList = new ArrayList<>();

        if (materiaBeenList.size() > subIndex) {
            subList.addAll(materiaBeenList.subList(0, subIndex));
            baseOtherList.addAll(materiaBeenList.subList(subIndex, materiaBeenList.size()));
            constraintsMeteria.setVisibility(View.VISIBLE);
            addSubscribe(RxView.clicks(tvMateriaMore).throttleFirst(Config.WINDOWDURATION, TimeUnit.SECONDS).subscribe(new Consumer<Object>() {
                @Override
                public void accept(Object o) throws Exception {
                    // TODO: 2019/6/4 点击弹窗列表展示更多的申请材料
                    if (adapter.getData().size() <= 4) {
                        adapter.addData(baseOtherList);
                        tvMateriaMore.setText("点击收起");
                    } else {
                        adapter.getData().removeAll(baseOtherList);
                        adapter.notifyItemRangeRemoved(subIndex, materiaBeenList.size());
                        tvMateriaMore.setText("点击查看更多");
                    }
                }
            }));
        } else {
            constraintsMeteria.setVisibility(View.GONE);
            tvMateriaMore.setOnClickListener(null);
        }

        adapter = new MatreriaAdapter(mContext, subList);
        recycMateria.setAdapter(adapter);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                MateriaBeen materiaBeen = (MateriaBeen) adapter.getData().get(position);
                int i = view.getId();
                if (i == R.id.item_meteria_layout) {
                    // TODO: 2019/6/4 跳转材料详情界面
                    skipActivity(MateriaDetailActivity.class, null);
                    ToastUtils.showShort("" + materiaBeen.getTitle());
                } else if (i == R.id.tv_default_table) {
                    // TODO: 2019/6/4 预览空表
                    ToastUtils.showShort(materiaBeen.getTitle() + "的预览空表");
                }
                if (i == R.id.tv_example_table) {
                    // TODO: 2019/6/4 预览样表
                    ToastUtils.showShort(materiaBeen.getTitle() + "预览样表");
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

    /**
     * 模拟的申请材料对象
     */
    class MateriaBeen {
        String title;
        boolean hasDefaultTable;
        boolean hasExampleTable;
        String defaultTableUrl;
        String exampleTableUrl;

        public String getTitle() {
            return title;
        }

        public MateriaBeen setTitle(String title) {
            this.title = title;
            return this;
        }

        public boolean getHasDefaultTable() {
            return hasDefaultTable;
        }

        public MateriaBeen setHasDefaultTable(boolean hasDefaultTable) {
            this.hasDefaultTable = hasDefaultTable;
            return this;
        }

        public boolean getHasExampleTable() {
            return hasExampleTable;
        }

        public MateriaBeen setHasExampleTable(boolean hasExampleTable) {
            this.hasExampleTable = hasExampleTable;
            return this;
        }

        public String getDefaultTableUrl() {
            return defaultTableUrl;
        }

        public MateriaBeen setDefaultTableUrl(String defaultTableUrl) {
            this.defaultTableUrl = defaultTableUrl;
            return this;
        }

        public String getExampleTableUrl() {
            return exampleTableUrl;
        }

        public MateriaBeen setExampleTableUrl(String exampleTableUrl) {
            this.exampleTableUrl = exampleTableUrl;
            return this;
        }
    }

    /**
     * 适配器
     */
    class MatreriaAdapter extends SingleBaseAdapter<MateriaBeen> {

        /**
         * 带默认空布局显示的
         *
         * @param context
         * @param data
         */
        public MatreriaAdapter(Context context, @Nullable List<MateriaBeen> data) {
            super(context, data);
            isUseEmpty(false);
        }

        @Override
        public int getLayoutResId() {
            return R.layout.item_meteria;
        }

        @Override
        protected void convert(BaseViewHolder helper, MateriaBeen item) {
            super.convert(helper, item);
            helper.setText(R.id.tv_meteria_item_title, item.getTitle());
            if (item.getHasExampleTable()) {
                helper.setBackgroundRes(R.id.tv_example_table, R.drawable.bg_meteria_table_select);
            } else {
                helper.setBackgroundRes(R.id.tv_example_table, R.drawable.bg_meteria_table_normal);
            }
            if (item.getHasDefaultTable()) {
                helper.setBackgroundRes(R.id.tv_default_table, R.drawable.bg_meteria_table_select);
            } else {
                helper.setBackgroundRes(R.id.tv_default_table, R.drawable.bg_meteria_table_normal);
            }
            helper.addOnClickListener(R.id.item_meteria_layout)
                    .addOnClickListener(R.id.tv_default_table)
                    .addOnClickListener(R.id.tv_example_table);
        }
    }

}
