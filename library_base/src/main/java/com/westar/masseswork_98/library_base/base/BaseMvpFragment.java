//package com.westar.masseswork_98.library_base.base;
//
///**
// * Created by ZWP on 2019/3/1.
// * 描述：
// */
//public abstract class BaseMvpFragment<T extends BasePresenter> extends BaseFragment implements BaseView {
//
//    protected T mPresenter;
//
//    @Override
//    protected void initPresenter() {
//        mPresenter = createPresenter();
//        if (mPresenter != null) {
//            mPresenter.attachView(this);
//        }
//    }
//
//    @Override
//    protected void removePresenter() {
//        if (mPresenter != null) {
//            mPresenter.detachView();
//        }
//    }
//
//    protected abstract T createPresenter();
//}
