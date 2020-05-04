package com.spring.cloud.product.service.impl;

import com.google.common.collect.Lists;
import com.spring.cloud.product.bean.ProductInfo;
import com.spring.cloud.product.DecreaseStockInput;
import com.spring.cloud.product.ProductInfoOutput;
import com.spring.cloud.product.enums.ProductStatusEnum;
import com.spring.cloud.product.enums.ResultEnum;
import com.spring.cloud.product.exception.ProductException;
import com.spring.cloud.product.repository.ProductInfoRepository;
import com.spring.cloud.product.service.ProductInfoService;
import com.spring.cloud.product.utils.JsonUtil;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Author 汪波
 * @Date 2020/4/4 20:24
 **/
@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public List<ProductInfoOutput> findList(List<String> productIdList) {
        return productInfoRepository.findByProductIdIn(productIdList).stream()
                .map(e -> {
                    ProductInfoOutput output = new ProductInfoOutput();
                    BeanUtils.copyProperties(e, output);
                    return output;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void decreaseStock(List<DecreaseStockInput> decreaseStockInputList) {
        // 数据库操作可以保证原子性，避免与非原子性操作（缓存、MQ等）耦合
        List<ProductInfo> productInfoList = decreaseStockFromDB(decreaseStockInputList);

        // 向Order服务发送MQ消息
        List<ProductInfoOutput> productInfoOutputList = productInfoList.stream()
                .map(e -> {
                    ProductInfoOutput output = new ProductInfoOutput();
                    BeanUtils.copyProperties(e, output);
                    return output;
                })
                .collect(Collectors.toList());
        amqpTemplate.convertAndSend("productInfo", JsonUtil.toJson(productInfoOutputList));
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public List<ProductInfo> decreaseStockFromDB(List<DecreaseStockInput> decreaseStockInputList) {
        List<ProductInfo> productInfoList = Lists.newArrayList();
        for (DecreaseStockInput cartDTO : decreaseStockInputList) {
            Optional<ProductInfo> productInfoOptional = productInfoRepository.findById(cartDTO.getProductId());
            // 判断商品是否存在
            if (!productInfoOptional.isPresent()) {
                throw new ProductException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            ProductInfo productInfo = productInfoOptional.get();
            // 库存是否足够
            Integer result = productInfo.getProductStock() - cartDTO.getProductQuantity();
            if (result < 0) {
                throw new ProductException(ResultEnum.PRODUCT_STOCK_ERROR);
            }

            productInfo.setProductStock(result);
            productInfoRepository.save(productInfo);

            productInfoList.add(productInfo);
        }
        return productInfoList;
    }
}
