package com.spring.cloud.user.service.impl;

import com.spring.cloud.user.bean.UserInfo;
import com.spring.cloud.user.repository.UserInfoRepository;
import com.spring.cloud.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author 汪波
 * @Date 2020/5/16 19:06
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoRepository repository;

    @Override
    public UserInfo findByOpenid(String openid) {
        return repository.findByOpenid(openid);
    }
}
