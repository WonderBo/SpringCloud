package com.spring.cloud.product.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 汪波
 * @Date 2020/4/11 18:09
 **/
@RestController
@Slf4j
public class ServerController {

    @GetMapping("/server/test")
    public String serviceCommunication() {
        return "this is production server_1";
    }
}
