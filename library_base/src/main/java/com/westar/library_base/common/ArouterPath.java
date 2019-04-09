package com.westar.library_base.common;

/**
 * Created by ZWP on 2019/4/8 11:30.
 * 描述：路由地址
 */
public interface ArouterPath {
    /*app主工程模块*/
    String APP_MAIN_ACTIVITY = "/app/MainActivity";
    //首页
    String APP_HOMEGROUP_ACTIVITY = "/app/HomeGroupActivity";


    /*更新模块*/
    String MODULE_UPDATE_UPDATE_ACTIVITY = "/module_update/UpdateActivity";


    /*登录模块*/
    String MODULE_LOGIN_WELCOME_ACTIVITY = "/module_login/WelcomeActivity";
    String MODULE_LOGIN_LOGIN_ACTIVITY = "/module_login/LoginActivity";
    String MODULE_LOGIN_FINGERPRINT_VERIFICATION_ACTIVITY = "/module_login/FingerprintVerificationActivity";


    /*我要预约模块*/
    String MODULE_WOYAOYUYUE_SELECT_DEP_ACTIVITY = "/module_woyaoyuyue/SelectDepActivity";


    /*我要办事模块*/
    String MODULE_WOYAOBANSHI_ITEM_LIST_ACTIVITY = "/module_woyaobanshi/ItemListActivity";


    /*我要查询模块*/
    String MODULE_WOYAOCHAXUN_BAN_JIAN_CHA_XUN_ACTIVITY = "/module_woyaochaxun/BanJianChaXunActivity";


    /*我要咨询模块*/
    String MODULE_WOYAOZIXUN_ZHI_NENG_WEN_DA_ACTIVITY = "/module_woyaozixun/ZhiNengWenDaActivity";


    /*我要投诉模块*/
    String MODULE_WOYAOTOUSU_WO_YAO_TOU_SU_ACTIVITY = "/module_woyaotousu/WoYaoTouSuActivity";


    /*我的模块*/
    String MODULE_ME_ME_ACTIVITY = "/module_me/MeActivity";
}
