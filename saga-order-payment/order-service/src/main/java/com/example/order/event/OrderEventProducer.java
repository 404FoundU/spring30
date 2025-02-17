package com.example.order.event;

import com.example.order.entity.Order;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderEventProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public OrderEventProducer(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public void sendOrderEvent(Order order) {
        try {
            kafkaTemplate.send("order-created", objectMapper.writeValueAsString(order));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
