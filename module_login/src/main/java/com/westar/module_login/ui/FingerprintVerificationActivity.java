package com.westar.module_login.ui;

import android.os.Build;
import android.support.v7.widget.AppCompatButton;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jakewharton.rxbinding2.view.RxView;
import com.westar.library_base.base.BaseActivity;
import com.westar.library_base.common.ArouterPath;
import com.westar.library_fingerprint.BiometricPromptManager;
import com.westar.module_login.R;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.functions.Consumer;

/**
 * Created by ZWP on 2019/3/25 16:50.
 * 描述：指纹验证界面
 */
@Route(path = ArouterPath.MODULE_LOGIN_FINGERPRINT_VERIFICATION_ACTIVITY)
public class FingerprintVerificationActivity extends BaseActivity {

    AppCompatButton appCompatButton;
    TextView showInfo;

    private BiometricPromptManager mManager;

    @Override
    protected void initView() {
        appCompatButton = findViewById(R.id.button);
        showInfo = findViewById(R.id.show_info);

        mManager = BiometricPromptManager.from(this);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SDK version is " + Build.VERSION.SDK_INT);
        stringBuilder.append("\n");
        stringBuilder.append("isHardwareDetected : " + mManager.isHardwareDetected());
        stringBuilder.append("\n");
        stringBuilder.append("hasEnrolledFingerprints : " + mManager.hasEnrolledFingerprints());
        stringBuilder.append("\n");
        stringBuilder.append("isKeyguardSecure : " + mManager.isKeyguardSecure());
        stringBuilder.append("\n");
        showInfo.setText(stringBuilder);

        //点击开启指纹验证
        addSubscribe(RxView.clicks(appCompatButton)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        skipActivity(LoginActivity.class, null);
                        finish();
                        //开启指纹验证
//                        if (mManager.isBiometricPromptEnable()) {
//                            mManager.authenticate(new BiometricPromptManager.OnBiometricIdentifyCallback() {
//                                @Override
//                                public void onUsePassword() {
//                                    ToastUtils.showShort("onUsePassword");
//                                }
//
//                                @Override
//                                public void onSucceeded() {
//                                    ToastUtils.showShort("onSucceeded");
//                                    skipActivity(LoginActivity.class, null);
//                                    finish();
//                                }
//
//                                @Override
//                                public void onFailed() {
//                                    ToastUtils.showShort("onFailed");
//                                }
//
//                                @Override
//                                public void onError(int code, String reason) {
//                                    ToastUtils.showShort("onError");
//                                }
//
//                                @Override
//                                public void onCancel() {
//                                    ToastUtils.showShort("onCancel");
//                                }
//                            });
//                        }
                    }
                })
        );
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_fingerprint_verification;
    }

    @Override
    protected void findId() {

    }

}
