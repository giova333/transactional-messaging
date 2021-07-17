package com.gladunalexander.delivery.debezium;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gladunalexander.debezium.annotation.DebeziumEventListener;
import com.gladunalexander.delivery.Event;
import com.gladunalexander.delivery.eventpublisher.EventPublisher;
import com.gladunalexander.delivery.eventstore.EventStore;
import io.debezium.engine.ChangeEvent;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@RequiredArgsConstructor
public class DebeziumEventPublisher implements EventPublisher {

    private final EventPublisher eventPublisher;
    private final EventStore eventStore;
    private final ObjectMapper objectMapper;

    @Override
    public void publish(Event event) {
        eventStore.save(event);
    }

    @SneakyThrows
    @DebeziumEventListener
    public void listen(ChangeEvent<String, String> event) {
        var orderCreatedEvent = toOrderCreatedEvent(event);
        eventPublisher.publish(orderCreatedEvent);
    }

    private Event toOrderCreatedEvent(ChangeEvent<String, String> event) throws JsonProcessingException {
        var jsonNode = objectMapper.readTree(event.value());
        var content = jsonNode.get("payload").get("after").get("content");
        return objectMapper.readValue(content.textValue(), Event.class);
    }
}
