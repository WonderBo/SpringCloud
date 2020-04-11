package com.spring.cloud.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Spring Cloud支持http调用的两种方式: RestTemplate、Feign客户端
 * Feign客户端是一个声明式（注解方式）http远程调用工具
 * 需要配置eureka中注册服务的别名
 *
 * Description: order
 * Created by 汪波 on 2020/4/11 22:29
 */
@FeignClient(name = "product")
public interface ProductClent {

    /**
     * Feign客户端具体访问的服务url
     * @return
     */
    @GetMapping("/server/test")
    String productRequest();
}
