package com.spring.cloud.user.service;

import com.spring.cloud.user.bean.UserInfo;

/**
 * @Author 汪波
 * @Date 2020/5/16 17:00
 **/
public interface UserService {
    /**
     * 通过openid查询用户信息
     *
     * @param openid
     * @return
     */
    UserInfo findByOpenid(String openid);
}
