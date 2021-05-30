package com.gladunalexander.pollingpublisher;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.gladunalexander.pollingpublisher.orderservice.OrderCreatedEvent;

import java.time.Instant;
import java.util.UUID;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(name = OrderCreatedEvent.TYPE, value = OrderCreatedEvent.class),
})
public interface Event {

    UUID getId();

    String getType();

    Instant getOccurredAt();
}
