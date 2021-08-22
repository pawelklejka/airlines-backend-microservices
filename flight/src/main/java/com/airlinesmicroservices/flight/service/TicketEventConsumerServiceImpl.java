package com.airlinesmicroservices.flight.service;

import com.airlinesmicroservices.flight.DTO.TicketDTO;
import com.airlinesmicroservices.flight.exception.AirlinesError;
import com.airlinesmicroservices.flight.exception.AirlinesException;
import com.airlinesmicroservices.flight.model.Flight;
import com.airlinesmicroservices.flight.repository.FlightRepository;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.stereotype.Service;

@Service
public class TicketEventConsumerServiceImpl implements TicketEventConsumerService{
    private final FlightRepository flightRepository;

    public TicketEventConsumerServiceImpl(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @RabbitListener(bindings = {
            @QueueBinding(value =
            @Queue(value = "ticketServiceQueue"), exchange = @Exchange(name = "eventExchange", type = "topic"), key = {"ticket.created"})
    }, messageConverter = "messageConverter")
    @Override
    public void ticketCreated(TicketDTO ticketDTO) {
        Flight flight = flightRepository.findById(ticketDTO.getFlightId())
                .orElseThrow(() -> new AirlinesException(AirlinesError.FLIGHT_NOT_FOUND));

        flight.getTouristIds().add(ticketDTO.getTouristId());

    }

//    @RabbitListener(queues = "ticket.changed")
    @Override
    public void ticketChanged(TicketDTO ticketDTO) {

    }

//    @RabbitListener(queues = "ticket.deleted")
    @Override
    public void ticketDeleted(TicketDTO ticketDTO) {

    }
}
