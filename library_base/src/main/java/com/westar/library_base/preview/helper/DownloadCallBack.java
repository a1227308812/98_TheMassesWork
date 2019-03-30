package com.westar.library_base.preview.helper;

import com.liulishuo.filedownloader.BaseDownloadTask;

/**
 * 下载监听
 *
 * @author luoyz
 * @time 2018/11/16.
 */

public interface DownloadCallBack {

    /**
     * 下载完成
     *
     * @param task
     */
    void completed(BaseDownloadTask task);

    void error();
}
