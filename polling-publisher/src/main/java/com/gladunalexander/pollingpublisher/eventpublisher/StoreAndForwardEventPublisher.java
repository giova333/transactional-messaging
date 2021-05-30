package com.gladunalexander.pollingpublisher.eventpublisher;

import com.gladunalexander.pollingpublisher.Event;
import com.gladunalexander.pollingpublisher.eventstore.EventStore;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@RequiredArgsConstructor
class StoreAndForwardEventPublisher implements EventPublisher {

    private final EventStore eventStore;
    private final EventPublisher kafkaEventPublisher;

    @Override
    public void publish(Event event) {
        eventStore.save(event);
    }

    @Scheduled(fixedRate = 3000)
    public void publishAllPeriodically() {
        List<Event> events = eventStore.toPublish();
        events.forEach(kafkaEventPublisher::publish);
        eventStore.markAsPublished(events);
    }
}
