package com.joango.repository.ticketRepository;

import com.joango.DAO.TicketDAO;
import com.joango.model.Event;
import com.joango.model.Ticket;
import com.joango.model.User;
import com.joango.storage.TicketMapStorage;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class TicketStorage implements TicketRepository {

    private long lastTicketId;

    @Autowired
    private TicketMapStorage ticketMapStorage;


    @Override
    public Ticket bookTicket(
        long userId,
        long eventId,
        int place,
        Ticket.Category category
    ) {
        lastTicketId++;
        Ticket newTicket = new TicketDAO(
            lastTicketId,
            userId,
            eventId,
            place,
            category
        );
        ticketMapStorage.updateTicketData();
        return ticketMapStorage.ticketMap.put(String.valueOf(lastTicketId), newTicket);
    }

    @Override
    public List<Ticket> getBookedTickets(User user, int pageSize, int pageNum) {
        return ticketMapStorage.ticketMap.entrySet()
            .stream()
            .map(ticketMap -> ticketMap.getValue())
            .filter(ticket -> ticket.getUserId() == user.getId())
            .collect(Collectors.toList());
    }

    @Override
    public List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum) {
        return ticketMapStorage.ticketMap.entrySet()
            .stream()
            .map(ticketMap -> ticketMap.getValue())
            .filter(ticket -> ticket.getEventId() == event.getId())
            .collect(Collectors.toList());
    }

    @Override
    public boolean cancelTicket(long ticketId) {
        ticketMapStorage.updateTicketData();
        return ticketMapStorage.ticketMap.remove(ticketId) != null;
    }
}
