package com.vincent.admin.enums;

/**
 * @author Vincent Tsai
 * @date 2021/4/23 19:52
 */
public class Github {

    public static final String CLIENT_ID = "56f813813ae6c3798552"; // TODO 修改成自己的
    public static final String CLIENT_SECRET = "4247f19b3df1265fa3837679ae59b734cbbed84b";  // TODO 修改成自己的
    public static final String CALLBACK = "http://127.0.0.1:9527/front/auth/callback/github";  // TODO 修改成自己的  [注意：callback要和注册的回调路径保持一致  否则登录授权之后会报NullPointerException]

    //获取code的url
    public static final String CODE_URL = "https://github.com/login/oauth/authorize?client_id=CLIENT_ID&state=STATE&redirect_uri=CALLBACK";
    //获取token的url
    public static final String TOKEN_URL = "https://github.com/login/oauth/access_token?client_id=CLIENT_ID&client_secret=CLIENT_SECRET&code=CODE&redirect_uri=CALLBACK";
    //获取用户信息的url
    public static final String USER_INFO_URL = "https://api.github.com/user?access_token=TOKEN";

}