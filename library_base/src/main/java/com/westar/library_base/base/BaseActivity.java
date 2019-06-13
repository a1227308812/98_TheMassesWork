package com.westar.library_base.base;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;


import com.alibaba.android.arouter.launcher.ARouter;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.widget.QMUILoadingView;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;
import com.westar.library_base.eventbus.BaseEvent;
import com.westar.library_base.eventbus.EventBusUtlis;
import com.westar.library_base.utils.LLog;
import com.westar.masseswork_98.library_base.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;

/**
 * Created by ZWP on 2019/3/8.
 * 描述：activity 超类
 */
public abstract class BaseActivity extends BaseMvpActivity {

    protected Context mContext;
    protected View decorvView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //添加Activity到堆栈
        BaseApplication.getIns().addActivity(this);
        //设置显示布局的默认底色
        decorvView = getWindow().getDecorView();
        decorvView.setBackgroundColor(ContextCompat.getColor(this, R.color.root_bg));

        mContext = this;
        setContentView(getLayoutID());
        QMUIStatusBarHelper.translucent(this);

        ButterKnife.bind(this);
        //ARouter inject注入
        ARouter.getInstance().inject(this);

//        DisplayMetrics metrics = QMUIDisplayHelper.getDisplayMetrics(mContext);
//        LLog.e("ccc", "--------density-------------  " + metrics.density);
//        LLog.e("ccc", "--------scaledDensity-------------  " + metrics.scaledDensity);


        initStatusBar();
        initPresenter();
        findId();
        initView();
        initData();
    }

    protected void initStatusBar() {
        //状态栏透明 字体和图标黑色
        QMUIStatusBarHelper.setStatusBarLightMode(this);
    }

    /**
     * 全局动态设置根布局的背景色
     *
     * @param layoutResID
     */
    @Override
    public void setContentView(int layoutResID) {
        ViewGroup rootView = (ViewGroup) LayoutInflater.from(this).inflate(layoutResID, null);
        super.setContentView(rootView);
    }

    /**
     * 全局动态设置根布局的背景色
     *
     * @param contentView
     */
    @Override
    public void setContentView(View contentView) {
        super.setContentView(contentView);
    }

    //布局ID
    protected abstract int getLayoutID();

    /**
     * 初始化控件id
     */
    protected abstract void findId();

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

    /**
     * 显示页面初始化loading弹框
     */
    protected void showLoadingDialog() {
    }

    /**
     * 关闭loading加载框
     */
    protected void hideLoadingDialog() {
    }


    @Override
    public void onStart() {
        super.onStart();
        if (!isRegisterEvenetBus()) {
            EventBusUtlis.register(this);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (!isRegisterEvenetBus()) {
            EventBusUtlis.unregister(this);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        removePresenter();
    }

    /**
     * evenbus普通事件接收
     *
     * @param baseEvent
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventBusCome(BaseEvent baseEvent) {
        if (baseEvent != null) {
            receiveEvent(baseEvent);
        }
    }

    /**
     * evenbus粘性事件接收
     *
     * @param baseEvent
     */
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

    /**
     * 是否注册EvenetBus
     */
    protected boolean isRegisterEvenetBus() {
        return EventBusUtlis.isRegistered(this);
    }


    /**
     * 复写返回键
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    /**
     * 打开新界面
     *
     * @param openClass 新开页面
     * @param bundle    参数
     */
    public void skipActivity(Class<?> openClass, @Nullable Bundle bundle) {
        Intent intent = new Intent(this, openClass);
        if (null != bundle)
            intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * 打开新界面，等待返回
     *
     * @param openClass   新界面
     * @param requestCode 请求码
     * @param bundle      参数
     */
    public void skipActivityForResult(Class<?> openClass, int requestCode, @Nullable Bundle bundle) {
        Intent intent = new Intent(this, openClass);
        if (null != bundle)
            intent.putExtras(bundle);
        startActivityForResult(intent, requestCode);
    }

    /**
     * 返回到上个页面
     *
     * @param bundle 参数
     */
    public void setResultOk(@Nullable Bundle bundle) {
        Intent intent = new Intent();
        if (bundle != null) ;
        intent.putExtras(bundle);
        setResult(RESULT_OK, intent);
        finish();
    }

    /**
     * 快速获取intent中的int参数
     *
     * @param key
     * @return
     */
    public int getIntentInt(@NonNull String key) {
        return getIntent().getIntExtra(key, 0);
    }

    /**
     * 快速获取intent中的String参数
     *
     * @param key
     * @return
     */
    public String getIntentString(@NonNull String key) {
        return getIntent().getStringExtra(key) == null ? "" : getIntent().getStringExtra(key);
    }

    /**
     * 快速获取intent中的boolean参数
     *
     * @param key
     * @return
     */
    public boolean getIntentBoolean(@NonNull String key) {
        return getIntent().getBooleanExtra(key, false);
    }

    /**
     * 快速获取intent中的Serializable参数
     *
     * @param key
     * @param <T>
     * @return
     */
    public <T> T getIntentSerializable(@NonNull String key) {
        return getIntent().getSerializableExtra(key) == null ? null : (T) getIntent().getSerializableExtra(key);
    }
}
