package com.joango.facade;

import com.joango.model.Category;
import com.joango.model.DTO.EventDTO;
import com.joango.model.DTO.TicketDTO;
import com.joango.model.DTO.UserDTO;

import java.util.Date;
import java.util.List;

/**
 * Groups together all operations related to tickets booking.
 * Created by maksym_govorischev.
 */
public interface BookingFacade {

    /**
     * Gets event by its id.
     * @return Event.
     */
    EventDTO getEventById(long eventId);

    /**
     * Get list of events by matching title. Title is matched using 'contains' approach.
     * In case nothing was found, empty list is returned.
     * @param title Event title or it's part.
     * @return List of events.
     */
    List<EventDTO> getEventsByTitle(String title);

    /**
     * Get list of events for specified day.
     * In case nothing was found, empty list is returned.
     * @param day Date object from which day information is extracted.
     * @return List of events.
     */
    List<EventDTO> getEventsForDay(Date day);

    /**
     * Creates new event. Event id should be auto-generated.
     * @param event Event data.
     * @return Created Event object.
     */
    EventDTO createEvent(EventDTO event);

    /**
     * Updates event using given data.
     * @param event Event data for update. Should have id set.
     * @return Updated Event object.
     */
    EventDTO updateEvent(EventDTO event);

    /**
     * Deletes event by its id.
     * @param eventId Event id.
     * @return Flag that shows whether event has been deleted.
     */
    void deleteEvent(long eventId);

    /**
     * Gets user by its id.
     * @return User.
     */
    UserDTO getUserById(long userId);

    /**
     * Gets user by its email. Email is strictly matched.
     * @return User.
     */
    UserDTO getUserByEmail(String email);

    /**
     * Get list of users by matching name. Name is matched using 'contains' approach.
     * In case nothing was found, empty list is returned.
     * @param name Users name or it's part.
     * @return List of users.
     */
    List<UserDTO> getUsersByName(String name);

    /**
     * Creates new user. User id should be auto-generated.
     * @param user User data.
     * @return Created User object.
     */
    UserDTO createUser(UserDTO user);

    /**
     * Updates user using given data.
     * @param user User data for update. Should have id set.
     * @return Updated User object.
     */
    UserDTO updateUser(UserDTO user);

    /**
     * Deletes user by its id.
     * @param userId User id.
     * @return Flag that shows whether user has been deleted.
     */
    void deleteUser(long userId);

    TicketDTO getTicketById(long id);

    /**
     * Book ticket for a specified event on behalf of specified user.
     * @param userId User Id.
     * @param eventId Event Id.
     * @param place Place number.
     * @param category Service category.
     * @return Booked ticket object.
     * @throws java.lang.IllegalStateException if this place has already been booked.
     */
    TicketDTO bookTicket(long userId, long eventId, int place, Category category);

    TicketDTO bookTicket(TicketDTO ticket);

    /**
     * Get all booked tickets for specified user. Tickets should be sorted by event date in descending order.
     * @param user User
     * @return List of Ticket objects.
     */
    List<TicketDTO> getBookedTicketsByUserId(Long userId);

    /**
     * Get all booked tickets for specified event. Tickets should be sorted in by user email in ascending order.
     * @param event Event
     * @return List of Ticket objects.
     */
    List<TicketDTO> getBookedTicketsByEventId(Long eventId);

    /**
     * Cancel ticket with a specified id.
     * @param ticketId Ticket id.
     * @return Flag whether anything has been canceled.
     */
    void cancelTicket(long ticketId);
}
