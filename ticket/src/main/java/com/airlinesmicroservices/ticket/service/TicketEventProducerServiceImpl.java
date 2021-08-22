package com.airlinesmicroservices.ticket.service;

import com.airlinesmicroservices.ticket.DTO.TicketDTO;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class TicketEventProducerServiceImpl  implements TicketEventProducerService{
    private final RabbitTemplate rabbitTemplate;
    private final Exchange exchange;


    public TicketEventProducerServiceImpl(RabbitTemplate rabbitTemplate, Exchange exchange) {
        this.rabbitTemplate = rabbitTemplate;
        this.exchange = exchange;
    }

    @Override
    public void createdTicket(TicketDTO ticketDTO) {
        String routingKey = "ticket.created";
        rabbitTemplate.convertAndSend(exchange.getName(), routingKey, ticketDTO);
    }

    @Override
    public void deletedTicket(TicketDTO touristDTO) {

    }

    @Override
    public void changedTicket(TicketDTO touristDTO) {

    }
}
