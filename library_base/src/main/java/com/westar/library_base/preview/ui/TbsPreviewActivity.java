package com.westar.library_base.preview.ui;

import android.graphics.Color;
import android.os.Build;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.liulishuo.filedownloader.BaseDownloadTask;
import com.tencent.smtt.sdk.TbsReaderView;
import com.westar.library_base.preview.bean.GlobalBean;
import com.westar.library_base.preview.helper.DownloadCallBack;
import com.westar.library_base.preview.helper.DownloadHelper;
import com.westar.masseswork_98.library_base.R;

import java.io.File;

/**
 * TBS预览文件
 */
public class TbsPreviewActivity extends AppCompatActivity {
    private LinearLayout llTbsLayout;
    private String mUrl;
    private String mFileType;
    private String mFileName;
    private String mUuid;
    private TbsReaderView mTbsReaderView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tbs_preview);
        llTbsLayout = findViewById(R.id.llTbsLayout);
        //设置透明状态栏
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        init();
    }

    private void init() {
        mUrl = getIntent().getStringExtra(GlobalBean.KEY_FILE_URL);
        mFileType = getIntent().getStringExtra(GlobalBean.KEY_FILE_TYPE);
        mFileName = getIntent().getStringExtra(GlobalBean.KEY_FILE_NAME);
        mUuid = getIntent().getStringExtra(GlobalBean.KEY_FILE_UUID);

        TbsReaderView.ReaderCallback readerCallback = new TbsReaderView.ReaderCallback() {
            @Override
            public void onCallBackAction(Integer integer, Object o, Object o1) {

            }
        };
        //实例化TbsReaderView，然后将它装入我们准备的容器
        mTbsReaderView = new TbsReaderView(this, readerCallback);
        llTbsLayout.addView(mTbsReaderView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        String url = mUrl;
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/腾讯文件TBS/";
        if (mUuid != null && !"".equals(mUuid)) {
            path = path + mUuid + mFileName;
        } else {
            path = path + mFileName;
        }
        File file = new File(path);
        if (file.exists()) {
            openFile(path);
        } else {
            downloadFile(url, path);
        }
    }

    /**
     * 下载附件
     *
     * @param url
     * @param path
     */
    private void downloadFile(String url, final String path) {
        Log.e("TBS", "下载路径" + path);
        DownloadHelper.download(url, path, new DownloadCallBack() {
            @Override
            public void completed(BaseDownloadTask task) {
                openFile(path);
            }

            @Override
            public void error() {
            }
        });
    }

    /**
     * TBS打开本地附件
     *
     * @param path
     */
    private void openFile(String path) {
        Bundle bundle = new Bundle();
        //文件路径
        bundle.putString("filePath", path);
        Log.i("TBS", "openFile: " + path);
        //临时文件的路径，必须设置，否则会报错
        bundle.putString("tempPath", Environment.getExternalStorageDirectory().getAbsolutePath() + "/腾讯文件TBS");
        if (!"".equals(mFileType)) {
            //准备
            boolean result = mTbsReaderView.preOpen(mFileType.toLowerCase(), false);
            if (result) {
                //预览文件
                mTbsReaderView.openFile(bundle);
            } else {
                Toast.makeText(this, "该文件不支持在线预览", Toast.LENGTH_SHORT).show();
                Log.i("TBS", "openFile: " + "不支持");
            }
        } else {
            //预览文件
            mTbsReaderView.openFile(bundle);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mTbsReaderView != null) {
            mTbsReaderView.onStop();
        }
    }
}
