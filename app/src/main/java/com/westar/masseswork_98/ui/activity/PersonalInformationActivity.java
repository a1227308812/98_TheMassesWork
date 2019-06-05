package com.westar.masseswork_98.ui.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TextInputEditText;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
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
import com.westar.been.LocationNode;
import com.westar.been.User;
import com.westar.library_base.base.BaseApplication;
import com.westar.library_base.base.BasePresenter;
import com.westar.library_base.base.ToolbarActivity;
import com.westar.library_base.common.ArouterPath;
import com.westar.library_base.eventbus.EventBusUtlis;
import com.westar.library_base.eventbus.UpdataUserInfoEvent;
import com.westar.library_base.http.been.HttpRequest;
import com.westar.library_base.utils.PickerUtil;
import com.westar.masseswork_98.R;
import com.westar.masseswork_98.mvp.contract.PersonalInformationContract;
import com.westar.masseswork_98.mvp.presenter.PersonalInformationPresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.functions.Consumer;

/**
 * Created by ZWP on 2019/5/5 11:27.
 * 描述：个人信息页面
 */
@Route(path = ArouterPath.PERSONAL_INFORMATION_ACTIVITY)
public class PersonalInformationActivity extends ToolbarActivity implements PersonalInformationContract.View {

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
    @BindView(R.id.stv_confirm)
    SuperTextView stvConfirm;

    PersonalInformationPresenter presenter;

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
        User user = BaseApplication.getIns().getUser();
        if (user != null) {
            //加载头像
            ivChangePhoto.setUrlImage(user.getPhotoUrl());
            etNickname.setText(user.getUserName());
            etGender.setText(user.getGender());
            etLocation.setText(user.getDomicile_address());
            etBirthday.setText(user.getBirthday());
        }

        ininLisenter();
    }

    @Override
    protected void initData() {

    }

    private void ininLisenter() {
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
                                    // TODO: 2019/5/5 修改新的昵称数据 ，并在点击更新之后上传更新的昵称信息
                                    etNickname.setText(editText.getText().toString());
                                } else {
                                    ToastUtils.showShort("昵称长度不可超过10位！");
                                }
                                dialog.cancel();
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
                presenter.getServiceAddressData(new HttpRequest());
            }
        }));
        addSubscribe(RxView.clicks(clGender).throttleFirst(Config.WINDOWDURATION, TimeUnit.SECONDS).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                PickerUtil.alertGenderPicker(mContext, new PickerUtil.OnGenderPickerLisenter() {
                    @Override
                    public void onClick(View view, int postion, PickerUtil.Gender gender) {
                        etGender.setText(gender.getTitle());
                    }
                });
            }
        }));
        addSubscribe(RxView.clicks(clBirthday).throttleFirst(Config.WINDOWDURATION, TimeUnit.SECONDS).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                PickerUtil.alertTimerPicker(mContext, PickerUtil.PickerType.YEAR_MONTH_DAY, new PickerUtil.TimerPickerCallBack() {
                    @Override
                    public void onTimeSelect(String date) {
                        etBirthday.setText(date);
                    }
                });
            }
        }));

        addSubscribe(RxView.clicks(stvConfirm).throttleFirst(Config.WINDOWDURATION, TimeUnit.SECONDS).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                //提交修改的数据
                if (TextUtils.isEmpty(etNickname.getText().toString())) {

                }
                if (TextUtils.isEmpty(etGender.getText().toString())) {

                }
                if (TextUtils.isEmpty(etLocation.getText().toString())) {

                }
                if (TextUtils.isEmpty(etBirthday.getText().toString())) {

                }
                presenter.updatePersonalInfo(new HttpRequest());
            }
        }));
    }

    /**
     * 选择地区
     *
     * @param locationNodeList
     */
    private void choiceAddress(List<LocationNode> locationNodeList) {
        // TODO: 2019/5/6
        if (null != locationNodeList && locationNodeList.size() > 0) {
            final List<LocationNode> provinceList = locationNodeList;
            final List<List<LocationNode>> cityList = new ArrayList<>();
            final List<List<List<LocationNode>>> areaList = new ArrayList<>();
            //初始化数据
            for (LocationNode province : provinceList) {
                List<LocationNode> citys = province.getChilds();
                cityList.add(citys);

                List<List<LocationNode>> cityAreas = new ArrayList<>();
                for (LocationNode city : citys) {
                    List<LocationNode> areas = city.getChilds();
                    cityAreas.add(areas);
                }
                areaList.add(cityAreas);
            }

            OptionsPickerBuilder builder = new OptionsPickerBuilder(mContext, new OnOptionsSelectListener() {
                @Override
                public void onOptionsSelect(int options1, int options2, int options3, View v) {
                    etLocation.setText(provinceList.get(options1).getName() + "--"
                            + cityList.get(options1).get(options2).getName() + "--"
                            + areaList.get(options1).get(options2).get(options3).getName());
                }
            });

            builder.setTitleText("请选择地址")
                    .setOutSideCancelable(true)
                    .setCyclic(false, false, false)
                    .build();

            OptionsPickerView pvOptions = builder.build();
            pvOptions.setKeyBackCancelable(true);
            pvOptions.setPicker(provinceList, cityList, areaList);
            pvOptions.show();
        }
    }

    @Override
    protected BasePresenter createPresenter() {
        return presenter = new PersonalInformationPresenter();
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
        if (data != null) {
            List<LocationNode> locationNodeList = (List<LocationNode>) data;
            choiceAddress(locationNodeList);
        }
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
                            presenter.updatePersonalInfo(new HttpRequest());
                            //2、本地修改图片的加载
                            ivChangePhoto.setUrlImage(cutPath);
                            // TODO: 2019/5/5 3、通知其他有头像的页面刷新页面
                        }
                    }
                    break;
            }
        }
    }

    @Override
    public void updatePersonalResult(User user) {
        if (user != null) {
            ToastUtils.showShort("修改成功！");
            BaseApplication.getIns().setUser(user);
            // TODO: 2019/5/10 修改个人头像显示和名称显示
            EventBusUtlis.sendStickyEvent(new UpdataUserInfoEvent());
        } else {
            ToastUtils.showShort("修改失败！请稍后重试！");
        }
    }
}
