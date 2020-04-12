package com.spring.cloud.order.exception;

import com.spring.cloud.order.enums.ResultEnum;

/**
 * @Author 汪波
 * @Date 2020/4/5 19:21
 **/
public class OrderException extends RuntimeException {

    private Integer code;

    public OrderException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public OrderException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
}
