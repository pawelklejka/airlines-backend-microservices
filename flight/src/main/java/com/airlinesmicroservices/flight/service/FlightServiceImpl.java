package com.airlinesmicroservices.flight.service;

import com.airlinesmicroservices.flight.model.Flight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service("flightService")
public class FlightServiceImpl implements FlightService{
    @Override
    public Page<Flight> findByStartingDestination(String startingDestination, Pageable pageable) {
        return null;
    }

    @Override
    public Page<Flight> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Flight findById(Long id) {
        return null;
    }

    @Override
    public void save(Flight flight) {

    }

    @Override
    public void updateFlight(Long id, Flight flight) {

    }

    @Override
    public void deleteTouristFromFlight(Long flightId, Long touristId) {

    }

    @Override
    public void deleteById(long id) {

    }

    @Override
    public void fillWithData(Integer amountOfFlight) {

    }
}
