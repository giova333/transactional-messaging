package com.gladunalexander.delivery.eventpublisher;

import com.gladunalexander.delivery.Event;

public interface EventPublisher {

    void publish(Event event);
}
