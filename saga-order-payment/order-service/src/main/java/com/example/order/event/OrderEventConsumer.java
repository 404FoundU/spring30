package com.example.order.consumer;

import com.example.order.entity.Order;
import com.example.order.repository.OrderRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderEventConsumer {
    private final OrderRepository orderRepository;
    private final ObjectMapper objectMapper;

    public OrderEventConsumer(OrderRepository orderRepository, ObjectMapper objectMapper) {
        this.orderRepository = orderRepository;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "payment-success", groupId = "order-group")
    public void handlePaymentSuccess(String orderJson) throws Exception {
        Order order = objectMapper.readValue(orderJson, Order.class);
        order.setStatus("CONFIRMED");
        orderRepository.save(order);
    }

    @KafkaListener(topics = "payment-failed", groupId = "order-group")
    public void handlePaymentFailure(String orderJson) throws Exception {
        Order order = objectMapper.readValue(orderJson, Order.class);
        orderRepository.deleteById(order.getId());
    }
}
