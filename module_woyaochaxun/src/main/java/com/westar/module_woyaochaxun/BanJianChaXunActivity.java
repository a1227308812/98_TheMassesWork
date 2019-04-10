package com.westar.module_woyaochaxun;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.westar.library_base.base.ToolbarActivity;
import com.westar.library_base.common.ArouterPath;

/**
 * Created by ZWP on 2019/4/8 13:10.
 * 描述：办件查询界面
 */
@Route(path = ArouterPath.MODULE_WOYAOCHAXUN_BAN_JIAN_CHA_XUN_ACTIVITY)
public class BanJianChaXunActivity extends ToolbarActivity {


    @Override
    protected int getLayoutID() {
        return R.layout.activity_ban_jian_cha_xun;
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
        return "办件查询";
    }
}
