package com.airlinesmicroservices.tourist.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

@Document
public class Ticket {
    @Id
    private Long ticketId;
    private String gate;
    private Long seat;
    private LocalTime boardingTime;
    private String codeBarQrBar; //TODO IMPLEMENT SERVICE THAT GENERATE QR OR BARCODE

    public Ticket() {


    }

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

    public Tourist getTouristInFlight() {
        return touristInFlight;
    }

    public void setTouristInFlight(Tourist touristInFlight) {
        this.touristInFlight = touristInFlight;
    }

    public Long getSeat() {
        return seat;
    }

    public void setSeat(Long seat) {
        this.seat = seat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ticket)) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(getTicketId(), ticket.getTicketId()) &&
                Objects.equals(getGate(), ticket.getGate()) &&
                Objects.equals(getSeat(), ticket.getSeat()) &&
                Objects.equals(getBoardingTime(), ticket.getBoardingTime()) &&
                Objects.equals(getCodeBarQrBar(), ticket.getCodeBarQrBar()) &&
                Objects.equals(getTouristInFlight(), ticket.getTouristInFlight());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTicketId(), getGate(), getSeat(), getBoardingTime(), getCodeBarQrBar(), getTouristInFlight());
    }


    @JsonIgnore
    private Tourist touristInFlight;




}
