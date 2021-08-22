package com.airlinesmicroservices.flight.service;

import com.airlinesmicroservices.flight.model.Flight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface FlightService {
    Page<Flight> findByStartingDestination(String startingDestination, Pageable pageable);
    Page<Flight> findAll(Pageable pageable);
    Flight findById(Long id);
    void save(Flight flight);
    void updateFlight(Long id, Flight flight);
//    void addTouristToFlight(Long id, TouristDTO touristDTO);
    void deleteTouristFromFlight(Long flightId, Long touristId);
    void deleteById(long id);
    void fillWithData(Integer amountOfFlight);
//    Set<Tourist> findTouristsInFlight(Long id);
}
