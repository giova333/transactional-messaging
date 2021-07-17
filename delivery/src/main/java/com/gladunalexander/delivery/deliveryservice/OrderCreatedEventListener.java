package com.gladunalexander.delivery.deliveryservice;

import com.gladunalexander.delivery.Event;
import com.gladunalexander.delivery.Topics;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
class OrderCreatedEventListener {

    @KafkaListener(topics = Topics.ORDER_CREATED)
    public void listen(Event event) {
        log.info("Delivering order {}", event);
    }
}
