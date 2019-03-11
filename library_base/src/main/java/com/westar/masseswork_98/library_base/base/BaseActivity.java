package com.westar.masseswork_98.library_base.base;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;


import com.westar.masseswork_98.library_base.R;
import com.westar.masseswork_98.library_base.eventbus.Event;

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
        setContentView(getLayoutID());
        ButterKnife.bind(this);
        mContext = this;
        initPresenter();
        initData();
    }


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
    public void onEventBusCome(Event event) {
        if (event != null) {
            receiveEvent(event);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onStickyEventBusCome(Event event) {
        if (event != null) {
            receiveStickyEvent(event);
        }
    }

    /**
     * 接收到分发到事件
     *
     * @param event 事件
     */
    protected void receiveEvent(Event event) {

    }

    /**
     * 接受到分发的粘性事件
     * 粘性事件的发布:
     * EventBus.getDefault().postSticky("xxx");
     *
     * @param event 粘性事件
     */
    protected void receiveStickyEvent(Event event) {

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
