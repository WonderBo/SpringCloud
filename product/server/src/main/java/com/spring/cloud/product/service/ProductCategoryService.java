package com.spring.cloud.product.service;

import com.spring.cloud.product.bean.ProductCategory;

import java.util.List;

/**
 * Description: product
 * Created by 汪波 on 2020/4/4 20:18
 */
public interface ProductCategoryService {

    /**
     * 根据类目查找
     *
     * @param categoryTypeList
     * @return
     */
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
