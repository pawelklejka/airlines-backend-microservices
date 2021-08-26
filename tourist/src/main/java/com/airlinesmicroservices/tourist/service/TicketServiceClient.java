package com.airlinesmicroservices.tourist.service;

import com.airlinesmicroservices.tourist.DTO.TicketDTO;
import com.airlinesmicroservices.tourist.model.TicketReadModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Service
@FeignClient(name = "ticket-service")
@RequestMapping("/tickets")
public interface TicketServiceClient {
    @GetMapping("/")
    Set<TicketReadModel> getTickets(@RequestParam String touristId);

    @PostMapping("/")
    void save(@RequestBody TicketDTO ticketDTO);
}
