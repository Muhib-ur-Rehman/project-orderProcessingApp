package com.example.orderProcessor.unit_test;

import com.example.orderProcessor.repository.OrderRepo;
import com.example.orderProcessor.service.OrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class OrderRepoUnitTest {

    @Mock
    OrderRepo orderRepo;

    @Test
    public void updateOrderUnitTest(){
        Assertions.assertDoesNotThrow(()->orderRepo.updateOrder("1","ACCEPTED","ACCEPTED"));
    }

}
