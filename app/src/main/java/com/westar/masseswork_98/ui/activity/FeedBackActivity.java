package com.westar.masseswork_98.ui.activity;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.coorchice.library.SuperTextView;
import com.jakewharton.rxbinding2.view.RxView;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.westar.Config;
import com.westar.library_base.base.BasePresenter;
import com.westar.library_base.base.ToolbarActivity;
import com.westar.library_base.http.ObserverManager;
import com.westar.library_base.http.been.HttpResult;
import com.westar.library_base.rxjava.RxScheduler;
import com.westar.masseswork_98.AppClient;
import com.westar.masseswork_98.R;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.functions.Consumer;

/**
 * Created by ZWP on 2019/5/8 13:36.
 * 描述：反馈建议界面
 */
public class FeedBackActivity extends ToolbarActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.tv_limit_num)
    TextView tvLimitNum;
    @BindView(R.id.stv_confirm)
    SuperTextView stvConfirm;

    int maxLength = 1000;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_feed_back;
    }

    @Override
    protected void findId() {

    }

    @Override
    protected void initView() {
        etContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int afterNum = etContent.getText().length();
                tvLimitNum.setText(String.valueOf(afterNum) + " / " + String.valueOf(maxLength));
            }
        });

        addSubscribe(RxView.clicks(stvConfirm).throttleFirst(Config.WINDOWDURATION, TimeUnit.SECONDS).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                if (TextUtils.isEmpty(etContent.getText().toString())) {
                    ToastUtils.showShort("内容不能为空！");
                    return;
                }
                showLoading();
                AppClient.getInstance()
                        .creatAPI()
                        .feedBack(etContent.getText().toString())
                        .compose(bindViewToLifecycle())
                        .compose(RxScheduler.rxObservableSchedulerHelper())
                        .subscribe(new ObserverManager<String>(getBaseContext()) {
                            @Override
                            protected void onOther(HttpResult<String> httpResult) {

                            }

                            @Override
                            protected void onSuccess(String data) {
                                ToastUtils.showShort(data);
                                finish();
                            }

                            @Override
                            protected void onFailure(Throwable e) {

                            }

                            @Override
                            protected void onFinish() {
                                hideLoading();
                            }
                        });
            }
        }));
    }

    @Override
    protected void initData() {

    }

    @Override
    public String setBarTitle() {
        return "反馈建议";
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

}
