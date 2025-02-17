package com.example.order.service;

import com.example.order.entity.Order;
import com.example.order.event.OrderEventProducer;
import com.example.order.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderEventProducer orderEventProducer;

    public OrderService(OrderRepository orderRepository, OrderEventProducer orderEventProducer) {
        this.orderRepository = orderRepository;
        this.orderEventProducer = orderEventProducer;
    }

    @Transactional
    public void createOrder(String product, double amount) {
        Order order = new Order(null, product, amount, "IN_PROGRESS");
        orderRepository.save(order);
        orderEventProducer.sendOrderEvent(order);
    }
}
