package com.westar.masseswork_98.mvp.contract;

import com.westar.been.LocationNode;
import com.westar.been.LocationResult;
import com.westar.library_base.base.BaseView;
import com.westar.library_base.http.been.HttpRequest;
import com.westar.library_base.http.been.HttpResult;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by ZWP on 2019/5/6 16:12.
 * 描述：个人信息页面的控制器
 */
public class PersonalInformationContract {
    public interface Module {
        Observable<HttpResult<List<LocationNode>>> getServiceAddressData(HttpRequest httpRequest);

        /**
         * 更新个人信息
         *
         * @param httpRequest
         * @return
         */
        Observable<HttpResult<String>> updatePersonalInfo(HttpRequest httpRequest);
    }

    public interface View extends BaseView {
        void updatePersonalResult(String s);
    }

    public interface Presenter {
        void getServiceAddressData(HttpRequest httpRequest);

        void updatePersonalInfo(HttpRequest httpRequest);

    }
}
