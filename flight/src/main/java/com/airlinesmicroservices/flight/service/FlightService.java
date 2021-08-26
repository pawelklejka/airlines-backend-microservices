package com.airlinesmicroservices.flight.service;

import com.airlinesmicroservices.flight.model.Flight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface FlightService<ID> {
    Page<Flight> findByStartingDestination(String startingDestination, Pageable pageable);
    Page<Flight> findAll(Pageable pageable);
    Flight findById(ID id);
    void save(Flight flight);
    void updateFlight(ID id, Flight flight);
//    void addTouristToFlight(ID id, TouristDTO touristDTO);
    void deleteTouristFromFlight(ID flightId, ID touristId);
    void deleteById(ID id);
//    Set<Tourist> findTouristsInFlight(Long id);
}
