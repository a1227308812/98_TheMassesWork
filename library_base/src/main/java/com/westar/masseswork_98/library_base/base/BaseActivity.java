package com.westar.masseswork_98.library_base.base;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;


import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.PermissionUtils;
import com.qmuiteam.qmui.widget.QMUILoadingView;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
import com.westar.masseswork_98.library_base.R;
import com.westar.masseswork_98.library_base.eventbus.BaseEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;

/**
 * Created by ZWP on 2019/3/8.
 * 描述：activity 超类
 */
public abstract class BaseActivity extends BasePermissionActivity {

    protected Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(getLayoutID());
//        ButterKnife.bind(this);
        //ARouter inject注入
        ARouter.getInstance().inject(this);
        initPresenter();
        initView();
        initData();
    }

    /**
     * 初始化布局
     */
    protected abstract void initView();
    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 检查网络是否可用
     *
     * @return
     */
    protected boolean checkNetWork() {
        ConnectivityManager mConnectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
        if (mNetworkInfo != null) {
            return mNetworkInfo.isAvailable();
        }
        return false;
    }

    QMUILoadingView loadingView;

    /**
     * 显示页面初始化loading弹框
     */
    protected void showLoadingDialog() {
        loadingView = new QMUILoadingView(this);
//        loadingView.setVisibility(View.VISIBLE);
        loadingView.setColor(ContextCompat.getColor(this, R.color.qmui_config_color_red));
        loadingView.start();
    }

    /**
     * 关闭loading加载框
     */
    protected void hideLoadingDialog() {
        loadingView.stop();
//        loadingView.setVisibility(View.GONE);
    }

    ;

    @Override
    public void onStart() {
        super.onStart();
        if (isRegisterEvenetBus()) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (isRegisterEvenetBus()) {
            EventBus.getDefault().unregister(this);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        removePresenter();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventBusCome(BaseEvent baseEvent) {
        if (baseEvent != null) {
            receiveEvent(baseEvent);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onStickyEventBusCome(BaseEvent baseEvent) {
        if (baseEvent != null) {
            receiveStickyEvent(baseEvent);
        }
    }

    /**
     * 接收到分发到事件
     *
     * @param baseEvent 事件
     */
    protected void receiveEvent(BaseEvent baseEvent) {

    }

    /**
     * 接受到分发的粘性事件
     * 粘性事件的发布:
     * EventBus.getDefault().postSticky("xxx");
     *
     * @param baseEvent 粘性事件
     */
    protected void receiveStickyEvent(BaseEvent baseEvent) {

    }

    //布局ID
    protected abstract int getLayoutID();

    //注册EvenetBus
    protected boolean isRegisterEvenetBus() {
        return false;
    }

    protected void initPresenter() {
    }

    protected void removePresenter() {
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
