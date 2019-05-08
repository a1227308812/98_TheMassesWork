package com.westar.library_base.http;


import android.content.Context;

import com.blankj.utilcode.util.ToastUtils;
import com.westar.library_base.http.been.HttpResult;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by ZWP on 2019/3/11.
 * 描述：Observer 封装  主要用于网络数据的请求  观察者
 *
 * @param <T>
 */
public abstract class ObserverManager<T> implements Observer<HttpResult<T>> {
    //状态码
    //0:请求成功
    //-1:请求失败
    //2:登录失效
    public static final int SUCCEED_CODE = 0;

    private Context mContent;

    public ObserverManager(Context mContext) {
        this.mContent = mContext;
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(HttpResult<T> httpResult) {
        onFinish();
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
        onFinish();
        //网络请求自定义异常类
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

    /**
     * 请求流结束
     */
    @Override
    public void onComplete() {
        onFinish();
    }

    /**
     * 请求成功其他返回值  一般用于登录失败时 用于区别登录不成功的原因
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
     * 本次请求结束
     */
    protected abstract void onFinish();
}
