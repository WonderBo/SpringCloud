package com.spring.cloud.product.enums;

import lombok.Getter;

/**
 * Description: product
 * Created by 汪波 on 2020/4/12 17:37
 */
@Getter
public enum ResultEnum {

    PRODUCT_NOT_EXIST(1, "商品不存在"),
    PRODUCT_STOCK_ERROR(2, "库存有误"),
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
