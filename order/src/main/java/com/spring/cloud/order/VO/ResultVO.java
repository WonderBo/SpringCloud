package com.spring.cloud.order.VO;

import lombok.Data;

/**
 * @Author 汪波
 * @Date 2020/4/5 19:13
 **/
@Data
public class ResultVO<T> {

    private Integer code;

    private String msg;

    private T data;
}
