package com.joango.exception;

public class EventNotFoundException extends RuntimeException{

    public EventNotFoundException(){
        super("Event not exists");
    }
}
