package com.westar.module_woyaobanshi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.westar.library_base.base.ToolbarActivity;
import com.westar.library_base.common.ArouterPath;

/**
 * Created by ZWP on 2019/4/8 11:02.
 * 描述：事项列表
 */
@Route(path = ArouterPath.MODULE_WOYAOBANSHI_ITEM_LIST_ACTIVITY)
public class ItemListActivity extends ToolbarActivity {


    @Override
    protected int getLayoutID() {
        return R.layout.activity_item_list;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public String setBarTitle() {
        return "办事指南";
    }
}
