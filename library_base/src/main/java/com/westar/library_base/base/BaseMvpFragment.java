package com.westar.library_base.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * Created by ZWP on 2019/3/1.
 * 描述：mvpFragment的超类 用于统一注册和反注册presenter
 */
public abstract class BaseMvpFragment<T extends BasePresenter> extends BasePermissionFragment implements BaseView {

    protected T mPresenter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initPresenter();
    }

    protected void initPresenter() {
        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    protected void removePresenter() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    protected abstract T createPresenter();

    @Override
    public void onDestroyView() {
        removePresenter();
        super.onDestroyView();
    }
}
