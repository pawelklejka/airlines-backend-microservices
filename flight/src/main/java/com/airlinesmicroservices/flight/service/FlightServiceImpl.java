package com.airlinesmicroservices.flight.service;

import com.airlinesmicroservices.flight.exception.AirlinesError;
import com.airlinesmicroservices.flight.exception.AirlinesException;
import com.airlinesmicroservices.flight.model.Flight;
import com.airlinesmicroservices.flight.repository.FlightRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service("flightService")
public class FlightServiceImpl implements FlightService<String>{
    private final FlightRepository flightRepository;

    public FlightServiceImpl(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @Override
    public Page<Flight> findByStartingDestination(String startingDestination, Pageable pageable) {
        return flightRepository.findFlightByStartingDestinationStartsWith(startingDestination, pageable);
    }

    @Override
    public Page<Flight> findAll(Pageable pageable) {
        return flightRepository.findAll(pageable);
    }

    @Override
    public Flight findById(String flightId) {
        return flightRepository.findById(flightId)
                .orElseThrow(() -> new AirlinesException(AirlinesError.FLIGHT_NOT_FOUND));
    }

    @Override
    public void save(Flight flight) {
        flightRepository.save(flight);
    }

    @Override
    public void updateFlight(String flightId, Flight flight) {
        Flight currentFlight = flightRepository.findById(flightId)
                .map(cf -> {
                    cf.setCapacity(flight.getCapacity());
                    cf.setStartingDestination(flight.getStartingDestination());
                    cf.setFinalDestination(flight.getFinalDestination());
                    cf.setFlightStartingTime(flight.getFlightStartingTime());
                    cf.setFlightArrivalTime(flight.getFlightArrivalTime());
                    cf.setPrice(flight.getPrice());
                    return cf;
                })
                .orElseThrow(() -> new AirlinesException(AirlinesError.FLIGHT_NOT_FOUND));
        flightRepository.save(currentFlight);
    }

    @Override
    public void deleteTouristFromFlight(String flightId, String touristId) {
        Flight currentFlight = flightRepository.findById(flightId)
                .orElseThrow(() -> new AirlinesException(AirlinesError.FLIGHT_NOT_FOUND));
        currentFlight.getTouristIds().remove(touristId);
        flightRepository.save(currentFlight);
    }

    @Override
    public void deleteById(String flightId) {
        flightRepository.deleteById(flightId);
    }


}
