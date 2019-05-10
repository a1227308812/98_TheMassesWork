package com.westar.masseswork_98.mvp.contract;

import com.westar.library_base.base.BaseView;
import com.westar.library_base.http.been.HttpRequest;
import com.westar.library_base.http.been.HttpResult;
import com.westar.masseswork_98.been.AddressInfo;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by ZWP on 2019/5/9 15:55.
 * 描述：
 */
public class AddressManagerContract {
    public interface Module {
        Observable<HttpResult<List<AddressInfo>>> getAddressList(HttpRequest httpRequest);
    }

    public interface View extends BaseView {

    }

    public interface Presenter {
        void getAddressList(HttpRequest httpRequest);
    }

}
