package com.westar.been;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.service.SerializationService;
import com.blankj.utilcode.util.GsonUtils;

import java.lang.reflect.Type;

// 如果需要传递自定义对象，新建一个类（并非自定义对象类），然后实现 SerializationService,并使用@Route注解标注(方便用户自行选择序列化方式)，例如：
@Route(path = "/been/gson")
public class GsonServiceImpl implements SerializationService {
    @Override
    public void init(Context context) {

    }

    @Override
    public <T> T json2Object(String text, Class<T> clazz) {
        return GsonUtils.fromJson(text, clazz);
    }

    @Override
    public String object2Json(Object instance) {
        return GsonUtils.toJson(instance);
    }

    @Override
    public <T> T parseObject(String input, Type clazz) {
        return GsonUtils.fromJson(input, clazz);
    }
}