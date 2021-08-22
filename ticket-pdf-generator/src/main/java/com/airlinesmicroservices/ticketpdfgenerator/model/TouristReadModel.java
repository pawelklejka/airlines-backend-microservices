package com.airlinesmicroservices.ticketpdfgenerator.model;

public class TouristReadModel {
    private String surname;
    private String name;
    private Long id;

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setTouristId(Long touristId) {
        this.id = touristId;
    }
}
