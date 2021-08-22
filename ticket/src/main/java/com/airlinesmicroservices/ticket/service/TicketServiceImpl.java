package com.airlinesmicroservices.ticket.service;

import com.airlinesmicroservices.ticket.DTO.TicketDTO;
import com.airlinesmicroservices.ticket.exception.AirlinesError;
import com.airlinesmicroservices.ticket.exception.AirlinesException;
import com.airlinesmicroservices.ticket.model.Ticket;
import com.airlinesmicroservices.ticket.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService<Ticket, String>{
    private final TicketRepository ticketRepository;
    private final TicketEventProducerService ticketEventProducerService;
    private final DateParserService dateParserService;

    public TicketServiceImpl(TicketRepository ticketRepository, TicketEventProducerService ticketEventProducerService, DateParserService dateParserService) {
        this.ticketRepository = ticketRepository;
        this.ticketEventProducerService = ticketEventProducerService;
        this.dateParserService = dateParserService;
    }

    @Override
    public List<Ticket> findAllByFlightId(String flightId) {
        return ticketRepository.findAllByFlightId(flightId);
    }

    @Override
    public Ticket findById(String ticketId) {
        return ticketRepository.findById(ticketId)
                .orElseThrow(() -> new AirlinesException(AirlinesError.TICKET_NOT_FOUND));
    }

    @Override
    public void save(TicketDTO ticketDTO) {
        Ticket ticket = new Ticket();
        ticket.setFlightId(ticketDTO.getFlightId());
        ticket.setTouristId(ticket.getTouristId());
        //TODO zrobic zeby generowalo number fotela od 0 do flightCapacity
        ticket.setSeat(10l);
        ticket.setGate("20S");
        ticket.setBoardingTime(dateParserService.parseDateTimeFromString(ticketDTO.getFlightStartingTime()));

        ticketRepository.save(ticket);
        ticketEventProducerService.createdTicket(ticketDTO);
    }

    @Override
    public void update(String ticketId, TicketDTO ticketDTO) {
        Ticket currentTicket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new AirlinesException(AirlinesError.TICKET_NOT_FOUND));
        //TODO nasluchuj zmiany w locie aby zmienic boardingTime i gate, ale to samo w druga strone niech lot nasluchuje zmian boarding time w bilecie
        currentTicket.setBoardingTime(dateParserService.parseDateTimeFromString(ticketDTO.getFlightStartingTime()));


    }

    @Override
    public void deleteById(String ticketId) {

    }
}
