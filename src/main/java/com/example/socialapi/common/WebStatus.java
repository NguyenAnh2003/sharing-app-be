package com.example.socialapi.common;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public enum WebStatus {
    UNAUTHORIZEd(403, HttpStatus.UNAUTHORIZED),
    NOT_FOUND(404, HttpStatus.NOT_FOUND);

    private final int status;
    private final HttpStatus httpStatus;

    public int getStatus() {
        return status;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
