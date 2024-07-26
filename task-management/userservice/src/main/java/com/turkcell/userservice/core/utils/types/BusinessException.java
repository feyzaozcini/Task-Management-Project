package com.turkcell.userservice.core.utils.types;

public class BusinessException extends RuntimeException {

    public BusinessException(String message){
        super(message);
    }
}