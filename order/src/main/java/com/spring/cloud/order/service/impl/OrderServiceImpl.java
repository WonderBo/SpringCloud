package com.spring.cloud.order.service.impl;

import com.spring.cloud.order.bean.OrderMaster;
import com.spring.cloud.order.dto.OrderDTO;
import com.spring.cloud.order.enums.OrderStatusEnum;
import com.spring.cloud.order.enums.PayStatusEnum;
import com.spring.cloud.order.repository.OrderDetailRepository;
import com.spring.cloud.order.repository.OrderMasterRepository;
import com.spring.cloud.order.service.OrderService;
import com.spring.cloud.order.util.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @Author 汪波
 * @Date 2020/4/5 18:21
 **/
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) {
        String orderId = KeyUtil.genUniqueKey();

        // 订单入库
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderAmount(new BigDecimal(100.0));
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterRepository.save(orderMaster);
        return orderDTO;
    }
}
