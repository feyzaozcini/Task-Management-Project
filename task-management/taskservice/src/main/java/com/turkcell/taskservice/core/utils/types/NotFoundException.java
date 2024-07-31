package com.turkcell.taskservice.core.utils.types;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message){
        super(message);
    }
}