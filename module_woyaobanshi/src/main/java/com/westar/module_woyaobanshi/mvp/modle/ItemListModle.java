package com.westar.module_woyaobanshi.mvp.modle;

import com.westar.been.ItemInfo;
import com.westar.library_base.http.ObserverManager;
import com.westar.library_base.http.been.HttpRequest;
import com.westar.library_base.http.been.HttpResult;
import com.westar.module_woyaobanshi.mvp.contract.ItemListContract;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import io.reactivex.Observable;

/**
 * Created by ZWP on 2019/5/15 10:21.
 * 描述：
 */
public class ItemListModle implements ItemListContract.Modle {
    @Override
    public Observable<HttpResult<List<ItemInfo>>> getItemList(HttpRequest httpRequest) {
        List<ItemInfo> itemInfos = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            itemInfos.add(new ItemInfo()
                    .setItemTitle("测试标题" + i)
                    .setTypeList(Arrays.asList("1", "2", "3", "4"))
                    .setStar(new Random().nextInt(5))
                    .setBljg("人社局")
                    .setZxdh("85236974")
                    .setFunctionList(Arrays.asList("申报", "预约", "咨询", "收藏"))
            );
        }
        HttpResult<List<ItemInfo>> httpResult = new HttpResult<>();
        httpResult.setCode(ObserverManager.SUCCEED_CODE);
        httpResult.setData(itemInfos);
        return Observable.just(httpResult);
    }
}
