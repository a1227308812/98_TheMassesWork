package com.westar.masseswork_98.library_base;

import android.app.Application;
import android.content.Context;

import com.westar.masseswork_98.library_base.utils.LLog;


/**
 * Created by ZWP on 2019/3/1.
 * 描述：
 */
public class BaseApplication extends Application {
    private static BaseApplication sInstance;

    public static Context context;

    public static BaseApplication getIns() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        context = this.getApplicationContext();
        //只有debug模式才会打印日志
        LLog.init(context);
    }

}
