package com.spring.cloud.order.util;

import com.spring.cloud.order.VO.ResultVO;

/**
 * @Author 汪波
 * @Date 2020/4/5 19:43
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
