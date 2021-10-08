package com.example.orderProcessor.service;

import com.example.orderProcessor.model.OrderInfo;
import com.example.orderProcessor.repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;

    public OrderInfo saveOrder(OrderInfo order){
        return this.orderRepo.save(order);
    }

    public void updateOrder(String orderNum , String paymentStatus , String orderStatus){
        this.orderRepo.updateOrder(orderNum,paymentStatus,orderStatus);
    }

}
