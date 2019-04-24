package com.westar.masseswork_98.mvp.modle;

import com.blankj.utilcode.util.GsonUtils;
import com.westar.library_base.http.ObserverManager;
import com.westar.library_base.http.been.HttpRequest;
import com.westar.library_base.http.been.HttpResult;
import com.westar.masseswork_98.AppClient;
import com.westar.masseswork_98.been.AccountInfo;
import com.westar.masseswork_98.mvp.contract.OfficeHallContract;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

/**
 * Created by ZWP on 2019/4/24 13:24.
 * 描述：数据转换
 */
public class OfficeHallModle implements OfficeHallContract.Module {
    @Override
    public Observable<HttpResult<AccountInfo>> getBaseData(HttpRequest httpRequest) {
        //转换
        String reqJson = GsonUtils.toJson(httpRequest);
        //关联具体的api
//        return AppClient.getInstance().creatAPI().officahHellGetData(reqJson);
        //模拟数据
        AccountInfo accountInfo = new AccountInfo();
        accountInfo.setHead_portrait("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=328179059,3377101288&fm=27&gp=0.jpg");
        accountInfo.setUserName("您好，小贱贱");
        accountInfo.setNoticeDescribe("您好，您已成功预约2019-01-12日 14：00-15：00的号，请提前做好准备。");
        HttpResult<AccountInfo> stringHttpResult = new HttpResult<>();
        stringHttpResult.setCode(ObserverManager.SUCCEED_CODE);
        stringHttpResult.setMsg("");
        stringHttpResult.setData(accountInfo);
        return Observable.just(stringHttpResult);
    }

    @Override
    public Observable<HttpResult<List<String>>> getBannerData(HttpRequest httpRequest) {
        //模拟数据
        HttpResult<List<String>> stringHttpResult = new HttpResult<>();
        List<String> stringList = new ArrayList<>();
        stringList.add("http://img1.imgtn.bdimg.com/it/u=1393987749,3422146058&fm=26&gp=0.jpg");
        stringList.add("http://img4.imgtn.bdimg.com/it/u=2153937626,1074119156&fm=26&gp=0.jpg");
        stringList.add("http://img4.imgtn.bdimg.com/it/u=1134117949,2023973703&fm=26&gp=0.jpg");
        stringList.add("http://img2.imgtn.bdimg.com/it/u=1372583848,569137420&fm=26&gp=0.jpg");
        stringHttpResult.setCode(ObserverManager.SUCCEED_CODE);
        stringHttpResult.setMsg("");
        stringHttpResult.setData(stringList);
        return Observable.just(stringHttpResult);
    }

    @Override
    public Observable<HttpResult<List<String>>> getHotData(HttpRequest httpRequest) {
        //模拟数据
        HttpResult<List<String>> stringHttpResult = new HttpResult<>();
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            stringList.add("我是热点事项" + i);
        }
        stringHttpResult.setCode(ObserverManager.SUCCEED_CODE);
        stringHttpResult.setMsg("");
        stringHttpResult.setData(stringList);
        return Observable.just(stringHttpResult);
    }
}
