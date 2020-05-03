package com.spring.cloud.order.message;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
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
public class MqSender {

    @Autowired
    private AmqpTemplate amqpTemplate;

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
}
