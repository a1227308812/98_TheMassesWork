package com.westar.module_woyaozixun.activity;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.qmuiteam.qmui.alpha.QMUIAlphaImageButton;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.westar.library_base.base.BasePresenter;
import com.westar.library_base.base.ToolbarActivity;
import com.westar.module_woyaozixun.R;
import com.westar.module_woyaozixun.databean.MenuItem;
import com.westar.module_woyaozixun.fragment.BottomMenuFragment;
import com.westar.module_woyaozixun.listener.MenuItemOnClickListener;
import com.westar.module_woyaozixun.util.DrawableUtil;
import com.westar.module_woyaozixun.widget.InputTextMsgDialog;

import java.util.ArrayList;
import java.util.List;

public class LiuYanZiXunActivity extends ToolbarActivity {

    private EditText etvZixunName;  //name
    private TextView tvZixunNameHint;
    private EditText etvZixunPhone; //phone
    private TextView tvZixunPhoneHint;
    private TextView tvWarningPhone;
    private LinearLayout llZixunTitle; //title
    private EditText etvZixunContent; //content
    private TextView tvZixunContentHint;
    private InputTextMsgDialog inputTextMsgDialog; //自定义dialog
    private TextView tvZixunAttachment; //附件
    public DrawableUtil drawableUtil; //设置"+"点击

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_liu_yan_zi_xun;
    }

    @Override
    protected void findId() {
        //姓名
        etvZixunName = (EditText) findViewById(R.id.etv_zixun_name);
        tvZixunNameHint = (TextView) findViewById(R.id.tv_zixun_hint_name);
        //phone
        etvZixunPhone = (EditText) findViewById(R.id.etv_zixun_phone);
        tvZixunPhoneHint = (TextView) findViewById(R.id.tv_zixun_hint_phone);
        tvWarningPhone = (TextView) findViewById(R.id.tv_warning_phone);
        //title
        llZixunTitle = (LinearLayout) findViewById(R.id.ll_zixun_title);
        //content
        etvZixunContent = (EditText) findViewById(R.id.etv_zixun_content);
        tvZixunContentHint = (TextView) findViewById(R.id.tv_zixun_hint_content);
        //自定义dialog
        inputTextMsgDialog = new InputTextMsgDialog(this, R.style.dialog_center);
        //附件
        tvZixunAttachment = (TextView) findViewById(R.id.tv_zixun_attachment);



    }

    @Override
    protected void initView() {
        //姓名栏
        setEditTextNormal(tvZixunNameHint, etvZixunName ,"*姓名：");
        //手机栏
        etvZixunPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

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
                    etvZixunPhone.setText(stringBuilder.toString());
                    etvZixunPhone.setSelection(index);

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        setEditTextId(tvZixunPhoneHint, tvWarningPhone, etvZixunPhone, "*手机号码：", 13);
        //资讯标题
        llZixunTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSelectDialogFragment();
            }
        });
        //咨询内容
        setEditTextContent(tvZixunContentHint, etvZixunContent);
        //附件
        drawableUtil = new DrawableUtil(tvZixunAttachment, new DrawableUtil.OnDrawableListener() {
            @Override
            public void onRight(View v, Drawable right) {
                Toast.makeText(getApplicationContext(), "ok", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void initData() {

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

    @Override
    public String setBarTitle() {
        return "留言咨询";
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
