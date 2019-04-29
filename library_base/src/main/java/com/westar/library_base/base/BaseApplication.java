package com.westar.library_base.base;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.AppUtils;
import com.coorchice.library.ImageEngine;
import com.liulishuo.filedownloader.FileDownloader;
import com.tencent.smtt.sdk.QbSdk;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;
import com.westar.library_base.supertext.GlideEngine;
import com.westar.library_base.utils.AppUtil;
import com.westar.library_base.utils.Density;
import com.westar.library_base.utils.LLog;
import com.westar.library_base.utils.ScreenAdapter;

import io.realm.Realm;
import io.realm.RealmConfiguration;


/**
 * Created by ZWP on 2019/3/1.
 * 描述：application 超类
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

        if (AppUtils.isAppDebug()) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this); // 尽可能早，推荐在Application中初始化


        //初始化数据库
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name(AppUtils.getAppName() + ".realm")
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);


        //x5内核初始化接口
        QbSdk.initX5Environment(getApplicationContext(), cb);
        //初始化下载组件
        FileDownloader.setup(getApplicationContext());
        //二维码扫描
        ZXingLibrary.initDisplayOpinion(this);

        //头条适配方案
        ScreenAdapter.setup(this);
        ScreenAdapter.register(this, 400f, ScreenAdapter.MATCH_BASE_WIDTH, ScreenAdapter.MATCH_UNIT_DP);

        // 安装图片引擎
        ImageEngine.install(new GlideEngine(this));
    }

    /**
     * 搜集本地tbs内核信息并上报服务器，服务器返回结果决定使用哪个内核。
     */
    QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {

        @Override
        public void onViewInitFinished(boolean arg0) {
            //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
            Log.e("TBSPlugin", " onViewInitFinished is " + arg0);
        }

        @Override
        public void onCoreInitFinished() {
            Log.e("TBSPlugin", " onCoreInitFinished");
        }
    };

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        //ARouter 解绑
        ARouter.getInstance().destroy();
    }
}
