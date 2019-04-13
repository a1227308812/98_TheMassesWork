package com.westar.module_login.ui;

import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.coorchice.library.SuperTextView;
import com.jakewharton.rxbinding2.view.RxView;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.westar.library_base.base.BaseActivity;
import com.westar.library_base.base.BasePresenter;
import com.westar.library_base.common.ArouterPath;
import com.westar.library_fingerprint.SPUtils;
import com.westar.module_login.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.functions.Consumer;

/**
 * Created by ZWP on 2019/3/25 16:47.
 * 描述：欢迎界面
 */
@Route(path = ArouterPath.MODULE_LOGIN_WELCOME_ACTIVITY)
public class WelcomeActivity extends BaseActivity {

    ViewPager vPWelcome;

    List<View> viewList;
    View welcomeView1;
    View welcomeView2;
    ViewGroup welcomeView3;

    SuperTextView superTextView;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void findId() {
        vPWelcome = findViewById(R.id.vp_welcome);
    }

    @Override
    protected void initView() {
        viewList = new ArrayList<>();
        welcomeView1 = LayoutInflater.from(mContext).inflate(R.layout.item_welcome, null);
        welcomeView1.setBackgroundResource(R.drawable.welcome_1);
        viewList.add(welcomeView1);
        welcomeView2 = LayoutInflater.from(mContext).inflate(R.layout.item_welcome, null);
        welcomeView2.setBackgroundResource(R.drawable.welcome_2);
        viewList.add(welcomeView2);
        welcomeView3 = (ViewGroup) LayoutInflater.from(mContext).inflate(R.layout.item_welcome, null);
        welcomeView3.setBackgroundResource(R.drawable.welcome_3);
        viewList.add(welcomeView3);

        superTextView = (SuperTextView) welcomeView3.getChildAt(0);

        addSubscribe(RxView.clicks(superTextView)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        //todo 判断是否设置了指纹登录，如果设置了指纹登录，那么跳转指纹登录界面，否则跳转登录界面
                        boolean hasFingerprint = SPUtils.getBoolean(mContext, SPUtils.KEY_BIOMETRIC_SWITCH_ENABLE, false);
                        if (hasFingerprint) {
                            skipActivity(FingerprintVerificationActivity.class, null);
                        } else {
                            skipActivity(LoginTypeSelectActivity.class, null);
                        }
                        finish();
                    }
                }));


        vPWelcome.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return viewList.size();
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
                return view == o;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                container.removeView(viewList.get(position));
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                container.addView(viewList.get(position));
                return viewList.get(position);
            }
        });


        vPWelcome.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                superTextView.setVisibility((i == viewList.size() - 1) ? View.VISIBLE : View.GONE);
            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
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

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }
}
