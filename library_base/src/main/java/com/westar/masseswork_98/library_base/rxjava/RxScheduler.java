package com.westar.masseswork_98.library_base.rxjava;

import org.reactivestreams.Publisher;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ZWP on 2019/3/1.
 * 描述：统一线程管理
 */
public class RxScheduler {
    /**
     * Flowable 统一线程处理
     * 只有在需要处理背压问题时，才需要使用Flowable。
     * 背压：指的是在上下游运行在不同的线程中，且上游发射数据的速度大于下游接收处理数据的速度时，才会产生背压问题；
     * 所以，如果能够确定：
     * 1、上下游运行在同一个线程中，
     * 2、上下游工作在不同的线程中，但是下游处理数据的速度不慢于上游发射数据的速度，
     * 3、上下游工作在不同的线程中，但是数据流中只有一条数据
     * 则不会产生背压问题，就没有必要使用Flowable，以免影响性能。
     *
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer<T, T> rxFlowableSchedulerHelper() {
        //compose简化线程
        return new FlowableTransformer<T, T>() {
            @Override
            public Publisher<T> apply(Flowable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * Observable 统一线程处理
     *
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<T, T> rxObservableSchedulerHelper() {
        //compose简化线程
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

}
