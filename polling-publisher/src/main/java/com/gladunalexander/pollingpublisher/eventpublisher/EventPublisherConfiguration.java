package com.gladunalexander.pollingpublisher.eventpublisher;

import com.gladunalexander.pollingpublisher.Event;
import com.gladunalexander.pollingpublisher.eventstore.EventStore;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
class EventPublisherConfiguration {

    @Bean
    EventPublisher kafkaPublisher(KafkaTemplate<String, Event> kafkaTemplate) {
        return new KafkaEventPublisher(kafkaTemplate);
    }

    @Bean
    @Primary
    EventPublisher storeAndForwardPublisher(EventStore eventStore,
                                            @Qualifier("kafkaPublisher") EventPublisher eventPublisher) {
        return new StoreAndForwardEventPublisher(eventStore, eventPublisher);
    }
}
