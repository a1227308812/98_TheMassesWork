package com.westar.library_base.base;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;


import com.alibaba.android.arouter.launcher.ARouter;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.widget.QMUILoadingView;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;
import com.westar.library_base.eventbus.BaseEvent;
import com.westar.masseswork_98.library_base.R;

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
        QMUIStatusBarHelper.translucent(this);

        ButterKnife.bind(this);
        //ARouter inject注入
        ARouter.getInstance().inject(this);
        initPresenter();
        findId();
        initView();
        initData();
    }

    /**
     * 全局动态设置根布局的背景色
     *
     * @param layoutResID
     */
    @Override
    public void setContentView(int layoutResID) {
        View rootView = LayoutInflater.from(this).inflate(layoutResID, null);
        rootView.setBackgroundColor(ContextCompat.getColor(this, R.color.root_bg));
        super.setContentView(rootView);
    }

    /**
     * 全局动态设置根布局的背景色
     *
     * @param contentView
     */
    @Override
    public void setContentView(View contentView) {
        contentView.setBackgroundColor(ContextCompat.getColor(this, R.color.root_bg));
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

    //注册EvenetBus
    protected boolean isRegisterEvenetBus() {
        return false;
    }

    /**
     * 初始化presenter
     */
    protected void initPresenter() {
    }

    /**
     * 移除presenter
     */
    protected void removePresenter() {
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

    public int getIntentInt(@NonNull String key) {
        return getIntent().getIntExtra(key, 0);
    }

    public String getIntentString(@NonNull String key) {
        return getIntent().getStringExtra(key) == null ? "" : getIntent().getStringExtra(key);
    }

    public boolean getIntentBoolean(@NonNull String key) {
        return getIntent().getBooleanExtra(key, false);
    }

    public <T> T getIntentSerializable(@NonNull String key) {
        return getIntent().getSerializableExtra(key) == null ? null : (T) getIntent().getSerializableExtra(key);
    }
}
