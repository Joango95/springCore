package com.joango.exception;

public class TicketNotFoundException extends EntityNotFoundException {

    public TicketNotFoundException() {
        super("Ticket not exists");
    }
}
