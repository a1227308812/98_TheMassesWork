package com.westar.masseswork_98.mvp.modle;

import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.ResourceUtils;
import com.google.gson.reflect.TypeToken;
import com.westar.been.LocationNode;
import com.westar.been.LocationResult;
import com.westar.library_base.http.ObserverManager;
import com.westar.library_base.http.been.HttpRequest;
import com.westar.library_base.http.been.HttpResult;
import com.westar.masseswork_98.mvp.contract.PersonalInformationContract;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by ZWP on 2019/5/6 16:15.
 * 描述：
 */
public class PersonalInformationModle implements PersonalInformationContract.Module {

    @Override
    public Observable<HttpResult<List<LocationNode>>> getServiceAddressData(HttpRequest httpRequest) {
        //解析本地的全国城市数据并返回
        String locationData = ResourceUtils.readAssets2String("pcas-code.json");
        List<LocationNode> locationNodeList = GsonUtils.fromJson(locationData, new TypeToken<List<LocationNode>>() {
        }.getType());

        HttpResult<List<LocationNode>> httpResult = new HttpResult<>();
        httpResult.setData(locationNodeList);
        httpResult.setCode(ObserverManager.SUCCEED_CODE);
        return Observable.just(httpResult);
    }

    @Override
    public Observable<HttpResult<String>> updatePersonalInfo(HttpRequest httpRequest) {
        HttpResult<String> httpResult = new HttpResult<>();
        httpResult.setData("个人信息更新成功");
        httpResult.setCode(ObserverManager.SUCCEED_CODE);
        return Observable.just(httpResult);
    }

}
