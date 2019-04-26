package com.westar.module_woyaochaxun;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.ToastUtils;
import com.qmuiteam.qmui.alpha.QMUIAlphaImageButton;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.uuzuche.lib_zxing.activity.CaptureFragment;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.westar.library_base.base.BaseActivity;
import com.westar.library_base.base.BasePresenter;
import com.westar.library_base.base.ToolbarActivity;
import com.westar.module_woyaochaxun.beanactivity.NoTitleActivity;
import com.westar.module_woyaochaxun.util.ImageUtil;

    public class SaoYiSaoActivity extends NoTitleActivity {

    private CaptureFragment captureFragment;
    private boolean isOpen = false;
    public static final int REQUEST_CODE = 111;
    public static final int REQUEST_IMAGE = 112;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_sao_yi_sao;
    }

    @Override
    protected void findId() {
    }

    @Override
    protected void initView() {
        //isTopBarBackButton(true); //是否有toolbar的返回键和设置右上方相册监听
        initCamera(); //初始化相机及手电筒监听
        findViewById(R.id.imb_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.tv_photo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_IMAGE);
            }
        });
    }

    @Override
    protected void initData() {

    }

    //是否有toolbar的返回键和设置右上方相册监听
//    private void isTopBarBackButton(boolean isBack) {
//        if (isBack) {
//            QMUIAlphaImageButton toolbar = topbar.addLeftImageButton(R.drawable.icon_top_fh_bs, getLayoutID());
//            toolbar.setChangeAlphaWhenPress(false);
//            toolbar.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    finish();
//                }
//            });
//        }
//        Button rightText = topbar.addRightTextButton("相册", getLayoutID());
//        rightText.setTextColor(0xffffffff);
//        rightText.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//                intent.addCategory(Intent.CATEGORY_OPENABLE);
//                intent.setType("image/*");
//                startActivityForResult(intent, REQUEST_IMAGE);
//            }
//        });
//        //标题颜色
//        TextView title = topbar.setTitle("扫一扫");
//        title.setTextColor(0xffffffff);
//        topbar.setBackgroundAlpha(0);
//    }

    //初始化相机及手电筒监听
    private void initCamera() {
        captureFragment = new CaptureFragment();
        CodeUtils.setFragmentArgs(captureFragment, R.layout.layout_camera);
//        captureFragment.setAnalyzeCallback(analyzeCallback);
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_my_container, captureFragment).commit();
        findViewById(R.id.tv_light).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isOpen) {
                    CodeUtils.isLightEnable(true);
                    isOpen = true;
                } else {
                    CodeUtils.isLightEnable(false);
                    isOpen = false;
                }
            }
        });
    }

//    /**
//     * 二维码解析回调函数
//     */
//    CodeUtils.AnalyzeCallback analyzeCallback = new CodeUtils.AnalyzeCallback() {
//        @Override
//        public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
//            Intent resultIntent = new Intent();
//            Bundle bundle = new Bundle();
//            bundle.putInt(CodeUtils.RESULT_TYPE, CodeUtils.RESULT_SUCCESS);
//            bundle.putString(CodeUtils.RESULT_STRING, result);
//            resultIntent.putExtras(bundle);
//            SaoYiSaoActivity.this.setResult(RESULT_OK, resultIntent);
//            SaoYiSaoActivity.this.finish();
//        }
//
//        @Override
//        public void onAnalyzeFailed() {
//            Intent resultIntent = new Intent();
//            Bundle bundle = new Bundle();
//            bundle.putInt(CodeUtils.RESULT_TYPE, CodeUtils.RESULT_FAILED);
//            bundle.putString(CodeUtils.RESULT_STRING, "");
//            resultIntent.putExtras(bundle);
//            SaoYiSaoActivity.this.setResult(RESULT_OK, resultIntent);
//            SaoYiSaoActivity.this.finish();
//        }
//    };

    //扫描或选择文件处理
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        /**
         * 处理二维码扫描结果
         */
        if (requestCode == REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Toast.makeText(this, "解析结果:" + result, Toast.LENGTH_LONG).show();
                    SaoYiSaoActivity.this.finish();
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(SaoYiSaoActivity.this, "解析二维码失败", Toast.LENGTH_LONG).show();
                    SaoYiSaoActivity.this.finish();
                }
            }
        }
        /**
         * 选择系统图片并解析
         */
        else if (requestCode == REQUEST_IMAGE) {
            if (data != null) {
                Uri uri = data.getData();
                try {
                    CodeUtils.analyzeBitmap(ImageUtil.getImageAbsolutePath(this, uri), new CodeUtils.AnalyzeCallback() {
                        @Override
                        public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                            Toast.makeText(SaoYiSaoActivity.this, "解析结果:" + result, Toast.LENGTH_LONG).show();
                            SaoYiSaoActivity.this.finish();
                        }
                        @Override
                        public void onAnalyzeFailed() {
                            Toast.makeText(SaoYiSaoActivity.this, "解析二维码失败", Toast.LENGTH_LONG).show();
                            SaoYiSaoActivity.this.finish();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
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
