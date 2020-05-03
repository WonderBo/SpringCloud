package com.spring.cloud.order.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 汪波
 * @Date 2020/5/3 16:50
 **/
@RestController
@RefreshScope
@RequestMapping("env")
public class EnvController {

    @Value("${env}")
    private String env;

    @GetMapping("/print")
    public String getEnv() {
        return env;
    }
}
