package com.westar.module_woyaotousu;


import com.alibaba.android.arouter.facade.annotation.Route;
import com.westar.library_base.base.ToolbarActivity;
import com.westar.library_base.common.ArouterPath;

/**
 * Created by ZWP on 2019/4/8 11:20.
 * 描述：我要投诉发起界面
 */
@Route(path = ArouterPath.MODULE_WOYAOTOUSU_WO_YAO_TOU_SU_ACTIVITY)
public class WoYaoTouSuActivity extends ToolbarActivity {


    @Override
    protected int getLayoutID() {
        return R.layout.activity_wo_yao_tou_su;
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
        return "我要投诉";
    }
}
