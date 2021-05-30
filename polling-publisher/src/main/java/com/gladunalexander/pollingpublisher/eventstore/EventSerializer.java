package com.gladunalexander.pollingpublisher.eventstore;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gladunalexander.pollingpublisher.Event;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@RequiredArgsConstructor
class EventSerializer {

    private final ObjectMapper objectMapper;

    @SneakyThrows
    String serialize(Event event) {
        return objectMapper.writeValueAsString(event);
    }

    @SneakyThrows
    Event deserialize(String json) {
        return objectMapper.readValue(json, Event.class);
    }

}
