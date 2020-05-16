package com.spring.cloud.order.controller;

import com.google.common.collect.Maps;
import com.spring.cloud.order.VO.ResultVO;
import com.spring.cloud.order.convert.OrderForm2OrderDTOConverter;
import com.spring.cloud.order.dto.OrderDTO;
import com.spring.cloud.order.enums.ResultEnum;
import com.spring.cloud.order.exception.OrderException;
import com.spring.cloud.order.form.OrderForm;
import com.spring.cloud.order.service.OrderService;
import com.spring.cloud.order.util.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

/**
 * @Author 汪波
 * @Date 2020/4/5 19:10
 **/
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public ResultVO<Map<String, String>> createOrder(@Valid OrderForm orderForm, BindingResult bindingResult) {
        // Valid框架参数校验
        if (bindingResult.hasErrors()) {
            log.error("【创建订单】参数不正确, orderForm={}", orderForm);
            throw new OrderException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        // orderForm -> orderDTO
        OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("【创建订单】购物车信息为空");
            throw new OrderException(ResultEnum.CART_EMPTY);
        }

        OrderDTO result = orderService.createOrder(orderDTO);

        Map<String, String> map = Maps.newHashMap();
        map.put("orderId", result.getOrderId());
        return ResultVOUtil.success(map);
    }

    @PostMapping("/finish")
    public ResultVO<OrderDTO> finish(@RequestParam("orderId") String orderId) {
        return ResultVOUtil.success(orderService.finish(orderId));
    }
}
