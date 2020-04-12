package com.spring.cloud.order.service;

import com.spring.cloud.order.dto.OrderDTO;

/**
 * @Author 汪波
 * @Date 2020/4/5 18:16
 **/
public interface OrderService {

    /**
     * 创建订单
     *
     * @param orderDTO
     * @return
     */
    OrderDTO createOrder(OrderDTO orderDTO);
}
