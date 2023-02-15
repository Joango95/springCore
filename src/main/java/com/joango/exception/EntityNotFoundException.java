package com.joango.exception;

public class EntityNotFoundException extends RuntimeException {

    EntityNotFoundException(String message){
        super(message);
    }
}
