package com.spring.cloud.user.enums;

import lombok.Getter;

/**
 * @Author 汪波
 * @Date 2020/5/16 17:00
 **/
@Getter
public enum ResultEnum {

    LOGIN_FAIL(1, "登录失败"),
    ROLE_ERROR(2, "角色权限有误"),
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
