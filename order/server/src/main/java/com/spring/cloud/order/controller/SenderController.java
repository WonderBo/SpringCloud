package com.spring.cloud.order.controller;

import com.spring.cloud.order.dto.OrderDTO;
import com.spring.cloud.order.message.StreamClient;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 发送MQ消息
 *
 * @Author 汪波
 * @Date 2020/5/3 20:56
 **/
@RestController
@RequestMapping("mq")
public class SenderController {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private StreamClient streamClient;

    @RequestMapping("/send/test")
    public void send() {
        amqpTemplate.convertAndSend("myQueue", "now " + new Date());
    }

    @RequestMapping("/send/test/order1")
    public void sendOrder() {
        amqpTemplate.convertAndSend("myOrder", "route_key_order1", "now " + new Date());
    }

    @RequestMapping("/send/test/order2")
    public void sendOrder2() {
        amqpTemplate.convertAndSend("myOrder", "route_key_order2", "now " + new Date());
    }

    /**
     * Stream发送基本类型
     */
    @GetMapping("/send/test/stream")
    public void sendStreamLiteral() {
        streamClient.output().send(MessageBuilder.withPayload("now " + new Date()).build());
    }

    /**
     * Stream发送复杂对象
     */
    @GetMapping("/send/test/stream/object")
    public void sendStreamObject() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId("123456");
        streamClient.output().send(MessageBuilder.withPayload(orderDTO).build());
    }
}
