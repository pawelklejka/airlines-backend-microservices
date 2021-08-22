package com.airlinesmicroservices.flight.service;

import com.airlinesmicroservices.flight.DTO.TicketDTO;
import com.rabbitmq.client.DefaultConsumer;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

public interface TicketEventConsumerService {

    void ticketCreated(TicketDTO ticketDTO);
    void ticketChanged(TicketDTO ticketDTO);
    void ticketDeleted(TicketDTO ticketDTO);

}
