package com.westar.masseswork_98.mvp.modle;

import com.westar.library_base.http.ObserverManager;
import com.westar.library_base.http.been.HttpRequest;
import com.westar.library_base.http.been.HttpResult;
import com.westar.masseswork_98.been.NewsInformationTabs;
import com.westar.masseswork_98.mvp.contract.NewsInformationContract;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

/**
 * Created by ZWP on 2019/4/24 20:26.
 * 描述：
 */
public class NewsInformationModle implements NewsInformationContract.Module {
    @Override
    public Observable<HttpResult<List<NewsInformationTabs>>> getTabData(HttpRequest httpRequest) {
        List<NewsInformationTabs> tabs = new ArrayList<>();
        NewsInformationTabs newsInformationTabs = new NewsInformationTabs();
        newsInformationTabs.setTabTitle("热点");
        newsInformationTabs.setTabType("0");
        tabs.add(newsInformationTabs);

        newsInformationTabs = new NewsInformationTabs();
        newsInformationTabs.setTabTitle("政务");
        newsInformationTabs.setTabType("1");
        tabs.add(newsInformationTabs);

        newsInformationTabs = new NewsInformationTabs();
        newsInformationTabs.setTabTitle("政策");
        newsInformationTabs.setTabType("2");
        tabs.add(newsInformationTabs);

        newsInformationTabs = new NewsInformationTabs();
        newsInformationTabs.setTabTitle("政务公告");
        newsInformationTabs.setTabType("3");
        tabs.add(newsInformationTabs);

        newsInformationTabs = new NewsInformationTabs();
        newsInformationTabs.setTabTitle("政府采购");
        newsInformationTabs.setTabType("4");
        tabs.add(newsInformationTabs);

        HttpResult<List<NewsInformationTabs>> httpResult = new HttpResult();
        httpResult.setCode(ObserverManager.SUCCEED_CODE);
        httpResult.setMsg("");
        httpResult.setData(tabs);

        return Observable.just(httpResult);
    }
}
