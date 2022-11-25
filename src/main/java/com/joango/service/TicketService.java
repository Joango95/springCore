package com.joango.service;

import com.joango.model.Event;
import com.joango.model.Ticket;
import com.joango.model.User;
import com.joango.repository.ticketRepository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TicketService {

   @Autowired
   private TicketRepository ticketRepository;


   public Ticket bookTicket(long userId, long eventId, int place, Ticket.Category category){
      return ticketRepository.bookTicket(userId, eventId, place, category);
   }

   public List<Ticket> getBookedTickets(User user, int pageSize, int pageNum){
      return ticketRepository.getBookedTickets(user, pageSize, pageNum);
   }

   public List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum){
      return ticketRepository.getBookedTickets(event, pageSize, pageNum);
   }

   public boolean cancelTicket(long ticketId){
      return ticketRepository.cancelTicket(ticketId);
   }
}
