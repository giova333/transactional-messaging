package com.gladunalexander.delivery.eventpublisher;

import com.gladunalexander.delivery.Event;
import com.gladunalexander.delivery.Topics;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;

@Slf4j
@RequiredArgsConstructor
class KafkaEventPublisher implements EventPublisher {

    private final KafkaTemplate<String, Event> kafkaTemplate;

    @Override
    public void publish(Event event) {
        log.info("Publishing event: {}", event);
        kafkaTemplate.send(
                Topics.ORDER_CREATED,
                event.getId().toString(),
                event);
    }
}
