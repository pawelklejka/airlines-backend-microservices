package com.airlinesmicroservices.tourist.service;

import com.airlinesmicroservices.tourist.DTO.TicketDTO;
import com.airlinesmicroservices.tourist.DTO.TouristDTO;
import com.airlinesmicroservices.tourist.exception.AirlinesError;
import com.airlinesmicroservices.tourist.exception.AirlinesException;
import com.airlinesmicroservices.tourist.model.Tourist;
import com.airlinesmicroservices.tourist.repository.TouristRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service(value = "touristService")
public class TouristServiceImpl implements TouristService {
    private final TouristRepository touristRepository;
    private final DateParserService dateParserService;
    private final TicketServiceClient ticketServiceClient;

    public TouristServiceImpl(TouristRepository touristRepository, DateParserService dateParserService, TicketServiceClient ticketServiceClient) {
        this.touristRepository = touristRepository;
        this.dateParserService = dateParserService;

        this.ticketServiceClient = ticketServiceClient;
    }

    @Override
    public Page<Tourist> findAll(Pageable pageable) {
        return touristRepository.findAll(pageable);
    }

    @Override
    public Tourist findById(String id) {
        return touristRepository.findById(id).orElseThrow(() -> new AirlinesException(AirlinesError.TOURIST_NOT_FOUND));
    }

    @Override
    public void save(TouristDTO tourist) {
        Tourist touristToBeSaved = new Tourist();
        touristToBeSaved.setName(tourist.getName());
        touristToBeSaved.setSurname(tourist.getSurname());
        touristToBeSaved.setCountry(tourist.getCountry());
        touristToBeSaved.setEmail(tourist.getEmail());
        touristToBeSaved.setDateOfBirth(tourist.getDateOfBirth());
        touristToBeSaved.setSex(tourist.getSex());
        touristToBeSaved.setNotes(tourist.getNotes());
        touristRepository.save(touristToBeSaved);
        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setTouristId(touristRepository.findByEmail(tourist.getEmail()).get().getTouristId());
        ticketDTO.setFlightStartingTime(tourist.getFlightStartingTime());
        ticketDTO.setFlightId(tourist.getFlightId());
        ticketServiceClient.save(ticketDTO);


    }

    @Override
    public void updateTourist(String id, TouristDTO tourist) {
        Tourist currentTourist = touristRepository.findById(id)
                .orElseThrow(() -> new AirlinesException(AirlinesError.TOURIST_NOT_FOUND));
        touristRepository.save(currentTourist);
    }

    @Override
    public void addTicketToTourist(String id, TouristDTO tourist) {
//        Tourist currentTourist = touristRepository.findById(id).get();
//        Flight flight = new Flight();
//        flight.setStartingDestination(flightDTO.getStartingDestination());
//        flight.setFlightStartingTime(dateParserService.parseDateTimeFromString(flightDTO.getFlightStartingTime()));
//        flight.setFlightArrivalTime(dateParserService.parseDateTimeFromString(flightDTO.getFlightArrivalTime()));
//        flight.setCapacity(flightDTO.getCapacity());
//        flight.setPrice(flightDTO.getPrice());
//        currentTourist.add(flight);
//        touristRepository.save(currentTourist);
    }

    @Override
    public void deleteTicketFromTourist(String touristId, String flightId) {
//        Tourist tourist = touristRepository.findById(touristId)
//                .orElseThrow(() -> new AirlinesException(AirlinesError.TOURIST_NOT_FOUND));
//
//        Ticket ticket = tourist.getTickets().stream()
//                .filter(t -> t.getFlightThatTouristIsIn().getId().equals(flightId))
//                .findFirst()
//                .orElseThrow(() -> new AirlinesException(AirlinesError.TICKET_NOT_FOUND));
//        tourist.getTickets().remove(ticket);
//        ticketRepository.deleteById(ticket.getTicketId());
//        touristRepository.save(tourist);
    }


    @Override
    public void deleteById(String id) {

        touristRepository.deleteById(id);
    }

//    private Tourist convertDtoToEntity(TouristDTO touristDTO) {
//        Tourist tourist = new Tourist();
//        tourist.setName(touristDTO.getName());
//        tourist.setSurname(touristDTO.getSurname());
//        tourist.setEmail(touristDTO.getEmail());
//        tourist.setSex(touristDTO.getSex());
//        tourist.setCountry(touristDTO.getCountry());
//        tourist.setDateOfBirth(this.dateParserService.parseDateFromString(touristDTO.getDateOfBirth()));
//        tourist.setNotes(touristDTO.getNotes());
//        return tourist;
//    }
}
