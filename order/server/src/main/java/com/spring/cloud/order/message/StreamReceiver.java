package com.spring.cloud.order.message;

import com.spring.cloud.order.dto.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

/**
 * Stream 接收消息
 *
 * @Author 汪波
 * @Date 2020/5/4 17:28
 **/
@Component
@EnableBinding(StreamClient.class)
@Slf4j
public class StreamReceiver {

//    /**
//     * Stream接收基本类型
//     *
//     * @param message
//     */
//    @StreamListener(StreamClient.INPUT)
//    public void receiveLiteral(String message) {
//        log.info("StreamReceiver: {}", message);
//    }

    /**
     * Stream接收复杂对象（自动反序列化）
     * 消费完成后返回Reply消息
     *
     * @param message
     */
    @StreamListener(StreamClient.INPUT)
    @SendTo(StreamClient.REPLY)
    public String receiveObject(OrderDTO message) {
        log.info("StreamReceiver_2: {}", message);
        // 消费完后返回reply消息
        return "had received.";
    }

    /**
     * Stream接收Reply消息
     *
     * @param message
     */
    @StreamListener(StreamClient.REPLY)
    public void receiveReply(String message) {
        log.info("StreamReceiver_3: {}", message);
    }
}
