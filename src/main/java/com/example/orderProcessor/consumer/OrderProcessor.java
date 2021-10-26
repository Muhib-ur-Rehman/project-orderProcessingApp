package com.example.orderProcessor.consumer;

import com.example.orderProcessor.config.OrderConfig;
import com.example.orderProcessor.model.OrderInfo;
import com.example.orderProcessor.service.OrderService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderProcessor {

    @Autowired
    OrderService orderService;

    public OrderProcessor(OrderService orderService){
        this.orderService=orderService;
    }

    @RabbitListener(queues = OrderConfig.QUEUE)
    public void consumeMessageFromQueue(OrderInfo order){
        System.out.println("Message received from queue : " + order);
        if (order.getPaymentStatus().equals("INITIATED")) {
            this.orderService.saveOrder(order);
        }
        if (!order.getPaymentStatus().equals("INITIATED")){
            this.orderService.updateOrder(order.getOrderNum(),order.getPaymentStatus(),order.getOrderStatus());
        }
    }
}
