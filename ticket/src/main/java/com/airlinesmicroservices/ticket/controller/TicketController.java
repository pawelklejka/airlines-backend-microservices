package com.airlinesmicroservices.ticket.controller;

import com.airlinesmicroservices.ticket.DTO.TicketDTO;
import com.airlinesmicroservices.ticket.model.Ticket;
import com.airlinesmicroservices.ticket.service.TicketService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;
import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketController {
    private final TicketService<Ticket, String> ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/")
    List<Ticket> findAll(@RequestParam(name = "flightId", required = false) String flightId,
                         @RequestParam(name = "touristId", required = false) String touristId) {
        if (touristId != null) {
            if (!touristId.isBlank() || !touristId.isEmpty()) return ticketService.findAllByTouristId(touristId);
        } else if (flightId != null) {
            if (!flightId.isBlank() || !flightId.isEmpty()) return ticketService.findAllByFlightId(flightId);
        }

        return ticketService.findAll();
    }

    @GetMapping("/{id}")
    Ticket findById(@PathVariable("id") String ticketId) {
        return ticketService.findById(ticketId);
    }

    @PostMapping("/")
    void save(@RequestBody TicketDTO ticketDTO) {
        ticketService.save(ticketDTO);
    }

    @PatchMapping("/{id}")
    void update(@PathVariable("id") String ticketId, @RequestBody Ticket ticket) {

    }

    @DeleteMapping("/{id}")
    void deleteById(@PathVariable("id") String ticketId) {
        ticketService.deleteById(ticketId);
    }
}
