package com.westar.module_login.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.ToastUtils;
import com.coorchice.library.SuperTextView;
import com.jakewharton.rxbinding2.view.RxView;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.westar.Config;
import com.westar.library_base.base.BasePresenter;
import com.westar.library_base.base.ToolbarActivity;
import com.westar.library_base.common.ArouterPath;
import com.westar.library_base.common.Common;
import com.westar.library_base.eventbus.EventBusUtlis;
import com.westar.library_base.eventbus.SolideTypeEvent;
import com.westar.library_base.glide.GlideApp;
import com.westar.module_login.R;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * Created by ZWP on 2019/4/16 11:04.
 * 描述：身份证认证界面
 */
public class IDCardConfirmActivity extends ToolbarActivity {

    TextView titleInfo;
    ImageView idCardPositive;
    ImageView idCardWrongSide;
    CheckedTextView idconfirmProtocol;
    SuperTextView upload;

    ImageView positiveDelete;
    ImageView positivePreview;
    ImageView wrongDelete;
    ImageView wrongPreview;


    final int SELECT_ID_CARD_POSITIVE = 1200;
    final int SELECT_ID_CARD_WRONG_SIDE = 1201;

    List<LocalMedia> positiveList;
    List<LocalMedia> wrongList;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_idcard_confirm;
    }

    @Override
    protected void findId() {
        titleInfo = findViewById(R.id.title_info);
        idCardPositive = findViewById(R.id.id_card_positive);
        idCardWrongSide = findViewById(R.id.id_card_wrong_side);
        idconfirmProtocol = findViewById(R.id.idconfirm_protocol);
        upload = findViewById(R.id.upload);
        positiveDelete = findViewById(R.id.positive_delete);
        positivePreview = findViewById(R.id.positive_preview);
        wrongDelete = findViewById(R.id.wrong_delete);
        wrongPreview = findViewById(R.id.wrong_preview);
    }

    @Override
    protected void initView() {

        idconfirmProtocol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idconfirmProtocol.setChecked(!idconfirmProtocol.isChecked());
            }
        });

        addSubscribe(RxView.clicks(positiveDelete)
                .throttleFirst(Config.WINDOWDURATION, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        if (positiveList != null && positiveList.size() > 0) {
                            idCardPositive.setImageResource(R.mipmap.png_sfz_zm);
                            positiveList.clear();
                        }
                    }
                }));
        addSubscribe(RxView.clicks(positivePreview)
                .throttleFirst(Config.WINDOWDURATION, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        if (positiveList != null && positiveList.size() > 0) {
                            PictureSelector.create(IDCardConfirmActivity.this)
                                    .externalPicturePreview(0, positiveList);
                        }
                    }
                }));
        addSubscribe(RxView.clicks(wrongDelete)
                .throttleFirst(Config.WINDOWDURATION, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        if (wrongList != null && wrongList.size() > 0) {
                            idCardWrongSide.setImageResource(R.mipmap.png_sfz_bm);
                            wrongList.clear();
                        }
                    }
                }));
        addSubscribe(RxView.clicks(wrongPreview)
                .throttleFirst(Config.WINDOWDURATION, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        if (wrongList != null && wrongList.size() > 0) {
                            PictureSelector.create(IDCardConfirmActivity.this)
                                    .externalPicturePreview(0, wrongList);
                        }
                    }
                }));
        addSubscribe(RxView.clicks(idCardPositive).throttleFirst(Config.WINDOWDURATION, TimeUnit.SECONDS).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                // 进入相册 以下是例子：用不到的api可以不写
                PictureSelector.create(IDCardConfirmActivity.this)
                        .openGallery(PictureMimeType.ofImage())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                        .maxSelectNum(1)// 最大图片选择数量 int
                        .minSelectNum(1)// 最小选择数量 int
                        .imageSpanCount(4)// 每行显示个数 int
                        .selectionMode(PictureConfig.SINGLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                        .previewImage(true)// 是否可预览图片 true or false
                        .isCamera(true)// 是否显示拍照按钮 true or false
                        .imageFormat(PictureMimeType.JPEG)// 拍照保存图片格式后缀,默认jpeg
                        .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                        .sizeMultiplier(1f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                        .isGif(false)// 是否显示gif图片 true or false
                        .previewEggs(true)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中) true or false
                        .isDragFrame(false)// 是否可拖动裁剪框(固定)
                        .forResult(SELECT_ID_CARD_POSITIVE);//结果回调onActivityResult code
            }
        }));
        addSubscribe(RxView.clicks(idCardWrongSide).throttleFirst(Config.WINDOWDURATION, TimeUnit.SECONDS).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                PictureSelector.create(IDCardConfirmActivity.this)
                        .openGallery(PictureMimeType.ofImage())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                        .maxSelectNum(1)// 最大图片选择数量 int
                        .minSelectNum(1)// 最小选择数量 int
                        .imageSpanCount(4)// 每行显示个数 int
                        .selectionMode(PictureConfig.SINGLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                        .previewImage(true)// 是否可预览图片 true or false
                        .isCamera(true)// 是否显示拍照按钮 true or false
                        .imageFormat(PictureMimeType.JPEG)// 拍照保存图片格式后缀,默认jpeg
                        .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                        .sizeMultiplier(1f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                        .isGif(false)// 是否显示gif图片 true or false
                        .previewEggs(true)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中) true or false
                        .isDragFrame(false)// 是否可拖动裁剪框(固定)
                        .forResult(SELECT_ID_CARD_WRONG_SIDE);//结果回调onActivityResult code
            }
        }));
        addSubscribe(RxView.clicks(upload).throttleFirst(Config.WINDOWDURATION, TimeUnit.SECONDS).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                if (idconfirmProtocol.isChecked()) {
                    final QMUITipDialog tipDialog = new QMUITipDialog.Builder(mContext)
                            .setTipWord("提交身份信息中")
                            .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                            .create(false);
                    tipDialog.show();
                    new Handler(getMainLooper()).postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            tipDialog.dismiss();
                            ToastUtils.showShort("检测通过！");
                            EventBusUtlis.sendStickyEvent(new SolideTypeEvent(Common.HAD_AUTHENTICATION));
                            //跳转主页
                            ARouter.getInstance().build(ArouterPath.APP_HOMEGROUP_ACTIVITY).navigation();
                        }
                    }, 1500);
                } else {
                    ToastUtils.showShort("请先阅读并同意《实名认证协议》！");
                }
            }
        }));

    }

    @Override
    protected void initData() {

    }

    public String setBarTitle() {
        return "身份证认证";
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
            if (requestCode == SELECT_ID_CARD_POSITIVE) {
                positiveList = PictureSelector.obtainMultipleResult(data);
                // 图片、视频、音频选择结果回调
                // 例如 LocalMedia 里面返回三种path
                // 1.media.getPath(); 为原图path
                // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true  注意：音视频除外
                // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true  注意：音视频除外
                // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
//                    imageGridAdapter.bindImagesData(selectList);
                if (null != positiveList.get(0)) {
                    GlideApp.with(mContext).load(positiveList.get(0).getPath()).into(idCardPositive);
                }
            } else if (requestCode == SELECT_ID_CARD_WRONG_SIDE) {
                wrongList = PictureSelector.obtainMultipleResult(data);
                // 图片、视频、音频选择结果回调
                // 例如 LocalMedia 里面返回三种path
                // 1.media.getPath(); 为原图path
                // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true  注意：音视频除外
                // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true  注意：音视频除外
                // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
//                    imageGridAdapter.bindImagesData(selectList);
                if (null != wrongList.get(0)) {
                    GlideApp.with(mContext).load(wrongList.get(0).getPath()).into(idCardWrongSide);
                }
            }
        }
    }

}
