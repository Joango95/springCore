package com.joango.facade;

import com.joango.exception.EventNotFoundException;
import com.joango.exception.TicketNotFoundException;
import com.joango.exception.UserNotFoundException;
import com.joango.model.Category;
import com.joango.model.DTO.EventDTO;
import com.joango.model.DTO.TicketDTO;
import com.joango.model.DTO.UserDTO;
import com.joango.model.Event;
import com.joango.model.Ticket;
import com.joango.model.User;
import com.joango.service.EventService;
import com.joango.service.TicketService;
import com.joango.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookingFacadeImpl implements BookingFacade {

    private static final ModelMapper mapper = new ModelMapper();

    @Autowired
    private UserService userService;

    @Autowired
    private EventService eventService;

    @Autowired
    private TicketService ticketService;

    public BookingFacadeImpl(
        UserService userService,
        EventService eventService,
        TicketService ticketService
    ) {
        this.userService = userService;
        this.eventService = eventService;
        this.ticketService = ticketService;
    }

    @Override
    public EventDTO getEventById(long eventId) {
        Event event = eventService.getEventById(eventId).orElseThrow(EventNotFoundException::new);
        return mapper.map(event, EventDTO.class);
    }

    @Override
    public List<EventDTO> getEventsByTitle(String title) {
        List<Event> events = eventService.getEventsByTitle(title);
        return events.stream()
            .map(event -> mapper.map(event, EventDTO.class))
            .collect(Collectors.toList());
    }

    @Override
    public List<EventDTO> getEventsForDay(Date day) {
        return eventService.getEventsForDay(day).stream()
            .map(event -> mapper.map(event, EventDTO.class))
            .collect(Collectors.toList());
    }

    @Override
    public EventDTO createEvent(EventDTO eventDto) {
        Event event = mapper.map(eventDto, Event.class);
        Event newEvent = eventService.createEvent(event);
        return mapper.map(newEvent, EventDTO.class);
    }

    @Override
    public EventDTO updateEvent(EventDTO eventDto) {
        Event event = mapper.map(eventDto, Event.class);
        Event eventUpdated = eventService.updateEvent(event);
        return mapper.map(eventUpdated, EventDTO.class);
    }

    @Override
    public void deleteEvent(long eventId) {
        eventService.deleteEvent(eventId);
    }

    @Override
    public UserDTO getUserById(long userId) {
        User user = userService.getUserById(userId).orElseThrow(UserNotFoundException::new);
        return mapper.map(user, UserDTO.class);
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        User user =  userService.getUserByEmail(email).orElseThrow(UserNotFoundException::new);
        return mapper.map(user, UserDTO.class);
    }

    @Override
    public List<UserDTO> getUsersByName(String name) {
        return userService.getUsersByName(name).stream()
            .map(user -> mapper.map(user, UserDTO.class))
            .collect(Collectors.toList());
    }

    @Override
    public UserDTO createUser(UserDTO userDto) {
        User user = mapper.map(userDto, User.class);
        User newUser = userService.createUser(user);
        return mapper.map(newUser, UserDTO.class);
    }

    @Override
    public UserDTO updateUser(UserDTO userDto) {
        User user = mapper.map(userDto, User.class);
        User userUpdated = userService.updateUser(user);
        return mapper.map(userUpdated, UserDTO.class);
    }

    @Override
    public void deleteUser(long userId) {
        userService.deleteUser(userId);
    }

    @Override
    public TicketDTO getTicketById(long id){
        Ticket ticket = ticketService.getTicketById(id).orElseThrow(TicketNotFoundException::new);
        return mapper.map(ticket, TicketDTO.class);
    }

    @Override
    public TicketDTO bookTicket(long userId, long eventId, int place, Category category) {
        Ticket newTicket = ticketService.bookTicket(userId, eventId, place, category);
        return mapper.map(newTicket, TicketDTO.class);
    }

    @Override
    public List<TicketDTO> getBookedTickets(UserDTO userDto) {
        User user = mapper.map(userDto, User.class);
        return ticketService.getBookedTickets(user).stream()
            .map(ticket -> mapper.map(ticket, TicketDTO.class))
            .collect(Collectors.toList());
    }

    @Override
    public List<TicketDTO> getBookedTickets(EventDTO eventDto) {
        Event event = mapper.map(eventDto, Event.class);
        return ticketService.getBookedTickets(event).stream()
            .map(ticket -> mapper.map(ticket, TicketDTO.class))
            .collect(Collectors.toList());
    }

    @Override
    public void cancelTicket(long ticketId) {
        ticketService.cancelTicket(ticketId);
    }
}
