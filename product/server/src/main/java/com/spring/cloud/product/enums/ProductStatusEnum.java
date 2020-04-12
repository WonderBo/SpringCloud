package com.spring.cloud.product.enums;

import lombok.Getter;

/**
 * 商品状态
 *
 * Description: product
 * Created by 汪波 on 2020/4/4 20:30
 */
@Getter
public enum ProductStatusEnum {

    UP(0, "在架"),
    DOWN(1, "下架"),
    ;

    private Integer code;
    private String message;

    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
