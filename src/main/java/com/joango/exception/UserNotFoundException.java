package com.joango.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(){
        super("User not exists");
    }
}
