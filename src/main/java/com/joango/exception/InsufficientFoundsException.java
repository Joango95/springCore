package com.joango.exception;

public class InsufficientFoundsException extends OperationErrorException {

    public InsufficientFoundsException(){
        super("Insufficient founds");
    }
}
