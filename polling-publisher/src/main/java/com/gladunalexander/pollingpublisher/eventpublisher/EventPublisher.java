package com.gladunalexander.pollingpublisher.eventpublisher;

import com.gladunalexander.pollingpublisher.Event;

public interface EventPublisher {

    void publish(Event event);
}
