package com.example.orderProcessor.repository;

import com.example.orderProcessor.model.OrderInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderRepo extends JpaRepository<OrderInfo,Integer> {

    @Modifying
    @Query(value = "UPDATE order_info set payment_status= :paymentStatus , order_status= :orderStatus where order_num= :orderNum" , nativeQuery = true)
    public void updateOrder(@Param("orderNum") String orderNum , @Param("paymentStatus") String paymentStatus , @Param("orderStatus") String orderStatus);
}
