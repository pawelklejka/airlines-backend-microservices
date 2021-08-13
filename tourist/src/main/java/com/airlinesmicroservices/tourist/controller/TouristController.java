package com.airlinesmicroservices.tourist.controller;

import com.airlinesmicroservices.tourist.model.Tourist;
import com.airlinesmicroservices.tourist.service.TouristService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/tourists")
public class TouristController {
    private final TouristService touristService;

    public TouristController(@Qualifier("touristService") TouristService touristService) {
        this.touristService = touristService;

    }

    @GetMapping("/")
    public Page<Tourist> findAll(@RequestParam Optional<Integer> page){
        return touristService.findAll(PageRequest.of(page.orElse(0), 21, Sort.by("surname").and(Sort.by("name"))));
    }

    @GetMapping("/{id}")
    public Tourist findById(@PathVariable("id") Long id){
        return touristService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable("id") Long id){
        touristService.deleteById(id);
    }

    @PatchMapping("/{id}/delete")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteTicketFromTourist(@PathVariable("id") Long touristId, @RequestParam Long flightId){
        touristService.deleteTicketFromTourist(touristId, flightId);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void update(@PathVariable("id") Long id, @Valid @RequestBody Tourist tourist){
        touristService.updateTourist(id, tourist);

    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@Valid @RequestBody Tourist tourist){
        touristService.save(tourist);
    }

}
