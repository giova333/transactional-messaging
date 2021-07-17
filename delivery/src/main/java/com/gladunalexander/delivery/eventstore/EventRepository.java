package com.gladunalexander.delivery.eventstore;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

interface EventRepository extends JpaRepository<EventDescriptor, Long> {

    List<EventDescriptor> findByStatus(EventDescriptor.Status status);

    @Modifying
    @Query("update EventDescriptor e set e.status='PUBLISHED' where e.id in :eventIds")
    void markAsPublished(@Param("eventIds") List<UUID> eventIds);
}
