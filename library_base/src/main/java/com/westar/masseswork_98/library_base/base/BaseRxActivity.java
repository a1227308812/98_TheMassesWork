package com.westar.masseswork_98.library_base.base;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by ZWP on 2019/3/8.
 * 描述：rx管理类
 */
public class BaseRxActivity extends RxAppCompatActivity {
    /**
     * 创建一个CompositeDisposable对象(替代RxJava1中CompositeSubscription)，
     * 在实例化RxJava2中的Disposable时可调用其add(Disposable d)将Disposable加入其中。
     * 等到Activity销毁或者不需要时便可调用其clear()方法对其中的Disposable进行统一取消订阅并销毁的操作
     */
    CompositeDisposable mCompositeDisposable = null;

    /**
     * 添加RxJava订阅
     *
     * @param subscription
     */
    public void addSubscribe(Disposable subscription) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(subscription);
    }



    /**
     * 取消RxJava订阅
     */
    public void unSubscribe() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.dispose();
            mCompositeDisposable.clear();
            mCompositeDisposable = new CompositeDisposable();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        /**
         * addSubscribe(Disposable disposable)与clearDisposable()可放入BaseActivity或者MVP架构中的BasePresenter中，便于统一管理，使代码更加简洁。
         */
        unSubscribe();
    }
}
