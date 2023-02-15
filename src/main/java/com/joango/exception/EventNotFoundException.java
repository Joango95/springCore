package com.joango.exception;

public class EventNotFoundException extends EntityNotFoundException {

    public EventNotFoundException(){
        super("Event not exists");
    }
}
