package com.spring.cloud.product.service;

import com.spring.cloud.product.bean.ProductCategory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

/**
 * Description: product
 * Created by 汪波 on 2020/4/4 20:46
 */
@SpringBootTest
class ProductCategoryServiceTest {

    @Autowired
    private ProductCategoryService productCategoryService;

    @Test
    void findByCategoryTypeIn() {
        List<ProductCategory> productCategories = productCategoryService.findByCategoryTypeIn(Arrays.asList(11, 22));
        Assertions.assertTrue(productCategories.size() > 0);
    }
}