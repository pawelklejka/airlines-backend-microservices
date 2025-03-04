package com.airlinesmicroservices.flight.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AirlinesExceptionHandler {

    @ExceptionHandler(value = AirlinesException.class)
    public ResponseEntity<ErrorInfo> handleException(AirlinesException e) {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        return ResponseEntity.status(httpStatus).body(new ErrorInfo(e.getAirlinesError().getErrorInfo()));
    }
}
