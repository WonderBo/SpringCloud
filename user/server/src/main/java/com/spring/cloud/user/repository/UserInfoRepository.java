package com.spring.cloud.user.repository;

import com.spring.cloud.user.bean.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author 汪波
 * @Date 2020/5/16 17:00
 **/
public interface UserInfoRepository extends JpaRepository<UserInfo, String> {
    UserInfo findByOpenid(String openid);
}
