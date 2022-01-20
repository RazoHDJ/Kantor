package com.example.kantor.repository;

import com.example.kantor.models.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "currency", path = "currency")
public interface CurrencyRepository extends JpaRepository<Currency, Integer> {
}
