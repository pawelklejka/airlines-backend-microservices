package com.airlinesmicroservices.tourist.DTO;

import com.airlinesmicroservices.tourist.model.TicketReadModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Set;

public class TouristDTO {

    private String touristId;
    @NotBlank
    private String flightId;
    @NotBlank
    private String flightStartingTime;

    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @Email
    private String email;
    @NotBlank
    private String sex;
    @NotBlank
    private String country;
    @DateTimeFormat(pattern = "yyyy-MM-dd", style = "S")
    @JsonFormat(pattern = "YYYY-MM-dd")
    @NotNull
    private String dateOfBirth;
    private String notes;
    private Set<TicketReadModel> tickets;

    public String getTouristId() {
        return touristId;
    }

    public void setTouristId(String touristId) {
        this.touristId = touristId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
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

    public Set<TicketReadModel> getTickets() {
        return tickets;
    }

    public void setTickets(Set<TicketReadModel> tickets) {
        this.tickets = tickets;
    }
}
