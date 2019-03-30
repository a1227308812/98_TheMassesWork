package com.westar.library_base.preview.helper;

import android.util.Log;

import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadListener;
import com.liulishuo.filedownloader.FileDownloader;

/**
 * Created by luoyz on 2019/2/20 15:45
 * Version : 1.0
 * Last update by luoyz on 2019/2/20 15:45
 * Describe : 下载辅助工具
 */

public class DownloadHelper {
    private static final String TAG = "TBS";

    public static void download(String downloadUrl, String savePath, final DownloadCallBack callBack) {
        FileDownloader.getImpl().create(downloadUrl)
                .setPath(savePath)
                .setListener(new FileDownloadListener() {
                    @Override
                    protected void pending(BaseDownloadTask task, int soFarBytes, int totalBytes) {

                    }

                    @Override
                    protected void progress(BaseDownloadTask task, int soFarBytes, int totalBytes) {

                    }

                    @Override
                    protected void completed(BaseDownloadTask task) {
                        callBack.completed(task);
                        Log.i(TAG, "completed: ");
                    }

                    @Override
                    protected void paused(BaseDownloadTask task, int soFarBytes, int totalBytes) {

                    }

                    @Override
                    protected void error(BaseDownloadTask task, Throwable e) {
                        Log.i(TAG, "error: " + e.getMessage());
                        callBack.error();
                    }

                    @Override
                    protected void warn(BaseDownloadTask task) {

                    }
                }).start();
    }
}
