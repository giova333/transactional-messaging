package com.gladunalexander.delivery.eventpublisher;

import com.gladunalexander.delivery.Event;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
class EventPublisherConfiguration {

    @Bean
    EventPublisher kafkaPublisher(KafkaTemplate<String, Event> kafkaTemplate) {
        return new KafkaEventPublisher(kafkaTemplate);
    }

}
