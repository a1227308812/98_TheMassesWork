package com.westar.library_base.utils;

import android.content.Context;
import android.view.View;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.contrarywind.interfaces.IPickerViewData;
import com.uuzuche.lib_zxing.DisplayUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by ZWP on 2019/5/5 17:08.
 * 描述：选择时间或者地址的帮助类
 */
public class PickerUtil {

    public enum PickerType {
        // 选择模式，年月日时分，年月日，年月日时 , 时分，月日时分 ,年月
        ALL("yyyy-MM-dd HH:mm"), YEAR_MONTH_DAY("yyyy-MM-dd"), YEAR_MONTH_DAY_HOUR("yyyy-MM-dd HH"), HOURS_MINS("HH:mm"), MONTH_DAY_HOUR_MIN("MM-dd HH:mm"), YEAR_MONTH("yyyy-MM");
        private String value;

        PickerType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    /**
     * 时间选择回调
     */
    public interface TimerPickerCallBack {
        void onTimeSelect(String date);
    }

    /**
     * 弹出时间选择
     *
     * @param context
     * @param pickerType TimerPickerView 中定义的 选择时间类型
     * @param callBack   时间选择回调
     */
    public static void alertTimerPicker(Context context, final PickerType pickerType, final TimerPickerCallBack callBack) {

        //时间选择器
        TimePickerBuilder builder = new TimePickerBuilder(context, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                SimpleDateFormat sdf = new SimpleDateFormat(pickerType.getValue(), Locale.US);
                callBack.onTimeSelect(sdf.format(date));
            }
        });
        // pvTime.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
        builder.setTitleText("请选择出生日期");
        builder.setContentTextSize(DisplayUtil.dip2px(context, 14));
        builder.build().show();
    }


    /**
     * 底部滚轮点击事件回调 单项选择
     */
    public interface OnGenderPickerLisenter {
        void onClick(View view, int postion, Gender gender);
    }

    /**
     * 性别选择
     *
     * @param context
     */
    public static void alertGenderPicker(Context context, final OnGenderPickerLisenter pickerLisenter) {
        final ArrayList<Gender> list = new ArrayList<>();
        list.add(new Gender().setTitle("男"));
        list.add(new Gender().setTitle("女"));

        OptionsPickerBuilder builder = new OptionsPickerBuilder(context, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                pickerLisenter.onClick(v, options1, list.get(options1));
            }
        });
        builder.setTitleText("请选择性别")
                .setOutSideCancelable(true)
                .setCyclic(false, false, false)
                .build();

        OptionsPickerView pvOptions = builder.build();
        pvOptions.setKeyBackCancelable(true);
        pvOptions.setPicker(list);
        pvOptions.show();
    }

    public static class Gender implements IPickerViewData {
        String title;

        public String getTitle() {
            return title;
        }

        public Gender setTitle(String title) {
            this.title = title;
            return this;
        }

        @Override
        public String getPickerViewText() {
            return this.title;
        }
    }

}