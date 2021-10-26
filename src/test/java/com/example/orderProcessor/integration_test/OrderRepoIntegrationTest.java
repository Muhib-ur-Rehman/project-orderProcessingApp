package com.example.orderProcessor.integration_test;

import com.example.orderProcessor.repository.OrderRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class OrderRepoIntegrationTest {

    @Autowired
    OrderRepo orderRepo;

    @Test
    @Transactional
    public void updateOrderIntegrationTest(){
        Assertions.assertDoesNotThrow(()->orderRepo.updateOrder("fe93c5c4-1fbb-4aec-acb8-25f5264bd9a1","ACCEPTED","ACCEPTED"));
    }
}
