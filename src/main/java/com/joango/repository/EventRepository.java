package com.joango.repository;

import com.joango.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> getEventsByTitle(String title);
    List<Event> getEventsByDate(Date date);
}
