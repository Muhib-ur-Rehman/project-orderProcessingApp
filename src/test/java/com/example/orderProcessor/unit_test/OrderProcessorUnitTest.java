package com.example.orderProcessor.unit_test;

import com.example.orderProcessor.consumer.OrderProcessor;
import com.example.orderProcessor.model.OrderInfo;
import com.example.orderProcessor.service.OrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class OrderProcessorUnitTest {

    @Mock
    OrderService orderService;

    @Test
    public void consumeMessageFromQueueUnitTest(){
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOrderId(1);
        orderInfo.setName("Burger");
        orderInfo.setPaymentStatus("INITIATED");
        OrderProcessor orderProcessor = new OrderProcessor(this.orderService);
        Mockito.when(this.orderService.saveOrder(orderInfo)).thenReturn(orderInfo);
        Mockito.doNothing().when(orderService).updateOrder("","","");
        Assertions.assertDoesNotThrow(()-> orderProcessor.consumeMessageFromQueue(orderInfo));
    }
}
