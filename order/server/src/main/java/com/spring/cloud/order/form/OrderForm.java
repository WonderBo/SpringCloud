package com.spring.cloud.order.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @Author 汪波
 * @Date 2020/4/5 19:15
 **/
@Data
public class OrderForm {

    @NotEmpty(message = "姓名必填")
    private String name;

    @NotEmpty(message = "手机号必填")
    private String phone;

    @NotEmpty(message = "地址必填")
    private String address;

    @NotEmpty(message = "openid必填")
    private String openid;

    @NotEmpty(message = "购物车不能为空")
    private String items;
}
