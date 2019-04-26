package com.westar.module_woyaozixun.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatDialog;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.westar.module_woyaozixun.R;

/**
 * Created by lgy on 2019/4/10 15:33
 * 描述：自定义Dialog控件
 */

public class InputTextMsgDialog extends AppCompatDialog {

    private Context mContext;
    private InputMethodManager imm;
    private EditText messageTextView;
    //private TextView confirmBtn;
    private TextView tvSend;
    private TextView tvCancel;
    private RelativeLayout rlDlg;
    private int mLastDiff = 0;
    private TextView tvNumber;
    private int maxNumber = 100;
    private LinearLayout llVoiceInput;

    //点击发送button的回调接口
    public interface OnTextSendListener {
        void onTextSend(String msg);
    }

    public interface OnTextCancelListener {
        void onTextCancel(String msg);
    }

    private OnTextSendListener mOnTextSendListener;
    private OnTextCancelListener mOnTextCancelListener;

    public InputTextMsgDialog(@NonNull Context context, int theme) {
        super(context, theme);
        this.mContext = context;
        //this.getWindow().setWindowAnimations(R.style.main_menu_animstyle);
        init();
        setLayout();
    }

    /**
     * 最大输入字数  默认100
     */
    @SuppressLint("SetTextI18n")
    public void setMaxNumber(int maxNumber) {
        this.maxNumber = maxNumber;
        tvNumber.setText("0/" + maxNumber);
    }

    /**
     * 设置输入提示文字
     */
    public void setHint(String text) {
        messageTextView.setHint(text);
    }

    /**
     * 设置按钮的文字  默认为：发送
     */
    public void setBtnText(String text) {
        tvSend.setText(text);
    }

    private void init() {
        setContentView(R.layout.dialog_input_text_msg);
        messageTextView = (EditText) findViewById(R.id.et_input_message);
        tvNumber = (TextView) findViewById(R.id.tv_test);
        tvSend = (TextView)findViewById(R.id.tv_send);
        tvCancel = (TextView)findViewById(R.id.tv_cancel);
        final RelativeLayout rldlgview = (RelativeLayout) findViewById(R.id.rl_inputdlg_view);
        llVoiceInput = (LinearLayout)findViewById(R.id.ll_voice_input);


        //长按监听，语音转文字
        llVoiceInput.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(mContext, "ok", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        //剩余可输入字符
        messageTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override

            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                tvNumber.setText(maxNumber - editable.length() + "" );
                messageTextView.setFilters(new InputFilter[]{new InputFilter.LengthFilter(100)});
                /*
                int selectionStart = messageTextView.getSelectionStart();
                int selectionEnd = messageTextView.getSelectionEnd();
                if(messageTextView.length() > maxNumber){
                    //截取字符串
                    editable.delete(selectionStart - 1, selectionEnd);
                    messageTextView.setText(editable.toString());
                    int selection = editable.length();
                    //光标回到末尾
                    messageTextView.setSelection(selection);
                }
                */
            }

        });

        imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        //发布button监听，无字符时不能发送
        tvSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = messageTextView.getText().toString();
                //if (!TextUtils.isEmpty(msg)) {
                    mOnTextSendListener.onTextSend(msg);
                    imm.showSoftInput(messageTextView, InputMethodManager.SHOW_FORCED);
                    imm.hideSoftInputFromWindow(messageTextView.getWindowToken(), 0);
                    messageTextView.setText("");
                    dismiss();
               // } else {
                //    Toast.makeText(mContext, "请输入文字", Toast.LENGTH_LONG).show();
                //}
                messageTextView.setText(null);
            }
        });
        //取消button监听
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = messageTextView.getText().toString();
                mOnTextCancelListener.onTextCancel(msg);
                messageTextView.setText("");
                dismiss();
                messageTextView.setText(null);
            }
        });

        messageTextView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                switch (actionId) {
                    case KeyEvent.KEYCODE_ENDCALL:
                    case KeyEvent.KEYCODE_ENTER:
                        if (messageTextView.getText().length() > 0) {
                            imm.hideSoftInputFromWindow(messageTextView.getWindowToken(), 0);
                            dismiss();
                        } else {
                            Toast.makeText(mContext, "请输入文字", Toast.LENGTH_LONG).show();
                        }
                        return true;
                    case KeyEvent.KEYCODE_BACK:
                        dismiss();
                        return false;
                    default:
                        return false;
                }
            }
        });

        messageTextView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                //Log.d("My test", "onKey " + keyEvent.getCharacters());
                return false;
            }
        });

        rlDlg = findViewById(R.id.rl_outside_view);
        /*
        rlDlg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() != R.id.rl_inputdlg_view)
                    dismiss();
            }
        });
        */

        //判断系统键盘长度
        rldlgview.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View view, int i, int i1, int i2, int i3, int i4, int i5, int i6, int i7) {
                Rect r = new Rect();
                //获取当前界面可视部分
                getWindow().getDecorView().getWindowVisibleDisplayFrame(r);
                //获取屏幕的高度
                int screenHeight = getWindow().getDecorView().getRootView().getHeight();
                //此处就是用来获取键盘的高度的， 在键盘没有弹出的时候 此高度为0 键盘弹出的时候为一个正数
                int heightDifference = screenHeight - r.bottom;

                if (heightDifference <= 0 && mLastDiff > 0) {
                    dismiss();
                }
                mLastDiff = heightDifference;
            }
        });

        /*
        rldlgview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imm.hideSoftInputFromWindow(messageTextView.getWindowToken(), 0);
                dismiss();
            }
        });
        */

        setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialogInterface, int keyCode, KeyEvent keyEvent) {
                if (keyCode == KeyEvent.KEYCODE_BACK && keyEvent.getRepeatCount() == 0)
                    dismiss();
                return false;
            }
        });

    }
    //设置布局
    private void setLayout() {
        getWindow().setGravity(Gravity.BOTTOM);
        //WindowManager m = getWindow().getWindowManager();
        //Display d = m.getDefaultDisplay();
        WindowManager.LayoutParams p = getWindow().getAttributes();
        p.width = WindowManager.LayoutParams.MATCH_PARENT;
        p.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(p);
        setCancelable(true);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

    public void setmOnTextSendListener(OnTextSendListener onTextSendListener) {
        this.mOnTextSendListener = onTextSendListener;
    }

    public void setmOnTextCancelListener(OnTextCancelListener onTextCancelListener) {
        this.mOnTextCancelListener = onTextCancelListener;
    }

    @Override
    public void dismiss() {
        super.dismiss();
        //dismiss之前重置mLastDiff值避免下次无法打开
        mLastDiff = 0;
    }

    @Override
    public void show() {
        super.show();
    }

    public void show(String msg) {
        super.show();
        messageTextView.setText(msg);
        messageTextView.setSelection(msg.length());
    }
}