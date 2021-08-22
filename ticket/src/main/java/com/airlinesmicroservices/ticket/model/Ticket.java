package com.airlinesmicroservices.ticket.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Document
public class Ticket {
    @Id
    private String ticketId;
    private String gate;
    private Long seat;
    private LocalTime boardingTime;
    private String codeBarQrBar; //TODO IMPLEMENT SERVICE THAT GENERATE QR OR BARCODE
    private String flightId;
    private String touristId;

    public Ticket() {
    }

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

    public LocalTime getBoardingTime() {
        return boardingTime;
    }

    public void setBoardingTime(LocalDateTime startingTime) {

        LocalTime localTime = LocalTime.of((startingTime.getHour()), startingTime.getMinute());

        this.boardingTime = LocalTime.of(localTime.getHour(), localTime.getMinute()).minusMinutes(30);
    }

    public String getCodeBarQrBar() {
        return codeBarQrBar;
    }

    public void setCodeBarQrBar(String codeBarQrBar) {
        this.codeBarQrBar = codeBarQrBar;
    }

    public String getFlightId() {
        return flightId;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }

    public String getTouristId() {
        return touristId;
    }

    public void setTouristId(String touristId) {
        this.touristId = touristId;
    }
}