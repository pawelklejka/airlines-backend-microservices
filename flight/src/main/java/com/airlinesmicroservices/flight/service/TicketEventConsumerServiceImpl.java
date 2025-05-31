package com.airlinesmicroservices.flight.service;

import org.springframework.stereotype.Service;

@Service
public class TicketEventConsumerServiceImpl implements TicketEventConsumerService{
//    private final FlightRepository flightRepository;
//
//    public TicketEventConsumerServiceImpl(FlightRepository flightRepository) {
//        this.flightRepository = flightRepository;
//    }
//
//    @RabbitListener(bindings = {
//            @QueueBinding(value =
//            @Queue(value = "ticketFlightServiceQueue"), exchange = @Exchange(name = "eventExchange", type = "topic"), key = {"ticket.created"})
//    }, messageConverter = "messageConverter")
//    @Override
//    public void ticketCreated(TicketDTO ticketDTO) {
//        Flight flight = flightRepository.findById(ticketDTO.getFlightId())
//                .orElseThrow(() -> new AirlinesException(AirlinesError.FLIGHT_NOT_FOUND));
//
//        flight.getTouristIds().add(ticketDTO.getTouristId());
//        flightRepository.save(flight);
//
//    }
//
////    @RabbitListener(queues = "ticket.changed")
//    @Override
//    public void ticketChanged(TicketDTO ticketDTO) {
//
//    }
//
////    @RabbitListener(queues = "ticket.deleted")
//    @Override
//    public void ticketDeleted(TicketDTO ticketDTO) {
//
//    }
}
