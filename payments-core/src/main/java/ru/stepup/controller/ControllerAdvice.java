package ru.stepup.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.stepup.dto.ErrorResponse;
import ru.stepup.dto.IntegrationException;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleIntegrationException(Exception e) {
        return new ErrorResponse(HttpStatus.NOT_FOUND.name(), "Not found: " + e.getMessage());
    }

    @ExceptionHandler(IntegrationException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleIntegrationException(IntegrationException e) {
        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.name(), e.getMessage());
    }
}