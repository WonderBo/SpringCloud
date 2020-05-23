package com.spring.cloud.order.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * @Author 汪波
 * @Date 2020/5/23 20:44
 **/
@RestController
@DefaultProperties(defaultFallback = "defaultFallback")
public class HystrixController {

    /**
     * 服务降级不仅可用于目标服务不能正常提供服务的情况，还可用于自己服务降级（eg. 自己服务【触发降级条件】抛出异常）
     *
     * @return
     */
    @HystrixCommand(fallbackMethod = "fallback", commandProperties = {
            // 超时时间配置
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
            // 服务熔断配置
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),                      // 熔断是否可用
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),         // 滚动时间窗口内断路器最小请求数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),   // 休眠时间窗口
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")        // 断路器打开的错误百分比条件
    })
    @GetMapping("/getProductInfoList")
    public String getProductInfoList(@RequestParam("number") Integer number) {
        if (number % 2 == 0) {
            return "success!";
        }
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject("http://localhost:8081/product/listForOrder",
                Arrays.asList("157875227953464068"),
                String.class);
    }

    /**
     * fallback方法需要和原方法参数个数、参数类型、返回值类型相同，否则抛出异常
     * HystrixCommand 特定降级处理逻辑
     *
     * @return
     */
    private String fallback(Integer number) {
        return "getProductInfoList: 触发降级~~";
    }

    /**
     * 默认降级处理逻辑
     *
     * @return
     */
    private String defaultFallback() {
        return "default: 触发降级~~";
    }
}
