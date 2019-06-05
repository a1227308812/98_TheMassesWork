package com.westar.module_woyaobanshi.mvp.contract;

import com.westar.library_base.base.BaseView;
import com.westar.library_base.http.been.HttpRequest;
import com.westar.library_base.http.been.HttpResult;

import io.reactivex.Observable;

/**
 * Created by ZWP on 2019/5/30 14:15.
 * 描述：
 */
public class ItemDetailContract {
    public interface Modle {
        Observable<HttpResult<String>> getData(HttpRequest httpRequest);
    }

    public interface View extends BaseView {

    }

    public interface Presenter {
        void getData(HttpRequest httpRequest);
    }
}
