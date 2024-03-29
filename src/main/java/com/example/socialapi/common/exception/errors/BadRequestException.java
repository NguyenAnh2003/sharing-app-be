package com.example.socialapi.common.exception.errors;

public class BadRequestException extends RuntimeException{
    private final String message;
    public BadRequestException(String message) {
        this.message = message;
        throw new BadRequestException(this.message);
    }
}
