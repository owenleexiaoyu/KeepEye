package com.owenandroid.keepeye.utils;

/**
 * Created by Administrator on 2017/4/9.
 * 用于存放APP的这种配置信息：如第三方SDK的APPkey、静态常量等
 */

public class AppConfig {
    //控制延时跳转的handler的what值
    public static final int HANDLER_FLASH_WHAT = 10001;
    //ShareUtils中判断是否是第一次启动的key
    public static final String SHAREUTILS_KEY_IS_FIRST = "isFirst";
    //ShareUtils存储登录成功时输入的用户名密码的key
    public static final String SHAREUTILS_KEY_USER_NAME_INPUT = "login_username";
    public static final String SHAREUTILS_KEY_USER_PASSWORD_INPUT = "login_password";
    //腾讯Bugly的APPID
    public static final String BUGLY_APP_ID = "dd5e72a87b";
    //快递查询的key
    public static final String EXPRESS_KEY = "78752e0a9f7eb18e06da849b526e4944";
    //归属地查询的key
    public static final String PHONE_KEY = "347c970ac9264aeb9ad62ffac16c538d";
    //图灵机器人APPid
    public static final String ROBOT_APP_ID = "872bb5b226f0415e973374026ffca036";

    //图灵机器人API接口
    public static final String TULING_API = "http://www.tuling123.com/openapi/api";
    //微信精选文章url
    public static final String WEIXIN_AIRTICLE_URL = "http://route.showapi.com/582-2";
    //易源API 的app id
    public static final String SHOWAPI_APPID = "35681";
    //易源API 的app secret
    public static final String SHOWAPI_SECRET = "14722671da20480e966206169ea5e76b";
    //http://route.showapi.com/582-2?showapi_appid=35681&showapi_sign=14722671da20480e966206169ea5e76b

    //妹子图片url
    public static final String GIRL_URL = "http://gank.io/api/search/query/listview/category/Android/count/10/page/1";

}
