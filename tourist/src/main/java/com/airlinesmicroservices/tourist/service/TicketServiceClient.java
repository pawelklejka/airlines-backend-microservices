package com.airlinesmicroservices.tourist.service;

import com.airlinesmicroservices.tourist.DTO.TicketDTO;
import com.airlinesmicroservices.tourist.model.TicketReadModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

@Service
@FeignClient(name = "ticket-service", path = "/tickets")
public interface TicketServiceClient {
    @GetMapping("/")
    Set<TicketReadModel> getTickets(@RequestParam String touristId);

    @PostMapping("/")
    void save(@RequestBody TicketDTO ticketDTO);
}
