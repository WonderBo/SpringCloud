package com.spring.cloud.product.service;

import com.spring.cloud.product.bean.ProductInfo;
import com.spring.cloud.product.service.ProductInfoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * Description: product
 * Created by 汪波 on 2020/4/4 20:35
 */
@SpringBootTest
class ProductInfoServiceTest {

    @Autowired
    private ProductInfoService productInfoService;

    @Test
    void findUpAll() {
        List<ProductInfo> productInfos = productInfoService.findUpAll();
        Assertions.assertTrue(productInfos.size() > 0);
    }
}