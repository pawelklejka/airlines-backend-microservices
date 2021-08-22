package com.airlinesmicroservices.ticketpdfgenerator.service;

import com.airlinesmicroservices.ticketpdfgenerator.model.Ticket;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "ticket-service")
@RequestMapping("/tickets")
@Service
public interface TicketServiceClient {

    @GetMapping("/{id}")
    Optional<Ticket> getTicket(@PathVariable("id") Long id);

}
