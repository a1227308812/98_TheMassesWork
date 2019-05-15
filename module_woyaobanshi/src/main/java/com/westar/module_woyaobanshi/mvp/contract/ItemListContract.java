package com.westar.module_woyaobanshi.mvp.contract;

import com.westar.been.ItemInfo;
import com.westar.library_base.base.BaseView;
import com.westar.library_base.http.been.HttpRequest;
import com.westar.library_base.http.been.HttpResult;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by ZWP on 2019/5/15 10:19.
 * 描述：
 */
public class ItemListContract {
    public interface Modle {
        Observable<HttpResult<List<ItemInfo>>> getItemList(HttpRequest httpRequest);
    }

    public interface View extends BaseView {

    }

    public interface Presenter {
        void getItemList(HttpRequest httpRequest);
    }
}
