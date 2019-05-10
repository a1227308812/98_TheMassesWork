package com.westar.masseswork_98.mvp.modle;

import com.westar.library_base.http.ObserverManager;
import com.westar.library_base.http.been.HttpRequest;
import com.westar.library_base.http.been.HttpResult;
import com.westar.masseswork_98.been.AddressInfo;
import com.westar.masseswork_98.mvp.contract.AddressManagerContract;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

/**
 * Created by ZWP on 2019/5/9 16:02.
 * 描述：
 */
public class AddressManagerModle implements AddressManagerContract.Module {
    @Override
    public Observable<HttpResult<List<AddressInfo>>> getAddressList(HttpRequest httpRequest) {
        List<AddressInfo> addressInfoList = new ArrayList<>();
        addressInfoList.add(new AddressInfo()
                .setName("刘先生")
                .setAddress("四川省成都市武侯区科园三路火炬时代B区")
                .setTel("18844687923")
                .setSelected(true));
        addressInfoList.add(new AddressInfo()
                .setName("小贱贱")
                .setAddress("贱贱省贱贱市贱贱区贱贱街贱贱楼")
                .setTel("233333333333"));
        addressInfoList.add(new AddressInfo()
                .setName("雅蠛蝶")
                .setAddress("日本省东京市xxxxxxxx")
                .setTel("22222222222"));

        HttpResult<List<AddressInfo>> httpResult = new HttpResult<>();
        httpResult.setCode(ObserverManager.SUCCEED_CODE);
        httpResult.setData(addressInfoList);
        return Observable.just(httpResult);
    }
}
