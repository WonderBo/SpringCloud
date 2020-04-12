package com.spring.cloud.product.utils;/**
 * Description: product
 * Created by 汪波 on 2020/4/4 21:35
 */

import com.spring.cloud.product.VO.ResultVO;

/**
 * @Author 汪波
 * @Date 2020/4/4 21:35
 **/
public class ResultVOUtil {

    public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        resultVO.setData(object);
        return resultVO;
    }
}
