package com.westar.module_woyaochaxun;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.ToastUtils;
import com.qmuiteam.qmui.alpha.QMUIAlphaImageButton;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CaptureFragment;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.westar.library_base.base.BasePresenter;
import com.westar.library_base.base.ToolbarActivity;
import com.westar.library_base.callback.IPermissionsCallBack;
import com.westar.library_base.common.ArouterPath;

import butterknife.BindView;

/**
 * Created by ZWP on 2019/4/8 13:10.
 * 描述：办件查询界面
 */
@Route(path = ArouterPath.MODULE_WOYAOCHAXUN_BAN_JIAN_CHA_XUN_ACTIVITY)
public class BanJianChaXunActivity extends ToolbarActivity {


    TextInputEditText etSearch;
    TextView btnSearch;
    RecyclerView recyDocuments;

    private final static int REQUEST_CODE_NORMAL = 10000;



    @Override
    protected int getLayoutID() {
        return R.layout.activity_ban_jian_cha_xun;
    }

    @Override
    protected void findId() {
        etSearch = findViewById(R.id.et_search);
        btnSearch = findViewById(R.id.btn_search);
        recyDocuments = findViewById(R.id.recy_documents);
    }

    @Override
    protected void initView() {
        isTopBarBackButton(true); //是否有toolbar的返回键和设置右上方扫描监听
    }

    @Override
    protected void initData() {
        //权限申请
        requestPermissions(new String[]{Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_PHONE_STATE},
                true,
                true,
                new IPermissionsCallBack() {
                    @Override
                    public void permissionErro(String name) {
                        ToastUtils.showShort("获取权限失败！");
                    }

                    @Override
                    public void permissionSuccess(String name) {
                        ToastUtils.showShort("获取权限成功！");
                    }
                });

    }

    //是否有toolbar的返回键和设置右上方扫描监听
    private void isTopBarBackButton(boolean isBack) {
        if (isBack) {
            QMUIAlphaImageButton toolbar = topBarLayout.addLeftImageButton(R.drawable.icon_other_fh, getLayoutID());
            toolbar.setChangeAlphaWhenPress(false);
            toolbar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
        final QMUIAlphaImageButton rightTopbar = topBarLayout.addRightImageButton(R.drawable.icon_top_sys, getLayoutID());
        rightTopbar.setChangeAlphaWhenPress(false);
        rightTopbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                skipActivityForResult(SaoYiSaoActivity.class, REQUEST_CODE_NORMAL, null);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_NORMAL) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    ToastUtils.showShort("解析结果:" + result);
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    ToastUtils.showShort("解析二维码失败");
                }
            }
        }
    }

    @Override
    public String setBarTitle() {
        return "办件查询";
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
