package com.westar.masseswork_98.been;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

/**
 * Created by ZWP on 2019/4/26 17:42.
 * 描述：历史搜索数据实体类
 */
@RealmClass
public class HisSearch extends RealmObject implements Serializable {
    @PrimaryKey
    private String id;

    private String searchData;
    private long searchTime;

    public String getSearchData() {
        return searchData;
    }

    public HisSearch setSearchData(String searchData) {
        this.searchData = searchData;
        return this;
    }

    public long getSearchTime() {
        return searchTime;
    }

    public HisSearch setSearchTime(long searchTime) {
        this.searchTime = searchTime;
        return this;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
