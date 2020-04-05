package com.spring.cloud.order.repository;

import com.spring.cloud.order.bean.OrderMaster;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Description: order
 * Created by 汪波 on 2020/4/5 17:10
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {
}
