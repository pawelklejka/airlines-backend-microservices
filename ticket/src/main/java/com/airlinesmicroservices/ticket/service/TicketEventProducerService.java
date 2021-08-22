package com.airlinesmicroservices.ticket.service;

import com.airlinesmicroservices.ticket.DTO.TicketDTO;

public interface TicketEventProducerService {

    void createdTicket(TicketDTO touristDTO);
    void deletedTicket(TicketDTO touristDTO);
    void changedTicket(TicketDTO touristDTO);
}
