package com.westar.library_base.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by ZWP on 2019/3/1.
 * 描述：mvpActivity的超类 用于统一注册和反注册presenter
 */
public abstract class BaseMvpActivity<T extends BasePresenter> extends BasePermissionActivity implements BaseView {

    protected T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initPresenter();
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        removePresenter();
    }

    /**
     * 初始化presenter
     */
    protected void initPresenter() {
        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    /**
     * 解绑presenter
     */
    protected void removePresenter() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }
    /**
     * 创建Presenter
     *
     * @return
     */
    protected abstract T createPresenter();

}
