package com.spring.cloud.order.convert;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.spring.cloud.order.bean.OrderDetail;
import com.spring.cloud.order.dto.OrderDTO;
import com.spring.cloud.order.enums.ResultEnum;
import com.spring.cloud.order.exception.OrderException;
import com.spring.cloud.order.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @Author 汪波
 * @Date 2020/4/5 19:28
 **/
@Slf4j
public class OrderForm2OrderDTOConverter {

    public static OrderDTO convert(OrderForm orderForm) {
        Gson gson = new Gson();
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());

        List<OrderDetail> orderDetailList = Lists.newArrayList();
        try {
            orderDetailList = gson.fromJson(orderForm.getItems(),
                    new TypeToken<List<OrderDetail>>() {
                    }.getType());
        } catch (Exception e) {
            log.error("【json转换】错误, string={}", orderForm.getItems());
            throw new OrderException(ResultEnum.PARAM_ERROR);
        }
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }
}
