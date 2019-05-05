package com.westar.masseswork_98.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TextInputEditText;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bigkoo.pickerview.utils.PickerViewAnimateUtil;
import com.blankj.utilcode.util.ToastUtils;
import com.coorchice.library.SuperTextView;
import com.jakewharton.rxbinding2.view.RxView;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.westar.Config;
import com.westar.library_base.base.BasePresenter;
import com.westar.library_base.base.ToolbarActivity;
import com.westar.library_base.common.ArouterPath;
import com.westar.masseswork_98.R;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.functions.Consumer;

/**
 * Created by ZWP on 2019/5/5 11:27.
 * 描述：个人信息页面
 */
@Route(path = ArouterPath.APP_PERSONAL_INFORMATION_ACTIVITY)
public class PersonalInformationActivity extends ToolbarActivity {

    @BindView(R.id.iv_change_photo)
    SuperTextView ivChangePhoto;
    @BindView(R.id.et_nickname)
    TextInputEditText etNickname;
    @BindView(R.id.cl_nickname)
    ConstraintLayout clNickname;
    @BindView(R.id.et_location)
    TextInputEditText etLocation;
    @BindView(R.id.cl_location)
    ConstraintLayout clLocation;
    @BindView(R.id.et_gender)
    TextInputEditText etGender;
    @BindView(R.id.cl_gender)
    ConstraintLayout clGender;
    @BindView(R.id.et_birthday)
    TextInputEditText etBirthday;
    @BindView(R.id.cl_birthday)
    ConstraintLayout clBirthday;

    @Override
    public String setBarTitle() {
        return "个人信息";
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_personal_information;
    }

    @Override
    protected void findId() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        //加载头像
        ivChangePhoto.setUrlImage("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=328179059,3377101288&fm=27&gp=0.jpg");
        addSubscribe(RxView.clicks(ivChangePhoto).throttleFirst(Config.WINDOWDURATION, TimeUnit.SECONDS).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                // 进入相册 以下是例子：用不到的api可以不写
                PictureSelector.create(PersonalInformationActivity.this)
                        .openGallery(PictureMimeType.ofImage())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                        .maxSelectNum(1)// 最大图片选择数量 int
                        .imageSpanCount(4)// 每行显示个数 int
                        .selectionMode(PictureConfig.SINGLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                        .previewImage(true)// 是否可预览图片 true or false
                        .isCamera(true)// 是否显示拍照按钮 true or false
                        .imageFormat(PictureMimeType.JPEG)// 拍照保存图片格式后缀,默认jpeg
                        .enableCrop(true)// 是否裁剪 true or false
                        .freeStyleCropEnabled(true)// 裁剪框是否可拖拽 true or false
                        .circleDimmedLayer(true)// 是否圆形裁剪 true or false
                        .showCropFrame(false)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false   true or false
                        .showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false    true or false
                        .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                        .sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                        .previewEggs(true)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中) true or false
                        .minimumCompressSize(100)// 小于100kb的图片不压缩
                        .synOrAsy(true)//同步true或异步false 压缩 默认同步
                        .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
            }
        }));
        addSubscribe(RxView.clicks(clNickname).throttleFirst(Config.WINDOWDURATION, TimeUnit.SECONDS).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                final int editId = 0x000033333;
                QMUIDialog.EditTextDialogBuilder builder = new QMUIDialog.EditTextDialogBuilder(mContext)
                        .setDefaultText(etNickname.getText().toString())
                        .setPlaceholder("请输入您的昵称")
                        .addAction("取消", new QMUIDialogAction.ActionListener() {
                            @Override
                            public void onClick(QMUIDialog dialog, int index) {
                                dialog.dismiss();
                            }
                        })
                        .addAction("确定", new QMUIDialogAction.ActionListener() {
                            @Override
                            public void onClick(QMUIDialog dialog, int index) {
                                EditText editText = dialog.findViewById(editId);
                                if (!TextUtils.isEmpty(editText.getText().toString()) && editText.getText().toString().length() <= 10) {
                                    // TODO: 2019/5/5 上传新的昵称数据 ，并在上传成功之后更新本地昵称信息
                                    ToastUtils.showShort("上传新的昵称数据");
                                    etNickname.setText(editText.getText().toString());
                                } else {
                                    ToastUtils.showShort("昵称长度不可超过10位！");
                                }
                            }
                        });
                builder.create().show();
                EditText editText = builder.getEditText();
                editText.setId(editId);
                editText.setInputType(InputType.TYPE_CLASS_TEXT); //输入类型
                editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(6)}); //最大输入长度
                editText.setMaxLines(1);
            }
        }));
        addSubscribe(RxView.clicks(clLocation).throttleFirst(Config.WINDOWDURATION, TimeUnit.SECONDS).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                ToastUtils.showShort("选择地址");
            }
        }));
        addSubscribe(RxView.clicks(clGender).throttleFirst(Config.WINDOWDURATION, TimeUnit.SECONDS).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                ToastUtils.showShort("选择性别");
                new QMUIDialog.MenuDialogBuilder(mContext)
                        .addItems(new String[]{"男", "女"}, new QMUIDialog.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (0 == which) {
                                    ToastUtils.showShort("选择了男");
                                } else {
                                    ToastUtils.showShort("选择了女");
                                }
                                dialog.cancel();
                            }
                        }).show();
            }
        }));
        addSubscribe(RxView.clicks(clBirthday).throttleFirst(Config.WINDOWDURATION, TimeUnit.SECONDS).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                ToastUtils.showShort("选择生日");
            }
        }));
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
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
        return this.bindToLifecycle();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片、视频、音频选择结果回调
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true  注意：音视频除外
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true  注意：音视频除外
                    // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    if (selectList != null && selectList.size() > 0) {
                        LocalMedia media = selectList.get(0);
                        if (media.isCut()) {
                            String cutPath = selectList.get(0).getCutPath();
                            // TODO: 2019/5/5 1、上传选择的图片

                            //2、本地修改图片的加载
                            ivChangePhoto.setUrlImage(cutPath);
                            // TODO: 2019/5/5 3、通知其他有头像的页面刷新页面
                        }
                    }
                    break;
            }
        }
    }
}
