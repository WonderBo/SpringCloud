package com.spring.cloud.order.util;

import java.util.Random;

/**
 * @Author 汪波
 * @Date 2020/4/5 18:23
 **/
public class KeyUtil {

    /**
     * 生成唯一主键
     * 格式：时间戳 + 随机数
     *
     * @return
     */
    public static synchronized String genUniqueKey() {
        Random random = new Random();
        Integer num = random.nextInt(900000) + 100000;

        return System.currentTimeMillis() + String.valueOf(num);
    }
}
