package com.westar.masseswork_98.mvp.contract;

import com.westar.library_base.base.BaseView;
import com.westar.library_base.http.been.HttpRequest;
import com.westar.library_base.http.been.HttpResult;

import io.reactivex.Observable;

/**
 * Created by ZWP on 2019/5/7 17:04.
 * 描述：
 */
public class ChangePhoneContract {
    public interface Module {
        Observable<HttpResult<String>> getYzm(HttpRequest httpRequest);

        Observable<HttpResult<String>> checkOldPhoneNum(HttpRequest httpRequest);
    }

    public interface View extends BaseView {

        void checkOldPhoneNumResult(String result);
    }

    public interface Presenter {
        void getYzm(HttpRequest httpRequest);

        void checkOldPhoneNum(HttpRequest httpRequest);
    }
}
