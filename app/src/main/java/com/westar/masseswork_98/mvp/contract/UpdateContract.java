package com.westar.masseswork_98.mvp.contract;


import com.westar.masseswork_98.library_base.base.BaseView;
import com.westar.masseswork_98.library_base.http.been.HttpRequest;
import com.westar.masseswork_98.library_base.http.been.HttpResult;

import io.reactivex.Observable;

/**
 * Created by ZWP on 2019/3/12.
 * 描述：
 */
public interface UpdateContract {
    /**
     * 数据获取接口
     */
    interface modle {
        Observable<HttpResult> update(HttpRequest httpRequest);
    }

    /**
     * 视图交互接口  可在这里添加非公用交互接口的其他交互接口
     */
    interface View extends BaseView {
        // TODO: 2019/3/12 add new function
    }

    /**
     * 具体交互执行接口
     */
    interface presenter {
        void update(HttpRequest httpRequest);
    }
}
