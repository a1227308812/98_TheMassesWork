//package com.westar.been;
//
//import com.contrarywind.interfaces.IPickerViewData;
//
//import io.realm.RealmObject;
//import io.realm.annotations.Index;
//
///**
// * Created by ZWP on 2019/5/6 16:41.
// * 描述：城市实体对象
// */
//public class CityNode extends RealmObject implements IPickerViewData {
//    @Index
//    String code;
//    String name;
//    @Index
//    String parent_code;
//
//    public String getCode() {
//        return code;
//    }
//
//    public void setCode(String code) {
//        this.code = code;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getParent_code() {
//        return parent_code;
//    }
//
//    public void setParent_code(String parent_code) {
//        this.parent_code = parent_code;
//    }
//
//    @Override
//    public String getPickerViewText() {
//        return name;
//    }
//}
