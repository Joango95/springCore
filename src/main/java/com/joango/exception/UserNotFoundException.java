package com.joango.exception;

public class UserNotFoundException extends EntityNotFoundException {

    public UserNotFoundException(){
        super("User not exists");
    }
}
