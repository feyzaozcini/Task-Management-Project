package com.turkcell.projectservice.core.utils;

import com.turkcell.projectservice.core.utils.details.NotFoundDetails;
import com.turkcell.projectservice.core.utils.types.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({NotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public NotFoundDetails handleNotFoundException(NotFoundException exception) {
        NotFoundDetails notFoundDetails = new NotFoundDetails();
        notFoundDetails.setMessage(exception.getMessage());
        return notFoundDetails;
    }
}