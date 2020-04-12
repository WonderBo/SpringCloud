package com.spring.cloud.product.exception;

import com.spring.cloud.product.enums.ResultEnum;

/**
 * @Author 汪波
 * @Date 2020/4/12 17:36
 **/
public class ProductException extends RuntimeException {

    private Integer code;

    public ProductException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public ProductException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
}
