package com.airlinesmicroservices.tourist.service;

import com.airlinesmicroservices.tourist.DTO.TicketDTO;
import com.airlinesmicroservices.tourist.model.TicketReadModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service
@FeignClient(name = "ticket-service")
@RequestMapping("/tickets")
public interface TicketServiceClient {
    @GetMapping("/")
    List<TicketReadModel> getTickets();

    @PostMapping("/")
    void save(@RequestBody TicketDTO ticketDTO);
}
