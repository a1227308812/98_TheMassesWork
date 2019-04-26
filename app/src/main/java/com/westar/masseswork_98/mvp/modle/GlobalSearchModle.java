package com.westar.masseswork_98.mvp.modle;

import com.westar.library_base.http.ObserverManager;
import com.westar.library_base.http.been.HttpRequest;
import com.westar.library_base.http.been.HttpResult;
import com.westar.masseswork_98.been.GlobalSearchHistory;
import com.westar.masseswork_98.been.SearchResult;
import com.westar.masseswork_98.mvp.contract.GlobalSearchContract;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

/**
 * Created by ZWP on 2019/4/26 16:03.
 * 描述：
 */
public class GlobalSearchModle implements GlobalSearchContract.Module {
    @Override
    public Observable<HttpResult<List<GlobalSearchHistory>>> searchResult(HttpRequest httpRequest) {
        HttpResult<List<GlobalSearchHistory>> httpResult = new HttpResult<>();
        List<GlobalSearchHistory> resultList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            GlobalSearchHistory result = new GlobalSearchHistory();
            result.setTitle("搜索结果事项" + i);
            resultList.add(result);
        }
        httpResult.setData(resultList);
        httpResult.setCode(ObserverManager.SUCCEED_CODE);
        return Observable.just(httpResult);
    }
}
