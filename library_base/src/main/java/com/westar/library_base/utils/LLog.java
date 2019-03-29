package com.westar.library_base.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.util.Log;

/**
 * Created by luoyz on 2019/2/26 10:43
 * Version : 1.0
 * Last update by luoyz on 2019/2/26 10:43
 * Describe : 自定义 Log工具
 */

public class LLog {
    private static final String TAG = "_LLog";

    private static boolean IS_DEBUG = false;

    /**
     * 初始化工具
     *
     * @param context
     */
    public static void init(Context context) {
        //只有debug模式才会打印日志
        IS_DEBUG = isApkInDebug(context);
    }

    public static void v(String tag, String msg) {
        if (IS_DEBUG) {
            Log.v(tag + TAG, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (IS_DEBUG) {
            Log.i(tag + TAG, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (IS_DEBUG) {
            Log.e(tag + TAG, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (IS_DEBUG) {
            Log.d(tag + TAG, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (IS_DEBUG) {
            Log.w(tag + TAG, msg);
        }
    }

    public static void a(String tag, String msg) {
        if (IS_DEBUG) {
            Log.wtf(tag + TAG, msg);
        }
    }

    /**
     * 判断是否在 dbug模式下
     *
     * @param context
     * @return
     */
    private static boolean isApkInDebug(Context context) {
        try {
            ApplicationInfo info = context.getApplicationInfo();
            return (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        } catch (Exception e) {
            return false;
        }
    }
}