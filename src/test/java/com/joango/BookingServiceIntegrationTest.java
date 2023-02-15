package com.joango;

import com.joango.exception.EventNotFoundException;
import com.joango.exception.InsufficientFoundsException;
import com.joango.exception.TicketNotFoundException;
import com.joango.facade.BookingFacade;
import com.joango.facade.UserAccountFacade;
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

    @Autowired
    UserAccountFacade userAccountFacade;

    @Test
    @DisplayName("Should get a user by Id")
    void getUserById() {
        UserDTO user = bookingFacade.getUserById(1);
        assertEquals("Lewis Burton", user.getName());
    }

    @Test
    @DisplayName("Should throw exception when user not found by Id")
    void getUserByIdException() {
        assertThrows(UserNotFoundException.class, () -> bookingFacade.getUserById(348L));
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
        users.forEach(user -> assertEquals("Lewis Burton", user.getName()));
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
        assertThrows(EventNotFoundException.class, () -> bookingFacade.getEventById(348L));
    }

    @Test
    @DisplayName("Should get a list of event by title")
    void getEventsByTitle() {
        List<EventDTO> events = bookingFacade.getEventsByTitle("posuere, enim");
        events.forEach(event -> assertEquals("posuere, enim", event.getTitle()));
    }

    @Test
    @DisplayName("Should get a list of event by day")
    void getEventsByDay() {
        List<EventDTO> events = bookingFacade.getEventsForDay(java.sql.Date.valueOf("2023-08-10"));
        events.forEach(event -> assertEquals(java.sql.Date.valueOf("2023-08-10"), event.getDate()));
    }

    @Test
    @DisplayName("Should create a new event")
    void createEvent() {
        EventDTO event = new EventDTO(
            "World cup quarters",
            java.sql.Date.valueOf("2023-10-10"),
            1234
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
        List<TicketDTO> tickets = bookingFacade.getBookedTicketsByUserId(1L);
        tickets.forEach(ticket -> assertEquals(1, ticket.getUserId()));
    }

    @Test
    @DisplayName("Should get a list of tickets by event")
    void getTicketsByEvent() {
        List<TicketDTO> tickets = bookingFacade.getBookedTicketsByEventId(1L);
        tickets.forEach(ticket -> assertEquals(1, ticket.getEventId()));
    }

    @Test
    @DisplayName("Should throw exception when ticket not found by Id")
    void getTicketByIdException() {
        assertThrows(TicketNotFoundException.class, () -> bookingFacade.getTicketById(348L));
    }

    @Test
    @DisplayName("Should book a ticket for a user with enough balance")
    void bookTicketWithEnoughBalance() {
        //given
        Long userId = 4L;
        Long eventId = 1L;
        Integer place = 4;
        Category category = Category.PREMIUM;
        Integer previousUserBalance = userAccountFacade.getUserAccountByUserId(userId).get().getUserBalance();
        Integer eventPrice = bookingFacade.getEventById(eventId).getTicketPrice();

        //when

        TicketDTO ticket = bookingFacade.bookTicket(userId, eventId, place, category);
        TicketDTO createdTicket = bookingFacade.getTicketById(ticket.getId());
        List<TicketDTO> userTickets = bookingFacade.getBookedTicketsByUserId(userId);

        //should
        Integer newUserBalance = userAccountFacade.getUserAccountByUserId(userId).get().getUserBalance();

        assertEquals(eventId, ticket.getEventId());
        assertEquals(userId, ticket.getUserId());
        assertEquals(place, ticket.getPlace());
        assertEquals(category, ticket.getCategory());

        assertEquals(createdTicket.getId(), ticket.getId());

        assertTrue(userTickets.contains(ticket));

        assertEquals(previousUserBalance - eventPrice, newUserBalance);
    }

    @Test
    @DisplayName("Should book a ticket for a user with enough balance")
    void bookTicketWithInsufficientFounds() {
        //given
        Long userId = 1L;
        Long eventId = 4L;
        Integer place = 4;
        Category category = Category.PREMIUM;

        //should
        assertThrows(InsufficientFoundsException.class, () -> bookingFacade.bookTicket(userId, eventId, place, category));
    }
}
