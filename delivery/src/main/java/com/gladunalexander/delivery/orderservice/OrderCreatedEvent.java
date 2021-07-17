package com.gladunalexander.delivery.orderservice;

import com.gladunalexander.delivery.Event;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Value
@Builder
@AllArgsConstructor
public class OrderCreatedEvent implements Event {

    public static final String TYPE = "order-created";

    UUID id = UUID.randomUUID();
    Instant occurredAt = Instant.now();
    String type = TYPE;
    Long orderId;
    List<String> products;

    public OrderCreatedEvent(Order order) {
        this.orderId = order.getId();
        this.products = order.getProducts();
    }
}
