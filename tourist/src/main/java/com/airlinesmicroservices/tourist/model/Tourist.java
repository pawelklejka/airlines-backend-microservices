package com.airlinesmicroservices.tourist.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.HashSet;
import java.util.Set;

@Document
public class Tourist {
    @Id
    private String touristId;
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

    public Tourist(){
        this.tickets = new HashSet<>();
    }

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

    public Set<TicketReadModel> getTickets() {
        return tickets;
    }

    public void setTickets(Set<TicketReadModel> tickets) {
        this.tickets = tickets;
    }
}
