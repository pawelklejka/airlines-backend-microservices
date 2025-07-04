package com.airlinesmicroservices.tourist.model;

import jakarta.validation.constraints.NotBlank;


public class TicketReadModel {
    @NotBlank
    private String ticketId;
    @NotBlank
    private String gate;
    @NotBlank
    private Long seat;
    @NotBlank
    private String boardingTime;

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getGate() {
        return gate;
    }

    public void setGate(String gate) {
        this.gate = gate;
    }

    public Long getSeat() {
        return seat;
    }

    public void setSeat(Long seat) {
        this.seat = seat;
    }

    public String getBoardingTime() {
        return boardingTime;
    }

    public void setBoardingTime(String boardingTime) {
        this.boardingTime = boardingTime;
    }
}
