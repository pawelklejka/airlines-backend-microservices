package com.airlinesmicroservices.tourist.service;

import com.airlinesmicroservices.tourist.model.Tourist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface TouristService {
    Page<Tourist> findAll(Pageable pageable);
    Tourist findById(Long id);
    void save(Tourist tourist);
    void updateTourist(Long id, Tourist tourist);
    void addTicketToTourist(Long id, Tourist tourist);
    void deleteTicketFromTourist(Long touristId, Long flightId);
    void deleteById(Long id);
}
