package com.joango.repository;

import com.joango.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;


public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> getEventsByTitle(String title);
    List<Event> getEventsByDate(Date date);
}
