package com.westar.masseswork_98.mvp.contract;

import com.westar.library_base.base.BaseView;
import com.westar.library_base.http.been.HttpRequest;
import com.westar.library_base.http.been.HttpResult;
import com.westar.masseswork_98.been.GlobalSearchHot;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by ZWP on 2019/4/26 15:53.
 * 描述：搜索页面的控制器
 */
public class GlobalSearchContract {
    public interface Module {
        Observable<HttpResult<List<GlobalSearchHot>>> searchResult(HttpRequest httpRequest);
    }

    public interface View extends BaseView {

    }

    public interface Presneter {
        void searchResult(HttpRequest httpRequest);
    }
}
