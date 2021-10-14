package com.example.orderProcessor.integration_test;

import com.example.orderProcessor.consumer.OrderProcessor;
import com.example.orderProcessor.model.OrderInfo;
import com.example.orderProcessor.service.OrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
}
