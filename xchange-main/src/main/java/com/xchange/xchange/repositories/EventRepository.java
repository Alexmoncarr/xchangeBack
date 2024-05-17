package com.xchange.xchange.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.xchange.xchange.models.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByTitle(String title);
}
