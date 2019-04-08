package com.westar.masseswork_98.test;

import android.support.design.circularreveal.cardview.CircularRevealCardView;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;

import com.westar.library_base.base.BaseActivity;
import com.westar.masseswork_98.R;

import butterknife.BindView;

/**
 * Created by ZWP on 2019/4/1 13:57.
 * 描述：测试自定义view的类
 */
public class TestViewActivity extends BaseActivity {

    @BindView(R.id.iv_test1)
    AppCompatImageView ivTest1;
//    @BindView(R.id.iv_test2)
//    ShadowImageView ivTest2;
    @BindView(R.id.cardview)
    CircularRevealCardView cardview;
    @BindView(R.id.button2)
    AppCompatButton button2;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_test_view;
    }

    @Override
    protected void initView() {

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ViewAnimationUtils.createCircularReveal()
            }
        });
    }

    @Override
    protected void initData() {

    }
}
