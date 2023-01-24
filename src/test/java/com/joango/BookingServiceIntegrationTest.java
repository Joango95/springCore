package com.joango;

import com.joango.exception.EventNotFoundException;
import com.joango.exception.TicketNotFoundException;
import com.joango.facade.BookingFacade;
import com.joango.model.Category;
import com.joango.model.DTO.EventDTO;
import com.joango.model.DTO.TicketDTO;
import com.joango.model.DTO.UserDTO;
import com.joango.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations="classpath:application-test.properties")
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
public class BookingServiceIntegrationTest {

    @Autowired
    BookingFacade bookingFacade;

    @Test
    @DisplayName("Should get a user by Id")
    void getUserById() {
        UserDTO user = bookingFacade.getUserById(1);
        assertEquals("Lewis Burton", user.getName());
    }

    @Test
    @DisplayName("Should throw exception when user not found by Id")
    void getUserByIdException() {
        assertThrows(UserNotFoundException.class, () -> bookingFacade.getUserById(348l));
    }

    @Test
    @DisplayName("Should get a user by Email")
    void getUserByEmail() {
        UserDTO user = bookingFacade.getUserByEmail("tellus.aenean@outlook.couk");
        assertEquals("Jaden Harrison", user.getName());
    }

    @Test
    @DisplayName("Should throw exception when user not found by email")
    void getUserByEmailException() {
        assertThrows(UserNotFoundException.class, () -> bookingFacade.getUserByEmail("GEORGEEMAILNOTEXIST@GMAIL.COM"));
    }

    @Test
    @DisplayName("Should get a list of users by Name")
    void getUsersByName() {
        List<UserDTO> users = bookingFacade.getUsersByName("Lewis Burton");
        users.stream().forEach(user -> {
            assertEquals("Lewis Burton", user.getName());
        });
    }

    @Test
    @DisplayName("Should create a new user")
    void createUser() {
        UserDTO user = new UserDTO(
            "George",
            "Email"
        );
        UserDTO newUser = bookingFacade.createUser(user);

        assertEquals("George", user.getName());
    }

    @Test
    @DisplayName("Should update a user")
    void updateUser() {
        UserDTO user = bookingFacade.getUserById(15);
        user.setName("George Gomez");
        UserDTO updatedUser = bookingFacade.updateUser(user);

        assertEquals("George Gomez", user.getName());
    }

    @Test
    @DisplayName("Should delete a user")
    void deleteUser() {
        bookingFacade.deleteUser(5);
        assertThrows(UserNotFoundException.class, () -> bookingFacade.getUserByEmail("parturient.montes.nascetur@aol.net"));
    }

    @Test
    @DisplayName("Should get an event by Id")
    void getEventById() {
        EventDTO event = bookingFacade.getEventById(1);
        assertEquals("posuere, enim", event.getTitle());
    }

    @Test
    @DisplayName("Should throw exception when event not found by Id")
    void getEventByIdException() {
        assertThrows(EventNotFoundException.class, () -> bookingFacade.getEventById(348l));
    }

    @Test
    @DisplayName("Should get a list of event by title")
    void getEventsByTitle() {
        List<EventDTO> events = bookingFacade.getEventsByTitle("posuere, enim");
        events.stream().forEach(event -> {
            assertEquals("posuere, enim", event.getTitle());
        });
    }

    @Test
    @DisplayName("Should get a list of event by day")
    void getEventsByDay() {
        List<EventDTO> events = bookingFacade.getEventsForDay(java.sql.Date.valueOf("2023-08-10"));
        events.stream().forEach(event -> {
            assertEquals(java.sql.Date.valueOf("2023-08-10"), event.getDate());
        });
    }

    @Test
    @DisplayName("Should create a new event")
    void createEvent() {
        EventDTO event = new EventDTO(
            "World cup quarters",
            java.sql.Date.valueOf("2023-10-10")
        );
        EventDTO newEvent = bookingFacade.createEvent(event);

        assertEquals("World cup quarters", event.getTitle());
    }

    @Test
    @DisplayName("Should update an event")
    void updateEvent() {
        EventDTO event = bookingFacade.getEventById(6);
        event.setTitle("World cup final");

        EventDTO updatedEvent = bookingFacade.updateEvent(event);

        assertEquals("World cup final", event.getTitle());
    }

    @Test
    @DisplayName("Should delete an event")
    void deleteEvent() {
        bookingFacade.deleteEvent(5);
        List<EventDTO> events = bookingFacade.getEventsByTitle("arcu eu");
        assertEquals(0, events.size());
    }

    @Test
    @DisplayName("Should get ticket by Id")
    void getTicketById() {
        TicketDTO ticket = bookingFacade.getTicketById(5l);
        assertEquals(1, ticket.getEventId());
        assertEquals(12, ticket.getUserId());
    }

    @Test
    @DisplayName("Should get a list of tickets by user")
    void getTicketsByUser() {
        List<TicketDTO> tickets = bookingFacade.getBookedTicketsByUserId(1l);
        tickets.stream().forEach(ticket -> {
            assertEquals(1, ticket.getUserId());
        });
    }

    @Test
    @DisplayName("Should get a list of tickets by event")
    void getTicketsByEvent() {
        List<TicketDTO> tickets = bookingFacade.getBookedTicketsByEventId(1l);
        tickets.stream().forEach(ticket -> {
            assertEquals(1, ticket.getEventId());
        });
    }

    @Test
    @DisplayName("Should throw exception when ticket not found by Id")
    void getTicketByIdException() {
        assertThrows(TicketNotFoundException.class, () -> bookingFacade.getTicketById(348l));
    }

    @Test
    @DisplayName("Should book a ticket")
    void bookTicket() {
        TicketDTO ticket = bookingFacade.bookTicket(1l, 4l, 4, Category.PREMIUM);
        TicketDTO createdTicket = bookingFacade.getTicketById(ticket.getId());
        List<TicketDTO> userTickets = bookingFacade.getBookedTicketsByUserId(1L);
        assertEquals(4l, ticket.getEventId());
        assertEquals(1l, ticket.getUserId());
        assertEquals(4, ticket.getPlace());
        assertEquals(Category.PREMIUM, ticket.getCategory());
        assertEquals(createdTicket.getId(), ticket.getId());
        assertTrue(userTickets.contains(ticket));
    }
}
