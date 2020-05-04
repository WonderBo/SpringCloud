package com.spring.cloud.order.message;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * Stream 接收消息
 *
 * @Author 汪波
 * @Date 2020/5/4 18:00
 **/
public interface StreamClient {

    String INPUT = "myMessage";

    String REPLY = "myReply";

    @Input(StreamClient.INPUT)
    SubscribableChannel input();

    @Output(StreamClient.INPUT)
    MessageChannel output();

    @Input(StreamClient.REPLY)
    SubscribableChannel replyInput();

    @Output(StreamClient.REPLY)
    MessageChannel replyOutput();
}
