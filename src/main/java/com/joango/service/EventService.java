package com.joango.service;

import com.joango.model.Event;
import com.joango.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public Optional<Event> getEventById(long eventId) {
        return eventRepository.findById(eventId);
    }

    public List<Event> getEventsByTitle(String title) {
        return eventRepository.getEventsByTitle(title);
    }

    public List<Event> getEventsForDay(Date date) {
        return eventRepository.getEventsByDate(date);
    }

    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    public Event updateEvent(Event event) {
        return eventRepository.save(event);
    }

    public void deleteEvent(long eventId) {
        eventRepository.deleteById(eventId);
    }

}
