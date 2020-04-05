package com.spring.cloud.order.enums;

import lombok.Getter;

/**
 * Description: order
 * Created by 汪波 on 2020/4/5 17:16
 */
@Getter
public enum PayStatusEnum {
    WAIT(0, "等待支付"),
    SUCCESS(1, "支付成功"),
    ;

    private Integer code;

    private String message;

    PayStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
