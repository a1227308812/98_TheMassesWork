package com.westar.masseswork_98;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.ToastUtils;
import com.jakewharton.rxbinding2.view.RxView;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.westar.Config;
import com.westar.been.TestBeen2;
import com.westar.been.TestBeen3;
import com.westar.library_base.base.BaseActivity;
import com.westar.library_base.callback.IPermissionsCallBack;
import com.westar.library_base.common.ArouterPath;
import com.westar.library_base.preview.helper.FilePreviewHelper;
import com.westar.library_base.preview.helper.ImagePreviewHelper;
import com.westar.masseswork_98.test.PictureSelectActivity;
import com.westar.masseswork_98.test.TestViewActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.functions.Consumer;

/**
 * Created by ZWP on 2019/4/8 16:42.
 * 描述：主页
 */
@Route(path = ArouterPath.APP_MAIN_ACTIVITY)
public class MainActivity extends BaseActivity {

    TextView textView;
    Button button;
    Button btnTestPreview;

    int REQUEST_CODE_NORMAL = 10000;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_main;
    }


    @Override
    protected void initView() {

        button = findViewById(R.id.button);
        textView = findViewById(R.id.textView);
        btnTestPreview = findViewById(R.id.btnTestPreview);
        addSubscribe(RxView.clicks(button)
                .throttleFirst(Config.windowDuration, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        //调整到更新activity
//                        // 1. 应用内简单的跳转(通过URL跳转在'进阶用法'中)
//                        ARouter.getInstance().build("/module_update/UpdateActivity").navigation();
                        ArrayList<String> strings = new ArrayList<>();
                        for (int i = 0; i < 10; i++) {
                            strings.add(String.valueOf(i));
                        }

                        TestBeen2 testBeen2 = new TestBeen2("Jack", "22", "男");
                        ArrayList<TestBeen2> beenList2 = new ArrayList<>();
                        Map<String, List<TestBeen2>> map = new HashMap<>();
                        beenList2.add(testBeen2);
                        map.put("beenList2", beenList2);

                        TestBeen3 testBeen3 = new TestBeen3("Jack", "22", "男");

                        // 2. 跳转并携带参数
                        ARouter.getInstance().build(ArouterPath.MODULE_UPDATE_UPDATE_ACTIVITY)
                                .withLong("ll", 666L)
                                .withString("ss", "888")
                                .withInt("aa", 777)
                                .withBoolean("bb", true)
                                .withDouble("cc", 100)
                                .withFloat("dd", 200f)
                                .withStringArrayList("ee", strings)
                                .withSerializable("tb3", testBeen3)
                                .withObject("tb1", beenList2)
                                .withObject("tb2", map)
                                .navigation();
                    }
                }));
        final String testUrl = "http://125.71.211.128:82/downLoad/down/e6a246dfd7c14b4bbe4abd16eacd5e3d/测试问题反馈单-宣化政务OAAPP端会议模块（复测2）20190322-王建东.docx";
        btnTestPreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FilePreviewHelper helper = new FilePreviewHelper.Builder(MainActivity.this, testUrl, "docx")
                        .fileName("测试问题反馈单-宣化政务OAAPP端会议模块（复测2）20190322-王建东.docx")
                        .uuid("e6a246dfd7c14b4bbe4abd16eacd5e3d")
                        .build();
                helper.jump2PreviewActivity();
            }
        });
        findViewById(R.id.btnTestImagePreview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String imgUrl = "http://125.71.211.128:82/downLoad/down/892b8ce8dde7424ea993b11e05429c9b/新增的接口.png";
                ImagePreviewHelper helper = new ImagePreviewHelper.Builder(MainActivity.this, imgUrl)
                        .defaultPos(0)
                        .build();
                helper.jump2PreviewView();
            }
        });

        findViewById(R.id.btn_zxing_normal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                skipActivityForResult(CaptureActivity.class, REQUEST_CODE_NORMAL, null);
            }
        });
        findViewById(R.id.btn_select_picture).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                skipActivity(PictureSelectActivity.class, null);
            }
        });
        findViewById(R.id.btn_testview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                skipActivity(TestViewActivity.class, null);
            }
        });


    }


    @Override
    protected void initData() {
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
}
