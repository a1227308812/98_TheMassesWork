package com.westar.masseswork_98.mvp.contract;

import com.westar.library_base.base.BaseView;
import com.westar.library_base.http.been.HttpRequest;
import com.westar.library_base.http.been.HttpResult;
import com.westar.masseswork_98.been.MeCardInfo;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by ZWP on 2019/5/9 11:04.
 * 描述：
 */
public class CardListContract {
    public interface Module {
        Observable<HttpResult<List<MeCardInfo>>> cardList(HttpRequest httpRequest);
    }

    public interface View extends BaseView {

    }

    public interface Presenter {
        void cardList(HttpRequest httpRequest);
    }
}
