package com.westar.masseswork_98.ui.activity;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.jakewharton.rxbinding2.view.RxView;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.westar.Config;
import com.westar.library_base.base.BaseActivity;
import com.westar.library_base.base.BasePresenter;
import com.westar.library_base.base.SingleBaseAdapter;
import com.westar.library_base.common.ArouterPath;
import com.westar.library_base.http.been.HttpRequest;
import com.westar.library_base.view.CustomFlexboxLayoutManager;
import com.westar.masseswork_98.R;
import com.westar.masseswork_98.been.GlobalSearchHot;
import com.westar.been.HisSearch;
import com.westar.masseswork_98.mvp.contract.GlobalSearchContract;
import com.westar.masseswork_98.mvp.presenter.GlobalSearchPresenter;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.functions.Consumer;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by ZWP on 2019/4/26 15:17.
 * 描述：全局搜索页面
 * todo 历史搜索的最大行数没法控制，很烦，空了在去想
 */
@Route(path = ArouterPath.SEARCH_ACTIVITY)
public class GlobalSearchActivity extends BaseActivity implements GlobalSearchContract.View {

    @BindView(R.id.ivBack)
    ImageView ivBack;
    @BindView(R.id.etSearch)
    AppCompatAutoCompleteTextView etSearch;
    @BindView(R.id.ivDelete)
    ImageView ivDelete;
    @BindView(R.id.rvHistoryRecord)
    RecyclerView rvHistoryRecord;
    @BindView(R.id.rvHotItem)
    RecyclerView rvHotItem;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.ll_history)
    LinearLayout llHistory;

    GlobalSearchPresenter presenter;

    GlobalSearchHistoryAdapter historyAdapter;

    GlobalSearchHotAdapter hotAdapter;

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
        if (null != data) {
            List<GlobalSearchHot> searchResults = (List<GlobalSearchHot>) data;
            hotAdapter.setNewData(searchResults);
        }
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public LifecycleTransformer bindViewToLifecycle() {
        return this.bindToLifecycle();
    }

    @Override
    protected BasePresenter createPresenter() {
        return presenter = new GlobalSearchPresenter();
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_global_search;
    }

    @Override
    protected void findId() {

    }

    @Override
    protected void initView() {
        //历史记录
        historyAdapter = new GlobalSearchHistoryAdapter(mContext, null);
        CustomFlexboxLayoutManager manager = new CustomFlexboxLayoutManager(mContext);
        manager.setFlexDirection(FlexDirection.ROW);//主轴为水平方向，起点在左端。
        manager.setFlexWrap(FlexWrap.WRAP);//按正常方向换行
        rvHistoryRecord.setLayoutManager(manager);
        rvHistoryRecord.setAdapter(historyAdapter);

        //热门
        hotAdapter = new GlobalSearchHotAdapter(mContext, null);
        rvHotItem.setLayoutManager(new LinearLayoutManager(mContext));
        rvHotItem.setAdapter(hotAdapter);

        //加载历史记录
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<HisSearch> searchRealmResults = realm.where(HisSearch.class).findAll().sort("searchTime", Sort.DESCENDING);
                if (searchRealmResults != null && searchRealmResults.size() > 0) {
                    llHistory.setVisibility(View.VISIBLE);
                    historyAdapter.setNewData(searchRealmResults);
                } else {
                    llHistory.setVisibility(View.GONE);
                }
            }
        });

        initListener();
    }

    private void initListener() {
        //返回键
        addSubscribe(RxView.clicks(ivBack).throttleFirst(Config.WINDOWDURATION, TimeUnit.SECONDS).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                finish();
            }
        }));

        // 搜索
        addSubscribe(RxView.clicks(ivSearch).throttleFirst(Config.WINDOWDURATION, TimeUnit.SECONDS).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                /**
                 * 1、提交后台，获取搜索结果
                 * 2、数据库保存这一条搜索信息
                 */
                presenter.searchResult(new HttpRequest());

                final String searchObj = etSearch.getText().toString();
                if (!TextUtils.isEmpty(searchObj)) {
                    llHistory.setVisibility(View.VISIBLE);

                    Realm realm = Realm.getDefaultInstance();
                    realm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            HisSearch hasSearchHis = realm.where(HisSearch.class)
                                    .equalTo("searchData", searchObj)
                                    .findFirst();
                            if (null == hasSearchHis) {
                                //创建一个数据库对象
                                HisSearch search = realm.createObject(HisSearch.class, UUID.randomUUID().toString());
                                search.setSearchData(searchObj)
                                        .setSearchTime(System.currentTimeMillis());
                            }

                            //查询所有的历史记录
                            RealmResults<HisSearch> hasSearchList = realm.where(HisSearch.class).findAll();
                            if (null != hasSearchList && hasSearchList.size() > 0) {
                                historyAdapter.setNewData(hasSearchList);
                            }
                        }
                    });
                }
            }
        }));

        //软键盘的搜索/确定按钮监听
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_DONE) {
                    ivSearch.performClick();
                    return true;
                }
                return false;
            }
        });

        //删除全部历史记录
        addSubscribe(RxView.clicks(ivDelete).throttleFirst(Config.WINDOWDURATION, TimeUnit.SECONDS).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                QMUIDialog.MessageDialogBuilder messageDialogBuilder = new QMUIDialog.MessageDialogBuilder(mContext);
                messageDialogBuilder.setMessage("确定要删除所有历史记录吗？")
                        //确认
                        .addAction("确定", new QMUIDialogAction.ActionListener() {
                            @Override
                            public void onClick(QMUIDialog dialog, int index) {
                                Realm realm = Realm.getDefaultInstance();
                                realm.executeTransaction(new Realm.Transaction() {
                                    @Override
                                    public void execute(Realm realm) {
                                        realm.where(HisSearch.class).findAll().deleteAllFromRealm();
                                        llHistory.setVisibility(View.GONE);
                                        historyAdapter.setNewData(null);
                                    }
                                });
                                dialog.dismiss();
                            }
                        })
                        //取消
                        .addAction("取消", new QMUIDialogAction.ActionListener() {
                            @Override
                            public void onClick(QMUIDialog dialog, int index) {
                                dialog.dismiss();
                            }
                        })
                        .setCanceledOnTouchOutside(false)
                        .setCancelable(true)
                        .show();
            }
        }));

        /**
         * 历史记录点击事件
         */
        historyAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                HisSearch hisSearch = (HisSearch) adapter.getData().get(position);
                if (!TextUtils.isEmpty(hisSearch.getSearchData())) {
                    etSearch.setText(hisSearch.getSearchData());
                    ivSearch.performClick();
                }
            }
        });

        /**
         * 历史记录长按事件
         */
        historyAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, final int position) {

                QMUIDialog.MessageDialogBuilder messageDialogBuilder = new QMUIDialog.MessageDialogBuilder(mContext);
                messageDialogBuilder.setMessage("确定要删除该条历史记录吗？")
                        //确认
                        .addAction("确定", new QMUIDialogAction.ActionListener() {
                            @Override
                            public void onClick(QMUIDialog dialog, int index) {
                                Realm realm = Realm.getDefaultInstance();
                                realm.executeTransaction(new Realm.Transaction() {
                                    @Override
                                    public void execute(Realm realm) {
                                        RealmResults<HisSearch> searchRealmResults = realm.where(HisSearch.class).findAll().sort("searchTime", Sort.DESCENDING);
                                        if (searchRealmResults != null && searchRealmResults.size() > 0) {
                                            searchRealmResults.deleteFromRealm(position);
                                        }
                                        if (searchRealmResults != null && searchRealmResults.size() > 0) {
                                            historyAdapter.setNewData(searchRealmResults);
                                        } else {
                                            llHistory.setVisibility(View.GONE);
                                            historyAdapter.setNewData(null);
                                        }
                                    }
                                });
                                dialog.dismiss();
                            }
                        })
                        //取消
                        .addAction("取消", new QMUIDialogAction.ActionListener() {
                            @Override
                            public void onClick(QMUIDialog dialog, int index) {
                                dialog.dismiss();
                            }
                        })
                        .setCanceledOnTouchOutside(false)
                        .setCancelable(true)
                        .show();
                return true;
            }
        });

        /**
         * 热门点击事件
         */
        hotAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                GlobalSearchHot globalSearchHot = (GlobalSearchHot) adapter.getData().get(position);
                if (!TextUtils.isEmpty(globalSearchHot.getTitle())) {
                    ToastUtils.showShort(globalSearchHot.getTitle());
                }
            }
        });
    }

    @Override
    protected void initData() {

    }

    /**
     * 历史记录
     */
    class GlobalSearchHistoryAdapter extends SingleBaseAdapter<HisSearch> {

        /**
         * 带默认空布局显示的
         *
         * @param context
         * @param data
         */
        public GlobalSearchHistoryAdapter(Context context, @Nullable List<HisSearch> data) {
            super(context, data);
            isUseEmpty(false);
        }


        @Override
        public int getLayoutResId() {
            return R.layout.item_global_search_history;
        }

        @Override
        protected void convert(BaseViewHolder helper, HisSearch item) {
            super.convert(helper, item);
            helper.setText(R.id.stv_title, item.getSearchData());
        }
    }

    /**
     * 热门
     */
    class GlobalSearchHotAdapter extends SingleBaseAdapter<GlobalSearchHot> {

        /**
         * 带默认空布局显示的
         *
         * @param context
         * @param data
         */
        public GlobalSearchHotAdapter(Context context, @Nullable List<GlobalSearchHot> data) {
            super(context, data);
        }


        @Override
        public int getLayoutResId() {
            return R.layout.item_global_search_hot;
        }

        @Override
        protected void convert(BaseViewHolder helper, GlobalSearchHot item) {
            super.convert(helper, item);
            helper.setText(R.id.title, item.getTitle());
            helper.setText(R.id.tv_hot_num, "12333");
        }
    }

}
