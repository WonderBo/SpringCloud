package com.spring.cloud.order.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 接收MQ消息
 *
 * @Author 汪波
 * @Date 2020/5/3 20:53
 **/
@Slf4j
@Component
public class MqReceiver {

    /**
     * 需要手动创建Queue
     *
     * @param message
     */
    @RabbitListener(queues = "myQueue")
    public void receive(String message) {
        log.info("MqReceiver_1: {}", message);
    }

    /**
     * 自动创建Queue
     *
     * @param message
     */
    @RabbitListener(queuesToDeclare = @Queue("myQueue"))
    public void receive2(String message) {
        log.info("MqReceiver_2: {}", message);
    }

    /**
     * 自动创建并绑定Exchange和Queue
     *
     * @param message
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("myQueue"),
            exchange = @Exchange("myExchange")
    ))
    public void receive3(String message) {
        log.info("MqReceiver_3: {}", message);
    }


    /**
     * 分组路由：Producer -> Message -> Exchange -> Routing Key -> Queue -> Consumer
     */

    /**
     * 使用Exchange对消息进行分组路由（route_key规则匹配）
     * 测试1
     *
     * @param message
     */
    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange("myOrder"),
            key = "route_key_order1",
            value = @Queue("queue1")
    ))
    public void receive4(String message) {
        log.info("Route_Key 1, MqReceiver_4: {}", message);
    }

    /**
     * 使用Exchange对消息进行分组路由（route_key规则匹配）
     * 测试2
     *
     * @param message
     */
    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange("myOrder"),
            key = "route_key_order2",
            value = @Queue("queue2")
    ))
    public void receive5(String message) {
        log.info("Route_Key 2, MqReceiver_5: {}", message);
    }
}
