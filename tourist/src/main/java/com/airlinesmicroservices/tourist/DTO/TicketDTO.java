package com.airlinesmicroservices.tourist.DTO;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TicketDTO {
    @NotBlank
    private String touristId;
    @NotBlank
    private String flightId;
    @NotNull
    private String flightStartingTime;


    public String getTouristId() {
        return touristId;
    }

    public void setTouristId(String touristId) {
        this.touristId = touristId;
    }

    public String getFlightId() {
        return flightId;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }

    public String getFlightStartingTime() {
        return flightStartingTime;
    }

    public void setFlightStartingTime(String flightStartingTime) {
        this.flightStartingTime = flightStartingTime;
    }

}

