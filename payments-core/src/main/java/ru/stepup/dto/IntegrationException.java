package ru.stepup.dto;

import lombok.Getter;

@Getter
public class IntegrationException extends RuntimeException {
    private IntegrationErrorDto errorDto;

    public IntegrationException(String message, Exception e) {
        super(message, e);
    }

    public IntegrationException(IntegrationErrorDto errorDto) {
        this.errorDto = errorDto;
    }

}
