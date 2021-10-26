package com.example.orderProcessor.integration_test;

import com.example.orderProcessor.consumer.OrderProcessor;
import com.example.orderProcessor.model.OrderInfo;
import com.example.orderProcessor.repository.OrderRepo;
import com.example.orderProcessor.service.OrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@SpringBootTest
public class OrderProcessorIntegrationTest {

    @Autowired
    OrderService orderService;

    @Test
    public void consumeMessageFromQueueIntegrationTest(){
        OrderProcessor orderProcessor = new OrderProcessor(this.orderService);
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOrderNum(UUID.randomUUID().toString());
        orderInfo.setOrderId(1);
        orderInfo.setName("Burger");
        orderInfo.setAccountNum("12345");
        orderInfo.setOrderStatus("PLACED");
        orderInfo.setPaymentStatus("INITIATED");
        OrderInfo orderInfo1 = new OrderInfo();
        orderInfo1.setOrderNum(UUID.randomUUID().toString());
        orderInfo1.setOrderId(1);
        orderInfo1.setName("Burger");
        orderInfo1.setAccountNum("12345");
        orderInfo1.setOrderStatus("PLACED");
        orderInfo1.setPaymentStatus("ACCEPTED");
        Assertions.assertDoesNotThrow(()-> orderProcessor.consumeMessageFromQueue(orderInfo));
        Assertions.assertDoesNotThrow(()-> orderProcessor.consumeMessageFromQueue(orderInfo1));

    }

    @Autowired
    OrderRepo orderRepo;

    @Test
    @Transactional
    public void updateOrderIntegrationTest(){
        Assertions.assertDoesNotThrow(()->orderRepo.updateOrder("fe93c5c4-1fbb-4aec-acb8-25f5264bd9a1","ACCEPTED","ACCEPTED"));
    }

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
    public void updateOrderIntegrationTest2(){
        Assertions.assertDoesNotThrow(()-> orderService.updateOrder("121qw134r","ACCEPTED","ACCEPTED"));
    }
}
