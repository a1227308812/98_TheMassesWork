//package com.westar.masseswork_98.library_base.base;
//
//import android.app.Activity;
//import android.content.Context;
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import com.xiaohou.basetools.eventbus.Event;
//
//import org.greenrobot.eventbus.EventBus;
//import org.greenrobot.eventbus.Subscribe;
//import org.greenrobot.eventbus.ThreadMode;
//
//import butterknife.ButterKnife;
//import butterknife.Unbinder;
//
///**
// * Created by ZWP on 2019/3/1.
// * 描述：application 超类
// */
//public abstract class BaseFragment extends BasePermissionFragment {
//
//    private View mView;
//    private Activity mActivity;
//    public Context mContext;
//    private Unbinder mUnBinder;
//    private LayoutInflater mInflater;
//    protected boolean isInit = false;
//    protected boolean isLoad = false;
//    public boolean isVisible;
//    public boolean isPrepared;
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        mView = inflater.inflate(getLayoutID(), null);
//        isInit = true;
//        return mView;
//    }
//
//    /**
//     * Fragment的UI是否是可见
//     * <p>
//     * 在onCreateView方法之前调用
//     *
//     * @param isVisibleToUser
//     */
//    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        super.setUserVisibleHint(isVisibleToUser);
//        isVisible = isVisibleToUser;
//        baseLazyLoad();
//    }
//    /**
//     * 懒加载条件判断
//     */
//    private void baseLazyLoad() {
//        if (isPrepared) {
//            if (isVisible) {
//                isLoad = true;
//                lazyLoadShow(isLoad);
//            } else {
//                isLoad = false;
//                lazyLoadHide(isLoad);
//            }
//
//        }
//    }
//
//    /**
//     * 出现懒加载
//     * @param isLoad
//     */
//    protected abstract void lazyLoadShow(boolean isLoad);
//
//    /**
//     * 隐藏懒加载
//     * @param isLoad
//     */
//    protected abstract void lazyLoadHide(boolean isLoad);
//
//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//    }
//
//
//
//    protected abstract int getLayoutID();//布局ID
//
//    @Override
//    public void onAttach(Context context) {
//        mActivity = (Activity) context;
//        mContext = context;
//        super.onAttach(context);
//    }
//
//    @Override
//    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        mUnBinder = ButterKnife.bind(this, view);
//        mInflater = onGetLayoutInflater(savedInstanceState);
//        initPresenter();
//        initData();
//        /**初始化的时候去加载数据**/
//        isPrepared = true;
//        baseLazyLoad();
//
//    }
//
//    protected abstract void initData();
//
//    /**
//     * 是否注册事件分发
//     *
//     * @return true绑定EventBus事件分发，默认不绑定，子类需要绑定的话复写此方法返回true.
//     */
//    protected boolean isRegisterEventBus() {
//        return false;
//    }
//    @Override
//    public void onStart() {
//        super.onStart();
//        if (isRegisterEventBus()) {
//            EventBus.getDefault().register(this);
//        }
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        if (isRegisterEventBus()) {
//            EventBus.getDefault().unregister(this);
//        }
//    }
//
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void onEventBusCome(Event event) {
//        if (event != null) {
//            receiveEvent(event);
//        }
//    }
//
//    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
//    public void onStickyEventBusCome(Event event) {
//        if (event != null) {
//            receiveStickyEvent(event);
//        }
//    }
//
//    /**
//     * 接收到分发到事件
//     *
//     * @param event 事件
//     */
//    protected void receiveEvent(Event event) {
//
//    }
//
//    /**
//     * 接受到分发的粘性事件
//     *
//     * @param event 粘性事件
//     */
//    protected void receiveStickyEvent(Event event) {
//
//    }
//
//    @Override
//    public void onDestroyView() {
//        isPrepared = false;
//        removePresenter();
//        mUnBinder.unbind();
//        super.onDestroyView();
//    }
//    protected void initPresenter() {
//
//    }
//
//    protected void removePresenter() {
//
//    }
//
//}
