package com.joango.exception;

public class TicketNotFoundException extends RuntimeException{

    public TicketNotFoundException() {
        super("Ticket not exists");
    }
}
