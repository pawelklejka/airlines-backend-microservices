package com.airlinesmicroservices.ticket.controller;

import com.airlinesmicroservices.ticket.DTO.TicketDTO;
import com.airlinesmicroservices.ticket.model.Ticket;
import com.airlinesmicroservices.ticket.service.TicketService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketController {
    private final TicketService<Ticket, String> ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/")
    List<Ticket> findAllByFlightId(String flightId){
        return ticketService.findAllByFlightId(flightId);
    }

    @GetMapping("/{id}")
    Ticket findById(@PathVariable("id") String ticketId){
        return ticketService.findById(ticketId);
    }

    @PostMapping("/")
    void save(@RequestBody TicketDTO ticketDTO){
        ticketService.save(ticketDTO);
    }

    @PatchMapping("/{id}")
    void update(@PathVariable("id") String ticketId, @RequestBody Ticket ticket){

    }

    @DeleteMapping("/{id}")
    void deleteById(@PathVariable("id") String ticketId){
        ticketService.deleteById(ticketId);
    }
}
