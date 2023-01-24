package com.joango.controller;

import com.joango.facade.BookingFacade;
import com.joango.model.DTO.EventDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.Instant;
import java.util.List;

@Controller
@RequestMapping(path = "/event")
public class EventController {

    @Autowired
    private BookingFacade bookingFacade;

    @GetMapping("/{id}")
    public String getEventById(@PathVariable(value = "id") long id, Model model) {
        EventDTO event = bookingFacade.getEventById(id);
        model.addAttribute("events", event);
        return "events";
    }

    @GetMapping("/title/{title}")
    public String getEventsByTitle(@PathVariable(value = "title") String title, Model model) {
        List<EventDTO> events = bookingFacade.getEventsByTitle(title);
        model.addAttribute("events", events);
        return "events";
    }

    @GetMapping("/day/{day}")
    public String getEventsForDay(@PathVariable(value = "day") long day, Model model) {
        List<EventDTO> events = bookingFacade.getEventsForDay(Date.from(Instant.ofEpochMilli(day)));
        model.addAttribute("events", events);
        return "events";
    }

    @PostMapping()
    public EventDTO createEvent(@RequestBody EventDTO evenDto, Model model) {
        return bookingFacade.createEvent(evenDto);
    }

    @PostMapping("/update")
    public EventDTO updateEvent(@RequestBody EventDTO evenDto, Model model) {
        return bookingFacade.updateEvent(evenDto);
    }

    @PostMapping("/delete")
    public Boolean deleteEvent(@RequestBody Long eventId, Model model) {
        bookingFacade.deleteEvent(eventId);
        return true;
    }
}
