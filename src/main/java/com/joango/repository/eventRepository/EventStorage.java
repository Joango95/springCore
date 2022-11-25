package com.joango.repository.eventRepository;


import com.joango.model.Event;
import com.joango.storage.EventMapStorage;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class EventStorage implements EventRepository {

    private long lastEventId;

    @Autowired
    private EventMapStorage eventMapStorage;

    @Override
    public Event getEventById(long eventId) {
        return eventMapStorage.eventMap.get(eventId);
    }

    @Override
    public List<Event> getEventsByTitle(String title, int pageSize, int pageNum) {
        return eventMapStorage.eventMap.entrySet()
            .stream()
            .map(eventMap -> eventMap.getValue())
            .filter(event -> event.getTitle() == title)
            .collect(Collectors.toList());
    }

    @Override
    public List<Event> getEventsForDay(Date day, int pageSize, int pageNum) {
        return eventMapStorage.eventMap.entrySet()
            .stream()
            .map(eventMap -> eventMap.getValue())
            .filter(event -> event.getDate().equals(day))
            .collect(Collectors.toList());
    }

    @Override
    public Event createEvent(Event event) {
        lastEventId++;
        event.setId(lastEventId);
        eventMapStorage.updateEventData();
        return eventMapStorage.eventMap.put(String.valueOf(lastEventId), event);
    }

    @Override
    public Event updateEvent(Event event) {
        eventMapStorage.updateEventData();
        return eventMapStorage.eventMap.replace(String.valueOf(event.getId()), event);
    }

    @Override
    public boolean deleteEvent(long eventId) {
        eventMapStorage.updateEventData();
        return eventMapStorage.eventMap.remove(eventId) != null;
    }
}
