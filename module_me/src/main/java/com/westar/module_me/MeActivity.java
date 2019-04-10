package com.westar.module_me;


import com.alibaba.android.arouter.facade.annotation.Route;
import com.westar.library_base.base.ToolbarActivity;
import com.westar.library_base.common.ArouterPath;

/**
 * Created by ZWP on 2019/4/8 11:27.
 * 描述：我的界面
 */
@Route(path = ArouterPath.MODULE_ME_ME_ACTIVITY)
public class MeActivity extends ToolbarActivity {

    @Override
    protected int getLayoutID() {
        return R.layout.activity_me;
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
        return "";
    }
}
