package com.gladunalexander.delivery.eventstore;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "outbox")
@Data
@NoArgsConstructor
class EventDescriptor {

    @Id
    @Type(type = "uuid-char")
    private UUID id;

    private String content;

    private Instant occurredAt;

    private String type;

    @Enumerated(value = EnumType.STRING)
    private Status status = Status.CREATED;

    enum Status {
        CREATED, PUBLISHED;
    }

    EventDescriptor(UUID id, String content, Instant occurredAt, String type) {
        this.id = id;
        this.content = content;
        this.occurredAt = occurredAt;
        this.type = type;
    }
}
