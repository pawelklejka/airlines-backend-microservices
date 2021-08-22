package com.airlinesmicroservices.tourist.repository;

import com.airlinesmicroservices.tourist.model.Tourist;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

public interface TouristRepository extends MongoRepository<Tourist, String> {
    Optional<Tourist> findByEmail(String email);
}
