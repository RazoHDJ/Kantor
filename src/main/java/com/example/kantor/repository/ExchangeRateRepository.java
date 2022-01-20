package com.example.kantor.repository;

import com.example.kantor.models.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "exchange-rate", path = "exchange-rate")
public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Integer> {
}
