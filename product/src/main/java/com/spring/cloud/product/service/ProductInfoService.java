package com.spring.cloud.product.service;

import com.spring.cloud.product.bean.ProductInfo;

import java.util.List;

/**
 * Description: product
 * Created by 汪波 on 2020/4/4 20:17
 */
public interface ProductInfoService {

    /**
     * 查询所有在架商品列表
     *
     * @Author 汪波
     * @Date 20:20 2020/4/4
     * @param []
     * @return java.util.List<com.spring.cloud.product.bean.ProductInfo>
     **/
    List<ProductInfo> findUpAll();
}
