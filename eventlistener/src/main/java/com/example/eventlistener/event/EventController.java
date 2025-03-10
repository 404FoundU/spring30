package com.example.eventlistener.event;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventController {
    private final EventPublisherService eventPublisherService;

    public EventController(EventPublisherService eventPublisherService) {
        this.eventPublisherService = eventPublisherService;
    }


    //http://localhost:8080/trigger-event?message=HelloEvent
    @GetMapping("/trigger-event")
    public String triggerEvent(@RequestParam String message) {
        eventPublisherService.publishEvent(message);
        return "Event triggered with message: " + message;
    }
}

