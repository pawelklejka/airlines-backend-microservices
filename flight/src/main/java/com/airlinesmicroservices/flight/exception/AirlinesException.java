package com.airlinesmicroservices.flight.exception;

import lombok.Getter;

@Getter
public class AirlinesException extends RuntimeException{
    private final AirlinesError airlinesError;

    public AirlinesException(AirlinesError airlinesError){
        this.airlinesError = airlinesError;
    }

}
