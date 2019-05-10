package com.westar.masseswork_98.mvp.contract;

import com.westar.been.LocationNode;
import com.westar.library_base.base.BaseView;
import com.westar.library_base.http.been.HttpRequest;
import com.westar.library_base.http.been.HttpResult;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by ZWP on 2019/5/10 13:08.
 * 描述：
 */
public class AddAddressContract {
    public interface Mudle {
        Observable<HttpResult<String>> addAddress(HttpRequest httpRequest);

        Observable<HttpResult<List<LocationNode>>> getServiceAddressData(HttpRequest httpRequest);
    }

    public interface View extends BaseView {
        void getServiceAddressData(List<LocationNode> locationNodeList);
    }

    public interface Presenter {
        void addAddress(HttpRequest httpRequest);

        void getServiceAddressData(HttpRequest httpRequest);
    }
}
