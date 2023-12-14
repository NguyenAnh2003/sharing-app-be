package com.example.socialapi.common.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(hidden = true)
public class ErrorResponse {
    private ZonedDateTime timestamp;
    private HttpStatus httpStatus;
    private String message;
}
