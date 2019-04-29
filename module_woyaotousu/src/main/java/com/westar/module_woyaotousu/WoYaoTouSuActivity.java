package com.westar.module_woyaotousu;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.IBinder;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.qmuiteam.qmui.alpha.QMUIAlphaImageButton;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.westar.library_base.base.BasePresenter;
import com.westar.library_base.base.ToolbarActivity;
import com.westar.library_base.common.ArouterPath;
import com.westar.module_woyaotousu.databean.MenuItem;
import com.westar.module_woyaotousu.fragment.BottomMenuFragment;
import com.westar.module_woyaotousu.listener.MenuItemOnClickListener;
import com.westar.module_woyaotousu.util.DrawableUtil;
import com.westar.module_woyaotousu.widget.InputTextMsgDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZWP on 2019/4/8 11:20.
 * 描述：我要投诉发起界面
 */
@Route(path = ArouterPath.MODULE_WOYAOTOUSU_WO_YAO_TOU_SU_ACTIVITY)
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
    private LinearLayout llTousuTitle; //title
    private EditText etvTousuObject; //object
    private TextView tvTousuObjectHint;
    private EditText etvTousuUnit; //unit
    private TextView tvTousuUnitHint;
    private EditText etvTousuContent; //content
    private TextView tvTousuContentHint;
    private TextView tvTousuAttachment; //附件
    public DrawableUtil drawableUtil; //设置"+"点击

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
        llTousuTitle = (LinearLayout) findViewById(R.id.ll_tousu_title);
        //object
        etvTousuObject = (EditText) findViewById(R.id.etv_tousu_object);
        tvTousuObjectHint = (TextView) findViewById(R.id.tv_tousu_hint_object);
        //unit
        etvTousuUnit = (EditText) findViewById(R.id.etv_tousu_unit);
        tvTousuUnitHint = (TextView) findViewById(R.id.tv_tousu_hint_unit);
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
        setEditTextId(tvTousuIdHint, tvWarning, etvTousuId, "*身份证：", 18);
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
        setEditTextId(tvTousuPhoneHint, tvWarningPhone, etvTousuPhone, "*手机号码：", 13);
        //投诉主题
        llTousuTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSelectDialogFragment();
            }
        });
        //投诉对象
        setEditTextNormal(tvTousuObjectHint, etvTousuObject, "投诉对象：");
        //投诉对象单位
        setEditTextNormal(tvTousuUnitHint, etvTousuUnit, "投诉对象单位：");
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
                        editText.setHint(content);
                        textView.setVisibility(View.INVISIBLE);
                    }
                    else editText.setText(editText.getText().toString());
                }
            }
        });
    }

    //设置数字输入类型的edittext的获取焦点事件
    private void setEditTextId (final TextView textView, final TextView tvWarning, final EditText editText ,final String content, final int number) {
        editText.setOnFocusChangeListener(new android.view.View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    textView.setVisibility(View.VISIBLE);
                    editText.setHint("");
                } else {
                    if (editText.getText().toString().trim().isEmpty()) {
                        editText.setHint(content);
                        textView.setVisibility(View.INVISIBLE);
                        tvWarning.setVisibility(View.INVISIBLE);
                    }
                    else {
                        editText.setText(editText.getText().toString());
                        if (editText.getText().toString().length() < number) {
                            tvWarning.setVisibility(View.VISIBLE);
                            tvWarning.setText("请输入正确长度的数字！");
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
    public void showSelectDialogFragment() {
        BottomMenuFragment bottomMenuFragment = new BottomMenuFragment();

        List<MenuItem> menuItemList = new ArrayList<MenuItem>();
        MenuItem menuItem1 = new MenuItem();
        menuItem1.setText("Hello World");
        MenuItem menuItem2 = new MenuItem();
        menuItem2.setText("Menu Btn 2");
        MenuItem menuItem3 = new MenuItem();
        menuItem3.setText("点击！");
        menuItem3.setMenuItemOnClickListener(new MenuItemOnClickListener(bottomMenuFragment, menuItem3) {
            @Override
            public void onClickMenuItem(View v, MenuItem menuItem) {

            }
        });
        menuItemList.add(menuItem1);
        menuItemList.add(menuItem2);
        menuItemList.add(menuItem3);

        bottomMenuFragment.setMenuItems(menuItemList);

        bottomMenuFragment.show(getFragmentManager(), "BottomMenuFragment");
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
        return null;
    }
}
