package com.spring.cloud.order.message;

import com.fasterxml.jackson.core.type.TypeReference;
import com.spring.cloud.order.util.JsonUtil;
import com.spring.cloud.product.ProductInfoOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * ProductReceiver
 *
 * @Author 汪波
 * @Date 2020/5/4 21:24
 **/
@Component
@Slf4j
public class ProductReceiver {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private static final String PRODUCT_STOCK_TEMPLATE = "product_stock_%s";

    /**
     * 从Product服务获取商品扣减消息并存入Redis
     *
     * @param message
     */
    @RabbitListener(queuesToDeclare = @Queue("productInfo"))
    public void process(String message) {
        // 从MQ获取并消费消息
        List<ProductInfoOutput> productInfoOutputList = (List<ProductInfoOutput>)JsonUtil.fromJson(message,
                new TypeReference<List<ProductInfoOutput>>() {
        });
        log.info("get message: {} from mq: {}.", productInfoOutputList, "productInfo");

        // 转储到Redis
        productInfoOutputList.forEach(e -> {
            stringRedisTemplate.opsForValue().set(
                    String.format(PRODUCT_STOCK_TEMPLATE, e.getProductId()),
                    String.valueOf(e.getProductStock()));
        });
    }
}
