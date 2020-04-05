package com.spring.cloud.order.repository;

import com.spring.cloud.order.bean.OrderMaster;
import com.spring.cloud.order.enums.OrderStatusEnum;
import com.spring.cloud.order.enums.PayStatusEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

/**
 * Description: order
 * Created by 汪波 on 2020/4/5 17:12
 */
@SpringBootTest
class OrderMasterRepositoryTest {

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Test
    public void testSave() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("1234567");
        orderMaster.setBuyerName("jack");
        orderMaster.setBuyerPhone("17589690741");
        orderMaster.setBuyerAddress("北京");
        orderMaster.setBuyerOpenid("1101110");
        orderMaster.setOrderAmount(new BigDecimal(2.5));
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());

        OrderMaster result = orderMasterRepository.save(orderMaster);
        Assertions.assertNotNull(result);
    }
}