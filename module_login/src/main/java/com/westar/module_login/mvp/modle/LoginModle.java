package com.westar.module_login.mvp.modle;

import com.blankj.utilcode.util.GsonUtils;
import com.westar.masseswork_98.library_base.http.been.HttpRequest;
import com.westar.masseswork_98.library_base.http.been.HttpResult;
import com.westar.module_login.been.User;
import com.westar.module_login.http.LoginClient;
import com.westar.module_login.mvp.contract.LoginContract;

import io.reactivex.Observable;

/**
 * Created by ZWP on 2019/3/25 16:55.
 * 描述：
 */
public class LoginModle implements LoginContract.modle {
    @Override
    public Observable<HttpResult<User>> login(HttpRequest httpRequest) {
        String httpRequestJson = GsonUtils.toJson(httpRequest);
        return LoginClient.getInstance().creatAPI().login(httpRequestJson);
    }
}
