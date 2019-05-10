package com.westar.masseswork_98.mvp.modle;

import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.ResourceUtils;
import com.google.gson.reflect.TypeToken;
import com.westar.been.LocationNode;
import com.westar.been.LocationResult;
import com.westar.been.User;
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
    public Observable<HttpResult<User>> updatePersonalInfo(HttpRequest httpRequest) {
        HttpResult<User> httpResult = new HttpResult<>();
        User user = new User();
        user.setPhotoUrl("http://img4.imgtn.bdimg.com/it/u=2146046871,2611785107&fm=26&gp=0.jpg")
                .setUserName("我就是修改后的")
                .setDomicile_address("四川省南充市顺庆区北湖花园")
                .setNoticeDescribe("您好，您已成功预约2019-06-12日 10：00-11：00的号，请提前做好准备。")
                .setBirthday("1995-05-06")
                .setGender("男");
        httpResult.setData(user);
        httpResult.setCode(ObserverManager.SUCCEED_CODE);
        return Observable.just(httpResult);
    }

}
