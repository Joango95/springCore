package com.joango.exception;

public class OperationErrorException extends RuntimeException {

    OperationErrorException(String message) {
        super(message);
    }
}
