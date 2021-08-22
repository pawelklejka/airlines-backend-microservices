package com.airlinesmicroservices.tourist.service;

import com.airlinesmicroservices.tourist.DTO.TouristDTO;
import com.airlinesmicroservices.tourist.model.Tourist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface TouristService {
    Page<Tourist> findAll(Pageable pageable);
    Tourist findById(String id);
    void save(TouristDTO tourist);
    void updateTourist(String id, TouristDTO tourist);
    void addTicketToTourist(String id, TouristDTO tourist);
    void deleteTicketFromTourist(String touristId, String flightId);
    void deleteById(String id);
}
