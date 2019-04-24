package com.westar.masseswork_98.mvp.contract;

import com.westar.library_base.base.BaseView;
import com.westar.library_base.http.been.HttpRequest;
import com.westar.library_base.http.been.HttpResult;
import com.westar.masseswork_98.been.AccountInfo;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by ZWP on 2019/4/24 11:51.
 * 描述：办事大厅控制器
 */
public class OfficeHallContract {
    public interface Module {
        Observable<HttpResult<AccountInfo>> getBaseData(HttpRequest httpRequest);

        Observable<HttpResult<List<String>>> getBannerData(HttpRequest httpRequest);

        Observable<HttpResult<List<String>>> getHotData(HttpRequest httpRequest);
    }

    public interface View extends BaseView {
        /**
         * 基础数据返回
         *
         * @param data
         */
        void getBaseData(AccountInfo data);

        /**
         * banner数据返回
         *
         * @param data
         */
        void getBannerData(List<String> data);

        /**
         * 热点数据返回
         *
         * @param data
         */
        void getHotData(List<String> data);
    }

    public interface Presenter {
        void getBaseData(HttpRequest httpRequest);

        void getBannerData(HttpRequest httpRequest);

        void getHotData(HttpRequest httpRequest);

        boolean initDataIsAllFinish();
    }
}
