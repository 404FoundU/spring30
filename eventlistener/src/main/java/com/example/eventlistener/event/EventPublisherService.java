package com.example.eventlistener.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class EventPublisherService {
    private final ApplicationEventPublisher applicationEventPublisher;

    public EventPublisherService(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void publishEvent(String message) {
        System.out.println("Publishing event: " + message);
        CustomEvent event = new CustomEvent(this, message);
        applicationEventPublisher.publishEvent(event);
    }
}

