package com.joango.repository;

import com.joango.model.Event;
import com.joango.model.Ticket;
import com.joango.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> getTicketsByUserId(Long userId);
    List<Ticket> getTicketsByEventId(Long eventId);

}
