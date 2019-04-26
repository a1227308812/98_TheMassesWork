package com.westar.masseswork_98.mvp.contract;

import com.westar.library_base.base.BaseView;
import com.westar.library_base.http.been.HttpRequest;
import com.westar.library_base.http.been.HttpResult;
import com.westar.masseswork_98.been.NewsInformationTabs;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by ZWP on 2019/4/24 20:17.
 * 描述：新闻资讯控制器
 */
public class NewsInformationContract {
    public interface Module {
        Observable<HttpResult<List<NewsInformationTabs>>> getTabData(HttpRequest httpRequest);
    }

    public interface View extends BaseView {

    }

    public interface Presenter {
        void getTabData(HttpRequest httpRequest);
    }
}
