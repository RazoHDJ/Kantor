package com.example.kantor.repository;

import com.example.kantor.models.Exchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "exchange", path = "exchange")
public interface ExchangeRepository extends JpaRepository<Exchange, Integer> {
}
