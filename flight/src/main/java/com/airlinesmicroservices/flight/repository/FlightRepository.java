package com.airlinesmicroservices.flight.repository;

import com.airlinesmicroservices.flight.model.Flight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends MongoRepository<Flight, String > {
    Page<Flight> findFlightByStartingDestinationStartsWith(String startingDestination, Pageable pageable);
}
