package com.airlinesmicroservices.flight.controller;

import com.airlinesmicroservices.flight.model.Flight;
import com.airlinesmicroservices.flight.service.FlightService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/flights")
public class FlightController {
    private final FlightService flightService;


    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping("/searchByDestination")
    public Page<Flight> findByStartingDestination(@RequestParam Optional<String> name,
                                                  @RequestParam Optional<Integer> page){
        return flightService.findByStartingDestination(
                name.orElse("_"),
                PageRequest.of(page.orElse(0), 2, Sort.by("startingDestination").and(Sort.by("flightStartingTime"))));
    }

    @GetMapping("/")
    public Page<Flight> findAll(@RequestParam Optional<Integer> page){

        return flightService.findAll(PageRequest.of(page.orElse(0), 21, Sort.by("startingDestination").and(Sort.by("flightStartingTime"))));
    }

    @GetMapping("/{id}")
    public Flight findById(@PathVariable("id") Long id){
        return flightService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable("id") Long id){
        flightService.deleteById(id);
    }
//
//    @PatchMapping("/{id}")
//    public void addTouristToFlight(@PathVariable("id") Long id, @Valid @RequestBody TouristDTO touristDTO){
//        flightService.addTouristToFlight(id, touristDTO);
//    }

    @PatchMapping("/{id}/delete")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteTouristFromFlight(@PathVariable("id") Long flightId, @RequestParam Long touristId){
        //TODO NAPRAWIC TEN ENDPOINT
        flightService.deleteTouristFromFlight(flightId, touristId);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void update(@PathVariable("id") Long id, @Valid @RequestBody Flight flight){

        flightService.updateFlight(id, flight);
    }

//    @GetMapping("/findTouristsInFlight/{id}")
//    public Set<TouristDTO> findTouristsInFlights(@PathVariable Long id){
//        return flightService.findTouristsInFlight(id);
//    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@Valid @RequestBody Flight flight){
        flightService.save(flight);
    }

    @GetMapping("/fillWithData")
    public void fillWithData(@RequestParam Integer amountOfFlight){
        flightService.fillWithData(amountOfFlight);
    }
}
