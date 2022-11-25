package com.joango.service;

import com.joango.model.Event;
import com.joango.model.User;
import com.joango.repository.eventRepository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public Event getEventById(long eventId) {
        return eventRepository.getEventById(eventId);
    }

    public List<Event> getEventsByTitle(String title, int pageSize, int pageNum) {
        return eventRepository.getEventsByTitle(title, pageSize, pageNum);
    }

    public List<Event> getEventsForDay(Date day, int pageSize, int pageNum) {
        return eventRepository.getEventsForDay(day, pageSize, pageNum);
    }

    public Event createEvent(Event event) {
        return eventRepository.createEvent(event);
    }

    public Event updateEvent(Event event) {
        return eventRepository.updateEvent(event);
    }

    public boolean deleteEvent(long eventId) {
        return eventRepository.deleteEvent(eventId);
    }

}
