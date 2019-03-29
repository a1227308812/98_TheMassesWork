package com.westar.masseswork_98;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.jakewharton.rxbinding2.view.RxView;
import com.westar.been.TestBeen2;
import com.westar.been.TestBeen3;
import com.westar.library_base.base.BaseActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.functions.Consumer;

@Route(path = "/app/MainActivity")
public class MainActivity extends BaseActivity {

    TextView textView;
    Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected void initView() {
        button = findViewById(R.id.button);
        textView = findViewById(R.id.textView);
        addSubscribe(RxView.clicks(button)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        //调整到更新activity
//                        // 1. 应用内简单的跳转(通过URL跳转在'进阶用法'中)
//                        ARouter.getInstance().build("/module_update/UpdateActivity").navigation();
                        ArrayList<String> strings = new ArrayList<>();
                        for (int i = 0; i < 10; i++) {
                            strings.add(String.valueOf(i));
                        }

                        TestBeen2 testBeen2 = new TestBeen2("Jack", "22", "男");
                        ArrayList<TestBeen2> beenList2 = new ArrayList<>();
                        Map<String, List<TestBeen2>> map = new HashMap<>();
                        beenList2.add(testBeen2);
                        map.put("beenList2", beenList2);

                        TestBeen3 testBeen3 = new TestBeen3("Jack", "22", "男");

                        // 2. 跳转并携带参数
                        ARouter.getInstance().build("/module_update/UpdateActivity")
                                .withLong("ll", 666L)
                                .withString("ss", "888")
                                .withInt("aa", 777)
                                .withBoolean("bb", true)
                                .withDouble("cc", 100)
                                .withFloat("dd", 200f)
                                .withStringArrayList("ee", strings)
                                .withSerializable("tb3", testBeen3)
                                .withObject("tb1", beenList2)
                                .withObject("tb2", map)
                                .navigation();
                    }
                }));
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_toolbar;
    }
}
