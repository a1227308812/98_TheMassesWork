package com.westar.module_login.ui;

import android.graphics.Rect;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.Window;

import com.blankj.utilcode.util.ScreenUtils;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.westar.library_base.base.BaseActivity;
import com.westar.library_base.base.BasePresenter;
import com.westar.library_base.utils.LLog;
import com.westar.module_login.R;

public class TestActivity extends BaseActivity {
    ConstraintLayout layout;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_test;
    }

    @Override
    protected void findId() {
//        layout.post(new Runnable() {
//            @Override
//            public void run() {
//                View v = TestActivity.this.getWindow().findViewById(Window.ID_ANDROID_CONTENT);
//                Rect rect = new Rect();
//                v.getDrawingRect(rect);
//                LLog.e("====view Drawing Rect==", "height--"+rect.height()+" width--"+rect.width()+" Top--"+rect.top+"  Left--"+rect.left);
//                v.getWindowVisibleDisplayFrame(rect);
//                LLog.e("====view WindowVisible rect==", "height--"+rect.height()+" width--"+rect.width()+" Top--"+rect.top+"  Left--"+rect.left);
//                LLog.e("ccc","post getWidth = " + layout.getWidth() + "       getHeight =" + layout.getHeight());
//                LLog.e("ccc","post getStatusbarHeight = " + QMUIStatusBarHelper.getStatusbarHeight(mContext));
//                LLog.e("ccc","post getScreenHeight = " + ScreenUtils.getScreenHeight() + "       getScreenWidth = " + ScreenUtils.getScreenWidth());
//            }
//        });
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onOther(Object data) {

    }

    @Override
    public void onSuccess(Object data) {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public LifecycleTransformer bindViewToLifecycle() {
        return null;
    }
}
