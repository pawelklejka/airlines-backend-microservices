package com.airlinesmicroservices.tourist.service;

import com.airlinesmicroservices.tourist.DTO.TicketDTO;
import com.airlinesmicroservices.tourist.exception.AirlinesError;
import com.airlinesmicroservices.tourist.exception.AirlinesException;
import com.airlinesmicroservices.tourist.model.TicketReadModel;
import com.airlinesmicroservices.tourist.model.Tourist;
import com.airlinesmicroservices.tourist.repository.TouristRepository;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class TicketEventConsumerServiceImpl implements TicketEventConsumerService{
    private final TouristRepository touristRepository;
    private final TicketServiceClient ticketServiceClient;

    public TicketEventConsumerServiceImpl(TouristRepository touristRepository, TicketServiceClient ticketServiceClient) {
        this.touristRepository = touristRepository;
        this.ticketServiceClient = ticketServiceClient;
    }

    @RabbitListener(bindings = {
            @QueueBinding(value =
            @Queue(value = "ticketTouristServiceQueue"), exchange = @Exchange(name = "eventExchange", type = "topic"), key = {"ticket.created"})
    }, messageConverter = "messageConverter")
    @Override
    public void ticketCreated(TicketDTO ticketDTO) {
        Tourist tourist = touristRepository.findById(ticketDTO.getTouristId())
                .orElseThrow(() -> new AirlinesException(AirlinesError.TOURIST_NOT_FOUND));


        Set<TicketReadModel> tickets = ticketServiceClient.getTickets(ticketDTO.getTouristId());

        tourist.getTickets().addAll(tickets);

        touristRepository.save(tourist);

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
