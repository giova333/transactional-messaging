package com.gladunalexander.delivery.debezium;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gladunalexander.delivery.eventpublisher.EventPublisher;
import com.gladunalexander.delivery.eventstore.EventStore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConditionalOnProperty(name = "transactional.outbox.strategy", havingValue = "debezium")
@PropertySource("classpath:debezium.properties")
public class DebeziumConfiguration {

    @Bean
    @Primary
    EventPublisher storeAndForwardPublisher(EventStore eventStore,
                                            EventPublisher eventPublisher,
                                            ObjectMapper objectMapper) {
        return new DebeziumEventPublisher(eventPublisher, eventStore, objectMapper);
    }
}
