package com.spring.cloud.user.constant;

/**
 * @Author 汪波
 * @Date 2020/5/16 19:08
 **/
public interface CookieConstant {

    String TOKEN = "token";

    String OPENID = "openid";

    /**
     * 过期时间（单位：s）
     */
    Integer expire = 7200;
}
