package com.spring.cloud.product.service;

import com.spring.cloud.product.bean.ProductInfo;
import com.spring.cloud.product.DecreaseStockInput;
import com.spring.cloud.product.ProductInfoOutput;

import java.util.List;

/**
 * Description: product
 * Created by 汪波 on 2020/4/4 20:17
 */
public interface ProductInfoService {

    /**
     * 查询所有在架商品列表
     *
     * @return
     */
    List<ProductInfo> findUpAll();

    /**
     * 查询商品列表
     *
     * @param productIdList
     * @return
     */
    List<ProductInfoOutput> findList(List<String> productIdList);

    /**
     * 扣库存
     *
     * @param decreaseStockInputList
     */
    void decreaseStock(List<DecreaseStockInput> decreaseStockInputList);
}
