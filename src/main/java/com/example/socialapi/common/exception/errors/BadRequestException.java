package com.example.socialapi.common.exception.errors;

public class BadRequestException extends RuntimeException{
    public BadRequestException(String message) {
        throw new BadRequestException(message);
    }
}
