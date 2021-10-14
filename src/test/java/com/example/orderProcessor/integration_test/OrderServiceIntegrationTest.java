package com.example.orderProcessor.integration_test;

import com.example.orderProcessor.model.OrderInfo;
import com.example.orderProcessor.service.OrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OrderServiceIntegrationTest {

    @Autowired
    OrderService orderService;

    @Test
    public void saveOrderIntegrationTest(){
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOrderId(1);
        orderInfo.setName("Biryani");
        orderInfo.setOrderStatus("PLACED");
        orderInfo.setAccountNum("12345");
        orderInfo.setOrderNum("121qw134r");
        orderInfo.setPaymentStatus("INITIATED");
        orderInfo.setItemId(1);
        orderInfo.setPrice(200);
        orderInfo.setQty(1);
        OrderInfo savedOrder = this.orderService.saveOrder(orderInfo);
        Assertions.assertEquals(orderInfo.getName(),savedOrder.getName());
        Assertions.assertEquals(orderInfo.getOrderStatus(),savedOrder.getOrderStatus());
        Assertions.assertEquals(orderInfo.getOrderNum(),savedOrder.getOrderNum());
        Assertions.assertEquals(orderInfo.getPaymentStatus(),savedOrder.getPaymentStatus());
        Assertions.assertEquals(orderInfo.getQty(),savedOrder.getQty());
        Assertions.assertEquals(orderInfo.getPrice(),savedOrder.getPrice());
    }

    @Test
    public void updateOrderIntegrationTest(){
        Assertions.assertDoesNotThrow(()-> orderService.updateOrder("121qw134r","ACCEPTED","ACCEPTED"));
    }
}
