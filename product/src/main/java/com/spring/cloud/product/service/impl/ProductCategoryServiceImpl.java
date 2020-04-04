package com.spring.cloud.product.service.impl;

import com.spring.cloud.product.bean.ProductCategory;
import com.spring.cloud.product.repository.ProductCategoryRepository;
import com.spring.cloud.product.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author 汪波
 * @Date 2020/4/4 20:41
 **/
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return productCategoryRepository.findByCategoryTypeIn(categoryTypeList);
    }
}
