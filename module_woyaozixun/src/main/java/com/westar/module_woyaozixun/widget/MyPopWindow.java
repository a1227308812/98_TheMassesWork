package com.westar.module_woyaozixun.widget;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.westar.module_woyaozixun.R;
import com.westar.module_woyaozixun.activity.LiuYanZiXunActivity;

import java.util.logging.Handler;

import static android.support.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread;

public class MyPopWindow extends PopupWindow {
    private Context context;
    private View view;
    private LinearLayout llZixunLeaveMessage;
    private LinearLayout llZixunPhone;

    public MyPopWindow(final Context context) {
        this(context, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        setFocusable(true);
        setOutsideTouchable(true);
        setTouchable(true);
        setBackgroundDrawable(new BitmapDrawable());
        view = LayoutInflater.from(context).inflate(R.layout.layout_my_popwindow,null);
        setContentView(view);
        llZixunLeaveMessage = (LinearLayout) view.findViewById(R.id.ll_zixun_leave_message);
        llZixunPhone = (LinearLayout) view.findViewById(R.id.ll_zixun_phone);

        llZixunLeaveMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(context, LiuYanZiXunActivity.class);
                        context.startActivity(intent);
                    }
                }).start();
//                Intent intent = new Intent(context, LiuYanZiXunActivity.class);
//                context.startActivity(intent);
            }
        });
        llZixunPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "电话咨询", Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });
    }

    public MyPopWindow(final Context context, int width, int height) {
        super(context);
        this.context = context;
        setWidth(width);
        setHeight(height);
        setFocusable(true);
        setOutsideTouchable(true);
        setTouchable(true);
        setBackgroundDrawable(new BitmapDrawable());
        view = LayoutInflater.from(context).inflate(R.layout.layout_my_popwindow,null);
        setContentView(view);
        llZixunLeaveMessage = (LinearLayout) view.findViewById(R.id.ll_zixun_leave_message);
        llZixunPhone = (LinearLayout) view.findViewById(R.id.ll_zixun_phone);

    }


}

