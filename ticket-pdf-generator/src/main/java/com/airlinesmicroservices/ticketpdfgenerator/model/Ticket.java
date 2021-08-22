package com.airlinesmicroservices.ticketpdfgenerator.model;

import java.time.LocalTime;

public class Ticket {
    private Long ticketId;
    private String gate;
    private Long seat;
    private LocalTime boardingTime;
    private String codeBarQrBar; //TODO IMPLEMENT SERVICE THAT GENERATE QR OR BARCODE
    private FlightReadModel flight;
    private TouristReadModel tourist;


    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
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

    public void setBoardingTime(LocalTime boardingTime) {
        this.boardingTime = boardingTime;
    }

    public String getCodeBarQrBar() {
        return codeBarQrBar;
    }

    public void setCodeBarQrBar(String codeBarQrBar) {
        this.codeBarQrBar = codeBarQrBar;
    }

    public TouristReadModel getTouristInFlight() {
        return tourist;
    }

    public void setTourist(TouristReadModel tourist) {
        this.tourist = tourist;
    }

    public FlightReadModel getFlightThatTouristIsIn() {
        return flight;
    }

    public void setFlight(FlightReadModel flight) {
        this.flight = flight;
    }
}
