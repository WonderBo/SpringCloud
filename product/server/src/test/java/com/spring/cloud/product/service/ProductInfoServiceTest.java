package com.spring.cloud.product.service;

import com.google.common.collect.Lists;
import com.spring.cloud.product.DecreaseStockInput;
import com.spring.cloud.product.ProductInfoOutput;
import com.spring.cloud.product.bean.ProductInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
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

    @Test
    void findList() {
        List<ProductInfoOutput> productInfos = productInfoService.findList(Arrays.asList("157875196366160022", "164103465734242707"));
        Assertions.assertTrue(productInfos.size() > 0);
    }

    @Test
    void decreaseStock() {
        DecreaseStockInput decreaseStockInput = new DecreaseStockInput("157875227953464068", 3);
        productInfoService.decreaseStock(Lists.newArrayList(decreaseStockInput));
    }
}