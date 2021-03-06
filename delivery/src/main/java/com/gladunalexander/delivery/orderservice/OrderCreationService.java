package com.gladunalexander.delivery.orderservice;

import com.gladunalexander.delivery.eventpublisher.EventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderCreationService {

    private final OrderRepository orderRepository;
    private final EventPublisher eventPublisher;

    @Transactional
    public void create(Order order) {
        orderRepository.save(order);
        eventPublisher.publish(new OrderCreatedEvent(order));
    }
}
