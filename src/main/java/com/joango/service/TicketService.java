package com.joango.service;

import com.joango.model.Category;
import com.joango.model.Event;
import com.joango.model.Ticket;
import com.joango.model.User;
import com.joango.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

   @Autowired
   private TicketRepository ticketRepository;

   public Optional<Ticket> getTicketById(long id){
      return ticketRepository.findById(id);
   }

   public Ticket bookTicket(long userId, long eventId, int place, Category category){
      Ticket newTicket = new Ticket(userId, eventId, place, category);
      return ticketRepository.save(newTicket);
   }

   public List<Ticket> getBookedTickets(User user){
      return ticketRepository.getTicketsByUserId(user.getId());
   }

   public List<Ticket> getBookedTickets(Event event){
      return ticketRepository.getTicketsByEventId(event.getId());
   }

   public void cancelTicket(long ticketId){
      ticketRepository.deleteById(ticketId);
   }
}
