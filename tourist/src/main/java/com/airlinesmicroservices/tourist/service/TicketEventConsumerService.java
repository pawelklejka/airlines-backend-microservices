package com.airlinesmicroservices.tourist.service;

import com.airlinesmicroservices.tourist.DTO.TicketDTO;

public interface TicketEventConsumerService {

    void ticketCreated(TicketDTO ticketDTO);
    void ticketChanged(TicketDTO ticketDTO);
    void ticketDeleted(TicketDTO ticketDTO);

}
