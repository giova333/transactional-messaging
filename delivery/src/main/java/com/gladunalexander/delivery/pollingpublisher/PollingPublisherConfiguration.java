package com.gladunalexander.delivery.pollingpublisher;

import com.gladunalexander.delivery.eventpublisher.EventPublisher;
import com.gladunalexander.delivery.eventstore.EventStore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ConditionalOnProperty(name = "transactional.outbox.strategy", havingValue = "polling-publisher")
class PollingPublisherConfiguration {

    @Bean
    @Primary
    EventPublisher storeAndForwardPublisher(EventStore eventStore,
                                            EventPublisher eventPublisher) {
        return new StoreAndForwardEventPublisher(eventStore, eventPublisher);
    }
}
