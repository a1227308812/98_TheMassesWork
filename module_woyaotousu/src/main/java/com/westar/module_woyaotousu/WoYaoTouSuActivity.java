package com.westar.module_woyaotousu;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.IBinder;
import android.text.Editable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.jakewharton.rxbinding2.view.RxView;
import com.kyleduo.switchbutton.SwitchButton;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.westar.Config;
import com.westar.library_base.base.BasePresenter;
import com.westar.library_base.base.ToolbarActivity;
import com.westar.library_base.common.ArouterPath;
import com.westar.library_base.http.ObserverManager;
import com.westar.library_base.http.been.HttpResult;
import com.westar.library_base.rxjava.RxScheduler;
import com.westar.module_woyaotousu.databean.MenuItem;
import com.westar.module_woyaotousu.databean.TousuForm;
import com.westar.module_woyaotousu.fragment.BottomMenuFragment;
import com.westar.module_woyaotousu.listener.MenuItemOnClickListener;
import com.westar.module_woyaotousu.util.DrawableUtil;
import com.westar.module_woyaotousu.util.Validator;
import com.westar.module_woyaotousu.widget.InputTextMsgDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.functions.Consumer;

import static android.view.View.GONE;

/**
 * Created by ZWP on 2019/4/8 11:20.
 * 描述：我要投诉发起界面
 */
@Route(path = ArouterPath.WOYAOTOUSU_ACTIVITY)
public class WoYaoTouSuActivity extends ToolbarActivity {

    private InputTextMsgDialog inputTextMsgDialog; //自定义dialog
    private EditText etvTousuName;  //name
    private TextView tvTousuNameHint;
    private EditText etvTousuId; //id
    private TextView tvTousuIdHint;
    private TextView tvWarning;
    private EditText etvTousuPhone; //phone
    private TextView tvTousuPhoneHint;
    private TextView tvWarningPhone;
    private EditText etvTousuTitle; //title
    private TextView tvTousuHintTitle;
    private LinearLayout llTousuUnit;//unit
    private TextView tvTousuUnit;
    private TextView tvTousuHintUnit;
    private SwitchButton sbIsPublic; //ispublic
    private EditText etvTousuVerifyCode;//verifycode
    private TextView tvTousuHintVerifyCode;
    private EditText etvTousuContent; //content
    private TextView tvTousuContentHint;
    private TextView tvTousuAttachment; //附件
    public DrawableUtil drawableUtil; //设置"+"点击
    private static final int TYPE_NUMBER = 0; //限制输入长度的文本类型
    private static final int TYPE_WORD = 1;

    private String isPublic;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_wo_yao_tou_su;
    }

    @Override
    protected void findId() {
        inputTextMsgDialog = new InputTextMsgDialog(this, R.style.dialog_center);//自定义dialog
        //姓名
        etvTousuName = (EditText) findViewById(R.id.etv_tousu_name);
        tvTousuNameHint = (TextView) findViewById(R.id.tv_tousu_hint_name);
        //id
        etvTousuId = (EditText) findViewById(R.id.etv_tousu_id);
        tvTousuIdHint = (TextView) findViewById(R.id.tv_tousu_hint_id);
        tvWarning = (TextView) findViewById(R.id.tv_warning);
        //phone
        etvTousuPhone = (EditText) findViewById(R.id.etv_tousu_phone);
        tvTousuPhoneHint = (TextView) findViewById(R.id.tv_tousu_hint_phone);
        tvWarningPhone = (TextView) findViewById(R.id.tv_warning_phone);
        //title
        etvTousuTitle = (EditText) findViewById(R.id.etv_tousu_title);
        tvTousuHintTitle = (TextView) findViewById(R.id.tv_tousu_hint_title);
        //unit
        llTousuUnit= (LinearLayout) findViewById(R.id.ll_tousu_unit);
        tvTousuUnit = (TextView) findViewById(R.id.tv_tousu_unit);
        tvTousuHintUnit = (TextView) findViewById(R.id.tv_tousu_hint_unit);
        //ispublic
        sbIsPublic = (SwitchButton) findViewById(R.id.sb_is_public);
        //verifycode
        etvTousuVerifyCode = (EditText) findViewById(R.id.etv_tousu_verify_code);
        tvTousuHintVerifyCode = (TextView) findViewById(R.id.tv_tousu_hint_verify_code);
        //content
        etvTousuContent = (EditText) findViewById(R.id.etv_tousu_content);
        tvTousuContentHint = (TextView) findViewById(R.id.tv_tousu_hint_content);
        //附件
        tvTousuAttachment = (TextView) findViewById(R.id.tv_tousu_attachment);

    }

    @Override
    protected void initView() {
        //姓名栏
        setEditTextNormal(tvTousuNameHint, etvTousuName ,"*姓名：");
        //身份证栏
        setEditTextId(tvTousuIdHint, tvWarning, etvTousuId, "*身份证：", 18, TYPE_WORD);
        //edittext手机栏输入显示“-”
        etvTousuPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                if (charSequence == null || charSequence.length() == 0) {
                    return;
                }
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < charSequence.length(); i++) {
                    if (i != 3 && i != 8 && charSequence.charAt(i) == '-') {
                        continue;
                    } else {
                        stringBuilder.append(charSequence.charAt(i));
                        if ((stringBuilder.length() == 4 || stringBuilder.length() == 9)
                                && stringBuilder.charAt(stringBuilder.length() - 1) != '-') {
                            stringBuilder.insert(stringBuilder.length() - 1, '-');
                        }
                    }
                }
                if (!stringBuilder.toString().equals(charSequence.toString())) {
                    int index = start + 1;
                    if (stringBuilder.charAt(start) == '-') {
                        if (before == 0) {
                            index++;
                        } else {
                            index--;
                        }
                    } else {
                        if (before == 1) {
                            index--;
                        }
                    }
                    etvTousuPhone.setText(stringBuilder.toString());
                    etvTousuPhone.setSelection(index);

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        //手机栏
        setEditTextId(tvTousuPhoneHint, tvWarningPhone, etvTousuPhone, "*手机号码：", 13, TYPE_NUMBER);
        //投诉标题
        setEditTextNormal(tvTousuHintTitle, etvTousuTitle, "投诉标题：");
        //投诉单位
        llTousuUnit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppClient.getInstance()
                        .creatAPI()
                        .tousuTitleList()
                        .compose(bindViewToLifecycle())
                        .compose(RxScheduler.rxObservableSchedulerHelper())
                        .subscribe(new ObserverManager<List<MenuItem>>(getBaseContext()) {
                            @Override
                            protected void onOther(HttpResult<List<MenuItem>> httpResult) {

                            }

                            @Override
                            protected void onSuccess(List<MenuItem> data) {
                                showSelectDialogFragment(data);
                            }


                            @Override
                            protected void onFailure(Throwable e) {

                            }

                            @Override
                            protected void onFinish() {
                                hideLoading();
                            }
                        });
            }
        });
        //是否公开
        sbIsPublic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    isPublic = "1";
                } else
                    isPublic = "0";
            }
        });
        //验证码
        setEditTextNormal(tvTousuHintVerifyCode, etvTousuVerifyCode, "*图形验证码：");
        //TODO 接口获得验证码

        //投诉内容
        setEditTextContent(tvTousuContentHint, etvTousuContent);
        //附件
        drawableUtil = new DrawableUtil(tvTousuAttachment, new DrawableUtil.OnDrawableListener() {
            @Override
            public void onRight(View v, Drawable right) {
                Toast.makeText(getApplicationContext(), "ok", Toast.LENGTH_SHORT).show();
            }
        });
        //提交表单
        findViewById(R.id.stv_tousu_commit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkInfo()) {
                    showLoading();
                    TousuForm tousuForm = new TousuForm();
                    tousuForm.setName(etvTousuName.getText().toString().trim());
                    tousuForm.setIdentityId(etvTousuId.getText().toString().trim());
                    tousuForm.setPhone(etvTousuPhone.getText().toString().trim());
                    tousuForm.setTitle(etvTousuTitle.getText().toString().trim());
                    tousuForm.setIsPublic(isPublic);
                    tousuForm.setVerifyCode(etvTousuVerifyCode.getText().toString().trim());
                    tousuForm.setContent(etvTousuContent.getText().toString().trim());
                    AppClient.getInstance()
                            .creatAPI()
                            .submit(new Gson().toJson(tousuForm))
                            .compose(bindViewToLifecycle())
                            .compose(RxScheduler.rxObservableSchedulerHelper())
                            .subscribe(new ObserverManager<String>(getBaseContext()) {
                                @Override
                                protected void onOther(HttpResult<String> httpResult) {

                                }

                                @Override
                                protected void onSuccess(String data) {
                                    ToastUtils.showShort(data);
                                    finish();
                                }

                                @Override
                                protected void onFailure(Throwable e) {

                                }

                                @Override
                                protected void onFinish() {

                                }
                            });
                }
            }
        });
    }

    @Override
    protected void initData() {
    }

    @Override
    public String setBarTitle() {
        return "我要投诉";
    }

    //设置字符输入类型的edittext的获取焦点事件
    private void setEditTextNormal(final TextView textView, final EditText editText , final String content) {
        editText.setOnFocusChangeListener(new android.view.View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    textView.setVisibility(View.VISIBLE);
                    editText.setHint("");
                } else {
                    if (editText.getText().toString().trim().isEmpty()) {
                        editText.setText("");
                        editText.setHint(content);
                        textView.setVisibility(View.INVISIBLE);
                    }
                    else editText.setText(editText.getText().toString());
                }
            }
        });
    }

    //设置数字输入类型的edittext的获取焦点事件
    private void setEditTextId (final TextView textView, final TextView tvWarning, final EditText editText ,final String content, final int number, final int type) {
        editText.setOnFocusChangeListener(new android.view.View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    textView.setVisibility(View.VISIBLE);
                    editText.setHint("");
                    //实时监控文本长度并显示提示语
                    editText.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            if (s.length() != number) {
                                tvWarning.setVisibility(View.VISIBLE);
                                if (type == TYPE_NUMBER) {
                                    tvWarning.setText("请输入正确长度的数字！");
                                } else
                                    tvWarning.setText("请输入正确长度的身份证号码！");
                            }
                            else
                                tvWarning.setVisibility(View.GONE);
                        }
                    });
                } else {
                    if (editText.getText().toString().trim().isEmpty()) {
                        editText.setText("");
                        editText.setHint(content);
                        textView.setVisibility(View.INVISIBLE);
                        tvWarning.setVisibility(View.INVISIBLE);
                    }
                    else {
                        editText.setText(editText.getText().toString());
                        if (editText.getText().toString().length() < number) {
                            tvWarning.setVisibility(View.VISIBLE);
                            if (type == TYPE_NUMBER) {
                                tvWarning.setText("请输入正确长度的数字！");
                            } else  tvWarning.setText("请输入正确长度的身份证号码！");

                        } else tvWarning.setVisibility(View.INVISIBLE);
                    }
                }
            }
        });
    }

    //设置投诉内容的edittext的输入对话框和获取焦点事件
    private void setEditTextContent(final TextView textView, final EditText editText) {

        editText.setOnFocusChangeListener(new android.view.View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    if (editText.getText().toString().isEmpty()) {
                        inputTextMsgDialog.show();
                        editText.clearFocus();
                    } else {
                        inputTextMsgDialog.show(editText.getText().toString());
                        editText.clearFocus();
                    }
                }
            }
        });

        //将dialog的数据传到text
        inputTextMsgDialog.setmOnTextSendListener(new InputTextMsgDialog.OnTextSendListener() {
            @Override
            public void onTextSend(String msg) {
                String content = msg.trim();
                editText.setText(content);
                if (!content.isEmpty()) {
                    textView.setVisibility(View.VISIBLE);
                }
                else {
                    textView.setVisibility(View.INVISIBLE);
                }
            }
        });

        inputTextMsgDialog.setmOnTextCancelListener(new InputTextMsgDialog.OnTextCancelListener() {
            @Override
            public void onTextCancel(String msg) {
                editText.setText("");
                textView.setVisibility(View.INVISIBLE);
            }

        });
    }

    //展示仿ios底部弹出框及数据交互
    public void showSelectDialogFragment(List<MenuItem> data) {
        final BottomMenuFragment bottomMenuFragment = new BottomMenuFragment();


//        List<MenuItem> menuItemList = new ArrayList<MenuItem>();
//        MenuItem menuItem1 = new MenuItem();
//        menuItem1.setText("Hello World");
//        MenuItem menuItem2 = new MenuItem();
//        menuItem2.setText("Menu Btn 2");
//        MenuItem menuItem3 = new MenuItem();
//        menuItem3.setText("点击！");
//        menuItemList.add(menuItem1);
//        menuItemList.add(menuItem2);
//        menuItemList.add(menuItem3);

        bottomMenuFragment.setMenuItems(data);

        bottomMenuFragment.show(getFragmentManager(), "BottomMenuFragment");
        for (MenuItem menuItem : data) {
            menuItem.setMenuItemOnClickListener(new MenuItemOnClickListener(bottomMenuFragment, menuItem) {
                @Override
                public void onClickMenuItem(View v, MenuItem menuItem) {
                    tvTousuUnit.setText(menuItem.getText());
                    tvTousuHintUnit.setVisibility(View.VISIBLE);
                    bottomMenuFragment.dismiss();
                }
            });
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideKeyboard(v, ev)) {
                hideKeyboard(v.getWindowToken());
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时则不能隐藏
     *
     * @param v
     * @param event
     * @return
     */
    private boolean isShouldHideKeyboard(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0],
                    top = l[1],
                    bottom = top + v.getHeight(),
                    right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击EditText的事件，忽略它。
                return false;
            } else {
                return true;
            }
        }
        // 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditText上，和用户用轨迹球选择其他的焦点
        return false;
    }

    /**
     * 获取InputMethodManager，隐藏软键盘
     * @param token
     */
    private void hideKeyboard(IBinder token) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    //检查格式
    private boolean checkInfo() {
        if (TextUtils.isEmpty(etvTousuName.getText().toString().trim())) {
            ToastUtils.showShort("请输入投诉人姓名！");
            return false;
        } else if (TextUtils.isEmpty(etvTousuId.getText().toString().trim())) {
            ToastUtils.showShort("请输入投诉人身份证！");
            return false;
        } else if (!Validator.isIDCard(etvTousuId.getText().toString())) {
            ToastUtils.showShort("身份证格式不正确！");
            return false;
        } else if (etvTousuPhone.getText().length() <13) {
            ToastUtils.showShort("手机号长度不对!");
            return false;
        } else if (TextUtils.isEmpty(etvTousuVerifyCode.getText().toString().trim())){
            ToastUtils.showShort("请输入图形验证码！");
            return false;
        } else if (TextUtils.isEmpty(etvTousuContent.getText().toString().trim())) {
            ToastUtils.showShort("请输入投诉内容！");
            return false;
        } else
            return true;
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
}
