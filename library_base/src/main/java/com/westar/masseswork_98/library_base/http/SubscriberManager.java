package com.westar.masseswork_98.library_base.http;

import android.content.Context;

import com.blankj.utilcode.util.ToastUtils;
import com.westar.masseswork_98.library_base.http.been.HttpResult;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/**
 * Created by ZWP on 2019/3/11.
 * 描述：Flowable Subscriber 封装  有背压的回调
 *
 * @param <T>
 */
public abstract class SubscriberManager<T> implements Subscriber<HttpResult<T>> {
    //状态码
    //2:登录失效
    //-1:请求失败
    //0:请求成功
    private static final int SUCCEED_CODE = 0;

    private Context mContext;

    public SubscriberManager(Context mContext) {
        this.mContext = mContext;
    }


    @Override
    public void onSubscribe(Subscription s) {
        // 对比Observer传入的Disposable参数，Subscriber此处传入的参数 = Subscription
        // 相同点：Subscription参数具备Disposable参数的作用，即Disposable.dispose()切断连接, 同样的调用Subscription.cancel()切断连接
        // 不同点：Subscription增加了void request(long n)
        s.request(Long.MAX_VALUE);
        // 作用：决定观察者能够接收多少个事件
        // 如设置了s.request(3)，这就说明观察者能够接收3个事件（多出的事件存放在缓存区）
        // 官方默认推荐使用Long.MAX_VALUE，即s.request(Long.MAX_VALUE);

        //添加订阅到MAP中
    }

    @Override
    public void onNext(HttpResult<T> httpResult) {

        if (null != httpResult) {
            switch (httpResult.getCode()) {
                case SUCCEED_CODE:
                    onSuccess(httpResult.getData());
                    break;
                default:
                    onOther(httpResult);
                    break;
            }
        }
    }

    @Override
    public void onError(Throwable t) {
        RetrofitException.ResponeThrowable responeThrowable = RetrofitException.retrofitException(t);
        switch (responeThrowable.code) {
            case RetrofitException.ERROR.UNKNOWN:
            case RetrofitException.ERROR.PARSE_ERROR:
            case RetrofitException.ERROR.NETWORD_ERROR:
            case RetrofitException.ERROR.HTTP_ERROR:
            case RetrofitException.ERROR.SSL_ERROR:
                onFailure(t);
                ToastUtils.showShort(responeThrowable.message);
                break;
            default:
                onFailure(t);
                ToastUtils.showShort(responeThrowable.message);
                break;
        }
    }

    @Override
    public void onComplete() {
        onFinish();
    }

    /**
     * 请求成功其他返回值
     *
     * @param httpResult
     */
    protected abstract void onOther(HttpResult<T> httpResult);

    /**
     * 回调正确状态的data数据
     *
     * @param data
     */
    protected abstract void onSuccess(T data);

    /**
     * 回调错误方法
     *
     * @param e
     */
    protected abstract void onFailure(Throwable e);

    /**
     * 请求流结束
     */
    protected abstract void onFinish();
}
