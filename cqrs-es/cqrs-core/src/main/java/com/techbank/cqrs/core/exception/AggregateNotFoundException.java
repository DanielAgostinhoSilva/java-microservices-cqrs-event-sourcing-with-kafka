package com.techbank.cqrs.core.exception;

public class AggregateNotFoundException extends RuntimeException{

    public AggregateNotFoundException(String message) {
        super(message);
    }
}
