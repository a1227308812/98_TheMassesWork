package com.westar.module_woyaoyuyue;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.westar.library_base.base.ToolbarActivity;
import com.westar.library_base.common.ArouterPath;

/**
 * Created by ZWP on 2019/4/8 11:09.
 * 描述：预约部门选择
 */
@Route(path = ArouterPath.MODULE_WOYAOYUYUE_SELECT_DEP_ACTIVITY)
public class SelectDepActivity extends ToolbarActivity {


    @Override
    protected int getLayoutID() {
        return R.layout.activity_select_dep;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public String setBarTitle() {
        return "在线预约";
    }
}
