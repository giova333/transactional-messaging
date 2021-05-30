package com.gladunalexander.pollingpublisher.deliveryservice;

import com.gladunalexander.pollingpublisher.Event;
import com.gladunalexander.pollingpublisher.Topics;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderCreatedEventListener {

    @KafkaListener(topics = Topics.ORDER_CREATED)
    public void listen(Event event) {
        log.info("Delivering order {}", event);
    }
}
