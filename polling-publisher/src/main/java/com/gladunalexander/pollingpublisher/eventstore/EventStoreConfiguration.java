package com.gladunalexander.pollingpublisher.eventstore;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class EventStoreConfiguration {

    @Bean
    EventSerializer eventSerializer(ObjectMapper objectMapper) {
        return new EventSerializer(objectMapper);
    }

    @Bean
    EventStore eventStore(EventSerializer eventSerializer,
                          EventRepository eventRepository) {
        return new EventStore(eventRepository, eventSerializer);
    }
}
