package com.spring.cloud.product.service.impl;

import com.spring.cloud.product.bean.ProductInfo;
import com.spring.cloud.product.enums.ProductStatusEnum;
import com.spring.cloud.product.repository.ProductInfoRepository;
import com.spring.cloud.product.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author 汪波
 * @Date 2020/4/4 20:24
 **/
@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }
}
