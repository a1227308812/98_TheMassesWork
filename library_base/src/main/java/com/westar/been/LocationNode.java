package com.westar.been;

import com.contrarywind.interfaces.IPickerViewData;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Index;

/**
 * Created by ZWP on 2019/5/6 16:41.
 * 描述：城市实体对象
 */
public class LocationNode extends RealmObject implements IPickerViewData {
    String code;
    String name;
    RealmList<LocationNode> childs;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RealmList<LocationNode> getChilds() {
        return childs;
    }

    public void setChilds(RealmList<LocationNode> childs) {
        this.childs = childs;
    }

    @Override
    public String getPickerViewText() {
        return name;
    }
}
