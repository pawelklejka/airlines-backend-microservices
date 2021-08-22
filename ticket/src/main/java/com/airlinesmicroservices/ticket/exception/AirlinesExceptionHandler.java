package com.airlinesmicroservices.ticket.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AirlinesExceptionHandler {

    @ExceptionHandler(value = AirlinesException.class)
    public ResponseEntity<ErrorInfo> handleException(AirlinesException e) {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        if (AirlinesError.TICKET_NOT_FOUND.equals(e.getAirlinesError().getErrorInfo())) {
            httpStatus = HttpStatus.NOT_FOUND;
        } else if (AirlinesError.OUT_OF_FLIGHT_CAPACITY_ERROR.equals(e.getAirlinesError().getErrorInfo())) {
            httpStatus = HttpStatus.CONFLICT;

        }
        return ResponseEntity.status(httpStatus).body(new ErrorInfo(e.getAirlinesError().getErrorInfo()));

    }
}
