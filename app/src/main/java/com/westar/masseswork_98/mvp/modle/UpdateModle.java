//package com.westar.masseswork_98.mvp.modle;
//
//
//import com.blankj.utilcode.util.GsonUtils;
//import HttpRequest;
//import com.westar.masseswork_98.mvp.contract.UpdateContract;
//
//import io.reactivex.Observable;
//
///**
// * Created by ZWP on 2019/3/12.
// * 描述：更新模块的具体网络请求实现
// */
//public class UpdateModle implements UpdateContract.modle {
//    @Override
//    public Observable<HttpResult<Version>> update(HttpRequest httpRequest) {
//        //转换
//        String reqJson = GsonUtils.toJson(httpRequest);
//        //关联具体的api
//        return AppClient.getInstance().api.update(reqJson);
//    }
//}
