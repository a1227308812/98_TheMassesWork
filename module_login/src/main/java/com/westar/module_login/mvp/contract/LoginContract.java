package com.westar.module_login.mvp.contract;


import com.westar.masseswork_98.library_base.base.BaseView;
import com.westar.masseswork_98.library_base.http.been.HttpRequest;
import com.westar.masseswork_98.library_base.http.been.HttpResult;
import com.westar.module_login.been.User;

import io.reactivex.Observable;

/**
 * Created by ZWP on 2019/3/12.
 * 描述：
 */
public interface LoginContract {
    /**
     * 数据获取接口
     */
    interface modle {
        Observable<HttpResult<User>> login(HttpRequest httpRequest);
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
        /**
         * 请求更新
         *
         * @param httpRequest
         */
        void login(HttpRequest httpRequest);
    }
}
