package com.spring.cloud.product.controller;

import com.google.common.collect.Lists;
import com.spring.cloud.product.VO.ProductInfoVO;
import com.spring.cloud.product.VO.ProductVO;
import com.spring.cloud.product.VO.ResultVO;
import com.spring.cloud.product.bean.ProductCategory;
import com.spring.cloud.product.bean.ProductInfo;
import com.spring.cloud.product.DecreaseStockInput;
import com.spring.cloud.product.ProductInfoOutput;
import com.spring.cloud.product.service.ProductCategoryService;
import com.spring.cloud.product.service.ProductInfoService;
import com.spring.cloud.product.utils.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * ProductController
 *
 * @Author 汪波
 * @Date 2020/4/4 17:11
 **/
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private ProductCategoryService productCategoryService;

    /**
     * 查询商品类别列表
     *
     * @param request
     * @return
     */
    @GetMapping("/list")
    public ResultVO<ProductVO> listProduct(HttpServletRequest request) {
        // 1. 查询所有在架的商品
        List<ProductInfo> productInfoList = productInfoService.findUpAll();

        // 2. 获取类目type列表
        List<Integer> categoryTypeList = productInfoList.stream()
                .filter(Objects::nonNull)
                .map(ProductInfo::getCategoryType)
                .collect(Collectors.toList());

        // 3. 从数据库查询类目
        List<ProductCategory> categoryList = productCategoryService.findByCategoryTypeIn(categoryTypeList);

        // 4. 构造数据
        List<ProductVO> productVOList = Lists.newArrayList();
        for (ProductCategory productCategory : categoryList) {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());

            List<ProductInfoVO> productInfoVOList = Lists.newArrayList();
            for (ProductInfo productInfo : productInfoList) {
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }
        return ResultVOUtil.success(productVOList);
    }

    /**
     * 获取商品列表（供订单服务使用）
     *
     * @param productIdList
     * @return
     */
    @PostMapping("/listForOrder")
    public List<ProductInfoOutput> listForOrder(@RequestBody List<String> productIdList) {
        return productInfoService.findList(productIdList);
    }

    @PostMapping("/decreaseStock")
    public void decreaseStock(@RequestBody List<DecreaseStockInput> decreaseStockInputList) {
        productInfoService.decreaseStock(decreaseStockInputList);
    }
}
