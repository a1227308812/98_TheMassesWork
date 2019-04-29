package com.westar.masseswork_98.mvp.contract;

import com.westar.been.AddressNode;
import com.westar.library_base.base.BaseView;
import com.westar.library_base.http.been.HttpRequest;
import com.westar.library_base.http.been.HttpResult;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by ZWP on 2019/4/28 15:51.
 * 描述：地址选择控制器
 */
public class ChoiceAddressContract {
    public interface Modle {
        Observable<HttpResult<List<AddressNode>>> getAddressList(HttpRequest httpRequest);
    }

    public interface View extends BaseView {

    }

    public interface Presenter {
        void getAddressList(HttpRequest httpRequest);
    }
}
