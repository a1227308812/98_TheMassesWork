package com.westar.masseswork_98.mvp.contract;

import com.westar.library_base.base.BaseView;
import com.westar.library_base.http.been.HttpRequest;
import com.westar.library_base.http.been.HttpResult;
import com.westar.masseswork_98.been.AboutMe;

import io.reactivex.Observable;

/**
 * Created by ZWP on 2019/5/8 10:04.
 * 描述：
 */
public class AboutMeContract {
    public interface Module {
        Observable<HttpResult<AboutMe>> getAboutMe(HttpRequest httpRequest);
    }

    public interface View extends BaseView {
        
    }

    public interface Presenter {

        void getAboutMe(HttpRequest httpRequest);
    }
}
