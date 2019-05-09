package com.westar.masseswork_98.mvp.modle;

import com.westar.library_base.http.ObserverManager;
import com.westar.library_base.http.been.HttpRequest;
import com.westar.library_base.http.been.HttpResult;
import com.westar.masseswork_98.been.MeCardInfo;
import com.westar.masseswork_98.mvp.contract.CardListContract;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

/**
 * Created by ZWP on 2019/5/9 11:07.
 * 描述：
 */
public class CardListModle implements CardListContract.Module {

    @Override
    public Observable<HttpResult<List<MeCardInfo>>> cardList(HttpRequest httpRequest) {
        List<MeCardInfo> cardInfoList = new ArrayList<>();

        cardInfoList.add(new MeCardInfo()
                .setTitle("电子身份证明")
                .setDescrible("身份证")
                .setDescrible2("511300199911223344")
                .setAuthenticationStatus("已认证")
                .setQRCodeUrl("已认证")
                .setTypeUrl("http://image.baidu.com/search/detail?ct=503316480&z=&tn=baiduimagedetail&ipn=d&word=%E5%B0%8F%E5%9B%BE%E7%89%87&step_word=&ie=utf-8&in=&cl=2&lm=-1&st=-1&hd=&latest=&copyright=&cs=158094193,417757867&os=1574650139,1035232813&simid=3400835474,357383001" +
                        "&pn=12&rn=1&di=78210&ln=859&fr=&fmq=1556616716009_R&ic=&s=undefined&se=&sme=&tab=0&width=&height=&face=undefined&is=0," +
                        "0&istype=2&ist=&jit=&bdtype=0&spn=0&pi=0&gsm=0&objurl=http%3A%2F%2Fimg.alicdn.com%2Fimgextra%2Fi2%2F1134343704%2FTB2D" +
                        ".RZp3JlpuFjSspjXXcT.pXa_%2521%25211134343704.gif&rpstart=0&rpnum=0&adpicid=0&force=undefined"));
        cardInfoList.add(new MeCardInfo()
                .setTitle("社会保障卡")
                .setDescrible("成都市人力资源和社会保障厅")
                .setDescrible2("社保查询、医保支付")
                .setAuthenticationStatus("未关联")
                .setQRCodeUrl("已认证")
                .setTypeUrl("http://img0.imgtn.bdimg.com/it/u=1111126966,1527244034&fm=26&gp=0.jpg"));
        cardInfoList.add(new MeCardInfo()
                .setTitle("驾驶证")
                .setDescrible("成都市交通管理局")
                .setDescrible2("违章缴费，到期换证")
                .setAuthenticationStatus("已认证")
                .setQRCodeUrl("已认证")
                .setTypeUrl("http://img3.imgtn.bdimg.com/it/u=1736640203,2595736136&fm=26&gp=0.jpg"));
        cardInfoList.add(new MeCardInfo()
                .setTitle("健康证")
                .setDescrible("成都市医疗管理局")
                .setDescrible2("医疗缴费，到期换证")
                .setAuthenticationStatus("已认证")
                .setQRCodeUrl("已认证")
                .setTypeUrl("http://img3.imgtn.bdimg.com/it/u=1736640203,2595736136&fm=26&gp=0.jpg"));
        cardInfoList.add(new MeCardInfo()
                .setTitle("良民证")
                .setDescrible("成都市良民管理局")
                .setDescrible2("是不是良民，此证证明")
                .setAuthenticationStatus("已认证")
                .setQRCodeUrl("已认证")
                .setTypeUrl("http://img3.imgtn.bdimg.com/it/u=1736640203,2595736136&fm=26&gp=0.jpg"));

        HttpResult<List<MeCardInfo>> httpResult = new HttpResult<>();
        httpResult.setData(cardInfoList);
        httpResult.setCode(ObserverManager.SUCCEED_CODE);
        return Observable.just(httpResult);
    }
}
