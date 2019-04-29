package com.westar.masseswork_98.mvp.modle;

import com.westar.been.AddressNode;
import com.westar.library_base.http.ObserverManager;
import com.westar.library_base.http.been.HttpRequest;
import com.westar.library_base.http.been.HttpResult;
import com.westar.masseswork_98.mvp.contract.ChoiceAddressContract;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

/**
 * Created by ZWP on 2019/4/28 15:54.
 * 描述：
 */
public class ChoiceAddressModle implements ChoiceAddressContract.Modle {
    @Override
    public Observable<HttpResult<List<AddressNode>>> getAddressList(HttpRequest httpRequest) {
        HttpResult<List<AddressNode>> httpResult = new HttpResult<>();
        List<AddressNode> addressNodes = new ArrayList<>();
        addressNodes.add(new AddressNode().setNodeId(0).setParentId(-1).setNodeName("成都市"));
        for (int i = 0; i < 10; i++) {
            addressNodes.add(new AddressNode().setNodeId(0).setParentId(-1).setNodeName(i + "区"));
        }
        for (int i = 0; i < 16; i++) {
            addressNodes.add(new AddressNode().setNodeId(1).setParentId(0).setNodeName(i + "镇"));
        }
        for (int i = 0; i < 20; i++) {
            addressNodes.add(new AddressNode().setNodeId(2).setParentId(1).setNodeName(i + "街道"));
        }

        httpResult.setCode(ObserverManager.SUCCEED_CODE);
        httpResult.setData(addressNodes);
        return Observable.just(httpResult);
    }
}
