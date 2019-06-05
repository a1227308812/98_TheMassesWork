package com.westar.library_base.common;

/**
 * Created by ZWP on 2019/4/8 11:30.
 * 描述：路由地址
 */
public interface ArouterPath {
    /**
     * 主模块分组
     */
    String GROUP_HOME = "home";
    /**
     * 更新模块分组
     */
    String GROUP_UPDATE = "update";
    /**
     * 登录模块分组
     */
    String GROUP_LOGIN = "login";
    /**
     * 我要预约模块分组
     */
    String GROUP_WOYAOYUYUE = "woyaoyuyue";
    /**
     * 我要办事模块分组
     */
    String GROUP_WOYAOBANSHI = "woyaobanshi";
    /**
     * 我要查询模块分组
     */
    String GROUP_WOYAOCHAXUN = "woyaochaxun";
    /**
     * 我要咨询模块分组
     */
    String GROUP_WOYAOZIXUN = "woyaozixun";
    /**
     * 我要投诉模块分组
     */
    String GROUP_WOYAOTOUSU = "woyaotousu";




    /*app主工程模块 测试Activity*/
    String MAIN_ACTIVITY = "/" + GROUP_HOME + "/MainActivity";
    //首页
    String HOMEGROUP_ACTIVITY = "/" + GROUP_HOME + "/HomeGroupActivity";

    //地址选择页面
    String CHOICE_ADDRESS_ACTIVITY = "/" + GROUP_HOME + "/ChoiceAddressActivity";
    //搜索页面
    String SEARCH_ACTIVITY = "/" + GROUP_HOME + "/SearchActivity";
    //设置页面
    String SETTING_ACTIVITY = "/" + GROUP_HOME + "/SettingActivity";
    //个人信息页面
    String PERSONAL_INFORMATION_ACTIVITY = "/" + GROUP_HOME + "/personalInformationActivity";
    //我的证照列表页面
    String CARD_LIST_ACTIVITY = "/" + GROUP_HOME + "/CardListActivity";
    //我的证照详情页面
    String CARD_DETAIL_ACTIVITY = "/" + GROUP_HOME + "/CardDetailActivity";
    //地址管理页面
    String ADDRESS_MANAGER_ACTIVITY = "/" + GROUP_HOME + "/AddressManagerActivity";
    //关于我们页面
    String ABOUTME_ACTIVITY = "/" + GROUP_HOME + "/AboutMeActivity";
    //更多功能页面
    String MOREFUNCTION_ACTIVITY = "/" + GROUP_HOME + "/MoreFunctionActivity";


    /*更新模块*/
    String UPDATE_ACTIVITY = "/" + GROUP_UPDATE + "/UpdateActivity";


    /*登录模块*/
    //欢迎界面
    String WELCOME_ACTIVITY = "/" + GROUP_LOGIN + "/WelcomeActivity";
    //登录界面
    String LOGIN_ACTIVITY = "/" + GROUP_LOGIN + "/LoginActivity";
    //指纹验证界面
    String FINGERPRINT_VERIFICATION_ACTIVITY = "/" + GROUP_LOGIN + "/FingerprintVerificationActivity";
    //人脸识别页面
    String FACE_RECOGNITION_AUTHENTICATION_ACTIVITY = "/" + GROUP_LOGIN + "/FaceRecognitionAuthenticationActivity";
    //身份证信息上传界面
    String IDCARD_CONFIRM_ACTIVITY = "/" + GROUP_LOGIN + "/IDCardConfirmActivity";


    /*我要预约模块*/
    String SELECT_DEP_ACTIVITY = "/" + GROUP_WOYAOYUYUE + "/SelectDepActivity";


    /*我要办事模块*/
    String ITEM_LIST_ACTIVITY = "/" + GROUP_WOYAOBANSHI + "/ItemListActivity";


    /*我要查询模块*/
    String BANJIANCHAXUN_ACTIVITY = "/" + GROUP_WOYAOCHAXUN + "/BanJianChaXunActivity";


    /*我要咨询模块*/
    String ZHINENGWENDA_ACTIVITY = "/" + GROUP_WOYAOZIXUN + "/ZhiNengWenDaActivity";


    /*我要投诉模块*/
    String WOYAOTOUSU_ACTIVITY = "/" + GROUP_WOYAOTOUSU + "/WoYaoTouSuActivity";


    /*我的模块*/
    String ME_ACTIVITY = "/module_me/MeActivity";
}
