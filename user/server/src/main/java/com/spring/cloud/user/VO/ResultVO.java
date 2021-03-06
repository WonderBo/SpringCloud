package com.spring.cloud.user.VO;

import lombok.Data;

/**
 * HTTP请求返回最外层对象
 *
 * @Author 汪波
 * @Date 2020/5/16 17:00
 **/
@Data
public class ResultVO<T> {

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 具体内容
     */
    private T data;
}
