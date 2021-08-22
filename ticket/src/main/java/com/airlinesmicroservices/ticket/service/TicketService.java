package com.airlinesmicroservices.ticket.service;

import com.airlinesmicroservices.ticket.DTO.TicketDTO;
import com.airlinesmicroservices.ticket.model.Ticket;

import java.util.List;

public interface TicketService<T, ID> {
    List<T> findAllByFlightId(ID flightId);
    T findById(ID ticketId);
    void save(TicketDTO touristDTO);
    void update(ID ticketId, TicketDTO ticket);
    void deleteById(ID ticketId);

}
