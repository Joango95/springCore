package com.joango.controller;

import com.joango.facade.BookingFacade;
import com.joango.model.Category;
import com.joango.model.DTO.TicketDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(path = "/ticket")
public class TicketController {

    @Autowired
    private BookingFacade bookingFacade;

    @GetMapping("/{id}")
    public String getTicketById(@PathVariable long id, Model model) {
        TicketDTO ticket = bookingFacade.getTicketById(id);
        model.addAttribute("tickets", ticket);
        return "tickets";
    }

    @PostMapping()
    public String bookTicket(
        @RequestParam(name = "userId") long userId,
        @RequestParam(name = "eventId") long eventId,
        @RequestParam(name = "category") Category category,
        @RequestParam(name = "place") int place,
        Model model
    ) {
        TicketDTO ticket = bookingFacade.bookTicket(userId, eventId, place, category);
        model.addAttribute("tickets", ticket);
        return "tickets";
    }

    @PostMapping(value = "/batch",
        consumes = MediaType.APPLICATION_XML_VALUE)
    public String bookTicketsBatch(
        @RequestBody List<TicketDTO> tickets,
        Model model
    ) {
        List<TicketDTO> ticketsCreated =  tickets.stream().map(ticket -> bookingFacade.bookTicket(ticket))
                .collect(Collectors.toList());

        model.addAttribute("tickets", ticketsCreated);

        return "tickets";
    }

    @PostMapping("/ticket/ticketDto")
    public String bookTicket(
        @RequestBody TicketDTO ticketDto,
        Model model
    ) {
        TicketDTO ticket = bookingFacade.bookTicket(ticketDto);
        model.addAttribute("tickets", ticket);
        return "tickets";
    }

    @GetMapping("/userId/{userId}")
    public String getBookedTicketsByUserId(
        @PathVariable long userId,
        Model model
    ) {
        List<TicketDTO> tickets = bookingFacade.getBookedTicketsByUserId(userId);
        model.addAttribute("tickets", tickets);
        return "tickets";
    }

    @GetMapping("/eventId/{eventId}")
    public String getBookedTicketsByEventId(
        @PathVariable long eventId,
        Model model
    ) {
        List<TicketDTO> tickets = bookingFacade.getBookedTicketsByEventId(eventId);
        model.addAttribute("tickets", tickets);
        return "tickets";
    }

    @GetMapping("/cancelTicket/{ticketId}")
    public Boolean deleteTicketByTicketId(
        @PathVariable long ticketId
    ) {
        bookingFacade.cancelTicket(ticketId);
        return true;
    }
}
