package com.example.orderProcessor.unit_test;

import com.example.orderProcessor.model.OrderInfo;
import com.example.orderProcessor.repository.OrderRepo;
import com.example.orderProcessor.service.OrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;

import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
public class OrderServiceUnitTest {

    @Mock
    OrderRepo orderRepo;

    @Test
    public void saveOrderUnitTest(){
        OrderService orderService = new OrderService(this.orderRepo);
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOrderId(1);
        orderInfo.setName("Biryani");
        orderInfo.setOrderStatus("PLACED");
        Mockito.when(this.orderRepo.save(orderInfo)).thenReturn(orderInfo);
        OrderInfo returnedOrder = orderService.saveOrder(orderInfo);
        Assertions.assertEquals(orderInfo,returnedOrder);
        Assertions.assertEquals(orderInfo.getOrderId(),returnedOrder.getOrderId());
        Assertions.assertEquals(orderInfo.getName(),returnedOrder.getName());
        Assertions.assertEquals(orderInfo.getOrderStatus(),returnedOrder.getOrderStatus());
    }

    @Test
    public void updateOrderUnitTest(){
        OrderService orderService = new OrderService(this.orderRepo);
        Mockito.doNothing().when(orderRepo).updateOrder("1","ACCEPTED","ACCEPTED");
        Assertions.assertDoesNotThrow(()-> orderService.updateOrder("1","ACCEPTED","ACCEPTED"));
    }
}
