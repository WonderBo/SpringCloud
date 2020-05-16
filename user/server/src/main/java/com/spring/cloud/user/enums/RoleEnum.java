package com.spring.cloud.user.enums;

import lombok.Getter;

/**
 * @Author 汪波
 * @Date 2020/5/16 17:00
 **/
@Getter
public enum RoleEnum {
    BUYER(1, "买家"),
    SELLER(2, "卖家"),
    ;

    private Integer code;

    private String message;

    RoleEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
