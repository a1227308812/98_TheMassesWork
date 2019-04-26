package com.westar.masseswork_98.ui.activity;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseViewHolder;
import com.coorchice.library.SuperTextView;
import com.google.android.flexbox.FlexboxLayout;
import com.jakewharton.rxbinding2.view.RxView;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.uuzuche.lib_zxing.DisplayUtil;
import com.westar.Config;
import com.westar.library_base.base.BaseActivity;
import com.westar.library_base.base.BasePresenter;
import com.westar.library_base.base.SingleBaseAdapter;
import com.westar.library_base.common.ArouterPath;
import com.westar.library_base.http.been.HttpRequest;
import com.westar.masseswork_98.R;
import com.westar.masseswork_98.been.GlobalSearchHistory;
import com.westar.masseswork_98.been.HisSearch;
import com.westar.masseswork_98.been.SearchResult;
import com.westar.masseswork_98.mvp.contract.GlobalSearchContract;
import com.westar.masseswork_98.mvp.presenter.GlobalSearchPresenter;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.functions.Consumer;
import io.realm.Realm;

/**
 * Created by ZWP on 2019/4/26 15:17.
 * 描述：全局搜索页面
 */
@Route(path = ArouterPath.APP_SEARCH_ACTIVITY)
public class GlobalSearchActivity extends BaseActivity implements GlobalSearchContract.View {

    @BindView(R.id.ivBack)
    ImageView ivBack;
    @BindView(R.id.etSearch)
    EditText etSearch;
    @BindView(R.id.ivDelete)
    ImageView ivDelete;
    @BindView(R.id.rvHistoryRecord)
    FlexboxLayout rvHistoryRecord;
    @BindView(R.id.rvHotItem)
    RecyclerView rvHotItem;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.ll_history)
    LinearLayout llHistory;

    GlobalSearchPresenter presenter;

    GlobalSearchHistoryAdapter adapter;

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
            List<GlobalSearchHistory> searchResults = (List<GlobalSearchHistory>) data;
            adapter.setNewData(searchResults);
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
        adapter = new GlobalSearchHistoryAdapter(mContext, null);
        rvHotItem.setLayoutManager(new LinearLayoutManager(mContext));
        rvHotItem.setAdapter(adapter);

        addSubscribe(RxView.clicks(ivBack).throttleFirst(Config.WINDOWDURATION, TimeUnit.SECONDS).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                finish();
            }
        }));
        addSubscribe(RxView.clicks(ivSearch).throttleFirst(Config.WINDOWDURATION, TimeUnit.SECONDS).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                // TODO: 2019/4/26 搜索
                /**
                 * 1、提交后台，获取搜索结果
                 * 2、数据库保存这一条搜索信息
                 */
                presenter.searchResult(new HttpRequest());

                String searchObj = etSearch.getText().toString();
                if (!TextUtils.isEmpty(searchObj)) {
                    llHistory.setVisibility(View.VISIBLE);

                    Realm realm = Realm.getDefaultInstance();
                    realm.beginTransaction();//开启事务

                    HisSearch hasSearch = realm.where(HisSearch.class)
                            .equalTo("searchData", searchObj)
                            .findFirst();
                    if (null == hasSearch) {
                        //创建一个数据库对象
                        HisSearch search = realm.createObject(HisSearch.class, UUID.randomUUID().toString());
                        search.setSearchData(searchObj)
                                .setSearchTime(System.currentTimeMillis());

                        FlexboxLayout.LayoutParams layoutParams = new FlexboxLayout.LayoutParams(FlexboxLayout.LayoutParams.WRAP_CONTENT, FlexboxLayout.LayoutParams.WRAP_CONTENT);
                        layoutParams.setMargins(DisplayUtil.dip2px(mContext, 5)
                                , DisplayUtil.dip2px(mContext, 5)
                                , DisplayUtil.dip2px(mContext, 5)
                                , DisplayUtil.dip2px(mContext, 5));

                        SuperTextView textView = new SuperTextView(mContext);
                        textView.setText(searchObj);
                        textView.setGravity(Gravity.CENTER);
                        textView.setTextColor(Color.parseColor("#828899"));
                        textView.setTextSize(14);
                        textView.setSolid(Color.parseColor("#f5f6f8"));
                        textView.setCorner(1000);

                        textView.setPadding(DisplayUtil.dip2px(mContext, 20)
                                , DisplayUtil.dip2px(mContext, 9)
                                , DisplayUtil.dip2px(mContext, 20)
                                , DisplayUtil.dip2px(mContext, 9));
                        rvHistoryRecord.addView(textView, layoutParams);
                    }
                    //提交事务
                    realm.commitTransaction();
                }
            }
        }));
        //软键盘的确定按钮监听
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
    }

    @Override
    protected void initData() {

    }

    class GlobalSearchHistoryAdapter extends SingleBaseAdapter<GlobalSearchHistory> {

        /**
         * 带默认空布局显示的
         *
         * @param context
         * @param data
         */
        public GlobalSearchHistoryAdapter(Context context, @Nullable List<GlobalSearchHistory> data) {
            super(context, data);
        }


        @Override
        public int getLayoutResId() {
            return R.layout.item_global_search_hot;
        }

        @Override
        protected void convert(BaseViewHolder helper, GlobalSearchHistory item) {
            super.convert(helper, item);
            helper.setText(R.id.title, item.getTitle());
            helper.setText(R.id.tv_hot_num, item.getHotNum());
        }
    }

}
