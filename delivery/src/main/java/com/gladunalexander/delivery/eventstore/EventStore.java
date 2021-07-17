package com.gladunalexander.delivery.eventstore;

import com.gladunalexander.delivery.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class EventStore {

    private final EventRepository eventRepository;
    private final EventSerializer eventSerializer;

    public void save(Event event) {
        eventRepository.save(
                new EventDescriptor(
                        event.getId(),
                        eventSerializer.serialize(event),
                        event.getOccurredAt(),
                        event.getType()
                )
        );
    }

    public List<Event> toPublish() {
        return eventRepository.findByStatus(EventDescriptor.Status.CREATED)
                .stream()
                .map(EventDescriptor::getContent)
                .map(eventSerializer::deserialize)
                .collect(Collectors.toList());
    }

    @Transactional
    public void markAsPublished(List<Event> events) {
        if (CollectionUtils.isEmpty(events)) return;
        eventRepository.markAsPublished(
                events.stream()
                        .map(Event::getId)
                        .collect(Collectors.toList())
        );
    }


}
