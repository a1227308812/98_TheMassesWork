package com.westar.library_base.base;

import com.trello.rxlifecycle2.components.support.RxFragment;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by ZWP on 2019/3/1.
 * 描述：
 */
public class BaseRxFragment  extends RxFragment {
    private CompositeDisposable mCompositeDisposable = null;

    /**
     * RxJava 解除所有订阅者
     */
    public void unSubscribe() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.dispose();
            mCompositeDisposable.clear();
            mCompositeDisposable = new CompositeDisposable();
        }
    }

    /**
     * RxJava 添加订阅者
     */
    public void addSubscribe(Disposable subscription) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(subscription);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unSubscribe();
    }
}
