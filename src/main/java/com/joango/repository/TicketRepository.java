package com.joango.repository;

import com.joango.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> getTicketsByUserId(Long userId);
    List<Ticket> getTicketsByEventId(Long eventId);

}
