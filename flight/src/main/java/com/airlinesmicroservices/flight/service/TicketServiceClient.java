package com.airlinesmicroservices.flight.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient
@RequestMapping("/tickets")
public interface TicketServiceClient {
//    @GetMapping
//    List<Ticket> getTickets();
}
