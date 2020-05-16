package com.spring.cloud.user.bean;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author 汪波
 * @Date 2020/5/16 19:00
 **/
@Data
@Entity
@Table(name = "user_info")
public class UserInfo {

    @Id
    private String id;

    private String username;

    private String password;

    private String openid;

    private Integer role;
}
