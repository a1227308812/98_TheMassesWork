package com.westar.library_update.ui;

import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.jakewharton.rxbinding2.view.RxView;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.westar.been.TestBeen1;
import com.westar.been.TestBeen2;
import com.westar.been.TestBeen3;
import com.westar.library_base.base.BaseMvpActivity;
import com.westar.library_base.common.ArouterPath;
import com.westar.library_base.http.been.HttpRequest;
import com.westar.library_base.utils.LLog;
import com.westar.masseswork_98.library_update.R;
import com.westar.library_update.mvp.contract.UpdateContract;
import com.westar.library_update.mvp.presenter.UpdatePresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.functions.Consumer;
/**
 * Created by ZWP on 2019/4/8 11:50.
 * 描述：更新模块
 */
@Route(path = ArouterPath.MODULE_UPDATE_UPDATE_ACTIVITY)
public class UpdateActivity extends BaseMvpActivity<UpdatePresenter> implements UpdateContract.View {

    UpdatePresenter updatePresenter;
    TextView textView;

    @Autowired(name = "ss")
    String ss;
    @Autowired(name = "ll")
    long hhhh;

    @Autowired(name = "aa")
    int aa;
    @Autowired(name = "bb")
    boolean bb;
    @Autowired(name = "cc")
    double cc;
    @Autowired(name = "dd")
    float dd;
    @Autowired(name = "ee")
    ArrayList<String> ee;
    @Autowired(name = "tb1")
    ArrayList<TestBeen1> testBeen1s;
    @Autowired(name = "tb2")
    Map<String, List<TestBeen2>> tb2Map;
    @Autowired
    TestBeen3 tb3;

    @Override
    protected void initView() {
        textView = findViewById(R.id.textView);

        LLog.e("ccc","ss = " + getIntent().getStringExtra("ss")
                + "\nhhhh = " + getIntent().getLongExtra("ll",0)
                + "\naa = " + getIntent().getIntExtra("aa",0)
                + "\nbb = " + getIntent().getBooleanExtra("bb",false)
                + "\ncc = " + getIntent().getDoubleExtra("cc",0)
                + "\ndd = " + getIntent().getFloatExtra("dd",0)
                + "\nee = " + getIntent().getStringArrayListExtra("ee").toString()
                + "\ntestbeen1 = " + getIntent().getSerializableExtra("tb1")
                + "\ntestbeen2 = " + getIntent().getSerializableExtra("tb2")
                + "\ntestbeen3 = " + getIntent().getSerializableExtra("tb3")
        );


        textView.setText("ss = " + ss
                + "\nhhhh = " + hhhh
                + "\naa = " + aa
                + "\nbb = " + bb
                + "\ncc = " + cc
                + "\ndd = " + dd
                + "\nee = " + ee
//                + "\nTestBeen1 = " + testBeen1.toString()
//                + "\ntestbeen2 = " + testBeen1.toString()
        );
    }

    @Override
    protected void initData() {
        addSubscribe(RxView.
                clicks(textView)
                .throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        updatePresenter.update(new HttpRequest<String>().setData("sp"));
                    }
                }));
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_update;
    }

    @Override
    protected UpdatePresenter createPresenter() {
        updatePresenter = new UpdatePresenter();
        return updatePresenter;
    }

    @Override
    public void showLoading() {
        showLoadingDialog();
    }

    @Override
    public void hideLoading() {
        hideLoadingDialog();
    }

    @Override
    public void onOther(Object data) {
        LLog.e("", "onOther");
    }

    @Override
    public void onSuccess(Object data) {
        LLog.e("", "onSuccess");
        textView.setText(data.toString());
    }

    @Override
    public void onError(Throwable e) {
        LLog.e("", "onError");
    }

    @Override
    public LifecycleTransformer bindViewToLifecycle() {
        return this.bindToLifecycle();
    }
}
