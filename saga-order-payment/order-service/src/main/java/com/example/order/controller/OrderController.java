package com.example.order.controller;

import com.example.order.service.OrderService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/create")
    public String createOrder(@RequestParam String product, @RequestParam double amount) {
        orderService.createOrder(product, amount);
        return "Order placed successfully!";
    }
}
