package com.spring.cloud.order.controller;

import com.spring.cloud.order.client.ProductFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Author 汪波
 * @Date 2020/4/11 18:43
 **/
@RestController
@Slf4j
public class ClientController {

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ProductFeignClient productFeignClient;

    /**
     * 直接使用RestTemplate， 其中url为固定
     *
     * @return
     */
    @GetMapping("/client/rest/1")
    public String restTemplate_1() {
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject("http://localhost:8081/server/test", String.class);
        log.info("server response is: {}", response);
        return response;
    }

    /**
     * 利用LoadBalancerClient通过应用名获取url（host + port），然后再使用RestTemplate
     *
     * @return
     */
    @GetMapping("/client/rest/2")
    public String restTemplate_2() {
        ServiceInstance serviceInstance = loadBalancerClient.choose("PRODUCT");
        String host = serviceInstance.getHost();
        int port = serviceInstance.getPort();
        String url = String.format("http://%s:%s/server/test", host, port);

        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);
        log.info("server response is: {}", response);
        return response;
    }

    /**
     * 利用@LoadBalanced，可在RestTemplate里使用应用名
     *
     * @return
     */
    @GetMapping("/client/rest/3")
    public String restTemplate_3() {
        String response = restTemplate.getForObject("http://PRODUCT/server/test", String.class);
        log.info("server response is: {}", response);
        return response;
    }

    /**
     * 基于FeignClient访问对应服务
     *
     * @return
     */
    @GetMapping("/client/feign/4")
    public String restTemplate_4() {
        String response = productFeignClient.productRequest();
        log.info("server response is: {}", response);
        return response;
    }
}
