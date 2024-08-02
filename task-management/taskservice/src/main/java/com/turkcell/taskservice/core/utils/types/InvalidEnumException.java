package com.turkcell.taskservice.core.utils.types;

public class InvalidEnumException extends RuntimeException{
    public InvalidEnumException(String message) {
        super(message);
    }
}
