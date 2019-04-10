package com.westar.module_woyaozixun;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.westar.library_base.base.ToolbarActivity;
import com.westar.library_base.common.ArouterPath;

/**
 * Created by ZWP on 2019/4/8 11:24.
 * 描述：智能问答界面
 */
@Route(path = ArouterPath.MODULE_WOYAOZIXUN_ZHI_NENG_WEN_DA_ACTIVITY)
public class ZhiNengWenDaActivity extends ToolbarActivity {

    @Override
    protected int getLayoutID() {
        return R.layout.activity_zhi_neng_wen_da;
    }

    @Override
    protected void findId() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public String setBarTitle() {
        return "智能问答";
    }
}
