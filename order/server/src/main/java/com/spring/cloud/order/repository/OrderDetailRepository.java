package com.spring.cloud.order.repository;

import com.spring.cloud.order.bean.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Description: order
 * Created by 汪波 on 2020/4/5 17:11
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {
    List<OrderDetail> findByOrOrderId(String orderId);
}
