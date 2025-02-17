package com.example.payment.consumer;

import com.example.payment.entity.Order;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PaymentEventConsumer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public PaymentEventConsumer(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "order-created", groupId = "payment-group")
    public void processPayment(String orderJson) throws Exception {
        Order order = objectMapper.readValue(orderJson, Order.class);

        if (Math.random() > 0.7) { // Simulated failure
            kafkaTemplate.send("payment-failed", orderJson);
        } else {
            kafkaTemplate.send("payment-success", orderJson);
        }
    }
}
