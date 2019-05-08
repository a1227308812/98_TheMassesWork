package com.westar.masseswork_98.mvp.modle;

import com.westar.library_base.http.ObserverManager;
import com.westar.library_base.http.been.HttpRequest;
import com.westar.library_base.http.been.HttpResult;
import com.westar.masseswork_98.been.AboutMe;
import com.westar.masseswork_98.mvp.contract.AboutMeContract;

import io.reactivex.Observable;

/**
 * Created by ZWP on 2019/5/8 10:11.
 * 描述：
 */
public class AboutMeModule implements AboutMeContract.Module {
    @Override
    public Observable<HttpResult<AboutMe>> getAboutMe(HttpRequest httpRequest) {
        HttpResult<AboutMe> httpResult = new HttpResult<>();
        httpResult.setCode(ObserverManager.SUCCEED_CODE);
        httpResult.setData(new AboutMe()
                .setLogoUrl("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=4198265759,1182354722&fm=26&gp=0.jpg")
                .setAppName("府谷县智慧政务云")
                .setAppDescrible("超轻审批，移动化办公")
                .setTelNum("028-85137418")
                .setEmail("westarsoft@gmail.com")
                .setWebSite("www.cdwestarsoft.com")
        );
        return Observable.just(httpResult);
    }
}
