package com.westar.library_base.common;

/**
 * Created by ZWP on 2019/4/8 11:30.
 * 描述：路由地址
 */
public interface ArouterPath {
    /*app主工程模块 测试Activity*/
    String APP_MAIN_ACTIVITY = "/home/MainActivity";
    //首页
    String APP_HOMEGROUP_ACTIVITY = "/home/HomeGroupActivity";

    //地址选择页面
    String APP_CHOICE_ADDRESS_ACTIVITY = "/home/ChoiceAddressActivity";
    //搜索页面
    String APP_SEARCH_ACTIVITY = "/home/SearchActivity";
    //设置页面
    String APP_SETTING_ACTIVITY = "/home/SettingActivity";
    //个人信息页面
    String APP_PERSONAL_INFORMATION_ACTIVITY = "/home/personalInformationActivity";
    //我的证照列表页面
    String APP_CARD_LIST_ACTIVITY = "/home/CardListActivity";
    //我的证照详情页面
    String APP_CARD_DETAIL_ACTIVITY = "/home/CardDetailActivity";
    //地址管理页面
    String APP_ADDRESS_MANAGER_ACTIVITY = "/home/AddressManagerActivity";
    //关于我们页面
    String APP_ABOUTME_ACTIVITY = "/home/AboutMeActivity";
    //更多功能页面
    String APP_MOREFUNCTION_ACTIVITY = "/home/MoreFunctionActivity";


    /*更新模块*/
    String MODULE_UPDATE_UPDATE_ACTIVITY = "/module_update/UpdateActivity";


    /*登录模块*/
    //欢迎界面
    String MODULE_LOGIN_WELCOME_ACTIVITY = "/module_login/WelcomeActivity";
    //登录界面
    String MODULE_LOGIN_LOGIN_ACTIVITY = "/module_login/LoginActivity";
    //指纹验证界面
    String MODULE_LOGIN_FINGERPRINT_VERIFICATION_ACTIVITY = "/module_login/FingerprintVerificationActivity";
    //人脸识别页面
    String MODULE_LOGIN_FACE_RECOGNITION_AUTHENTICATION_ACTIVITY = "/module_login/FaceRecognitionAuthenticationActivity";
    //身份证信息上传界面
    String MODULE_LOGIN_IDCARD_CONFIRM_ACTIVITY = "/module_login/IDCardConfirmActivity";



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
