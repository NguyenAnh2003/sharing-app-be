package com.example.socialapi.common.exception;

import com.example.socialapi.common.exception.errors.BadRequestException;
import com.example.socialapi.common.exception.errors.NotFoundException;
import com.example.socialapi.common.exception.errors.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.ZonedDateTime;

@RestControllerAdvice
public class CustomExceptionHandler {
    // https://www.digitalocean.com/community/tutorials/how-to-troubleshoot-common-http-error-codes
    /* 400 */
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handlerBadRequest(BadRequestException e) {
        return getErrorResponse(HttpStatus.BAD_REQUEST, e);
    }
    /* 403 */
    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse handlerUnauthorized(UnauthorizedException e) {
        return getErrorResponse(HttpStatus.UNAUTHORIZED, e);
    }
    /* 404 */
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handlerNotFoundException(NotFoundException e) {
    // ErrorResponse errorResponse = new ErrorResponse(ZonedDateTime.now(), HttpStatus.NOT_FOUND, ex.getMessage());
        return getErrorResponse(HttpStatus.NOT_FOUND, e);
    }
    private ErrorResponse getErrorResponse(HttpStatus httpStatus, Exception e, WebRequest... req) {
        ErrorResponse errorResponse = new ErrorResponse(ZonedDateTime.now(), httpStatus, e.getMessage());
        return errorResponse;
    }

}
