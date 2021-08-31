package com.airlinesmicroservices.flight.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Document
public class Flight {
    @Id
    private String flightId;
    @NotBlank
    private String startingDestination;
    @NotBlank
    private String finalDestination;
    @NotNull
    private String flightStartingTime;
    @NotNull
    private LocalDateTime flightArrivalTime;
    @NotNull
    private Integer capacity;
    private Integer touristAmount;
    private BigDecimal price;

    private Set<String> touristIds;

    public Flight(){
        this.touristIds = new HashSet<>();
    }

    public String getFlightId() {
        return flightId;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }

    public String getStartingDestination() {
        return startingDestination;
    }

    public void setStartingDestination(String startingDestination) {
        this.startingDestination = startingDestination;
    }

    public String getFinalDestination() {
        return finalDestination;
    }

    public void setFinalDestination(String finalDestination) {
        this.finalDestination = finalDestination;
    }

    public String getFlightStartingTime() {
        return flightStartingTime;
    }

    public void setFlightStartingTime(String flightStartingTime) {
        this.flightStartingTime = flightStartingTime;
    }

    public LocalDateTime getFlightArrivalTime() {
        return flightArrivalTime;
    }

    public void setFlightArrivalTime(LocalDateTime flightArrivalTime) {
        this.flightArrivalTime = flightArrivalTime;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getTouristAmount() {
        return touristAmount;
    }

    public void setTouristAmount(Integer touristAmount) {
        this.touristAmount = touristAmount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Set<String> getTouristIds() {
        return touristIds;
    }

    public void setTouristIds(Set<String> touristIds) {
        this.touristIds = touristIds;
    }
}
