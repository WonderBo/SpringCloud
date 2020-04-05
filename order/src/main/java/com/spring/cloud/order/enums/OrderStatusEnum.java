package com.spring.cloud.order.enums;

import lombok.Getter;

/**
 * Description: order
 * Created by 汪波 on 2020/4/5 17:14
 */
@Getter
public enum OrderStatusEnum {
    NEW(0, "新订单"),
    FINISHED(1, "完结"),
    CANCEL(2, "取消"),
    ;

    private Integer code;
    private String message;

    OrderStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
