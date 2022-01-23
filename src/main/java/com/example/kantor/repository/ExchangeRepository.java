package com.example.kantor.repository;

import com.example.kantor.models.Exchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeRepository extends JpaRepository<Exchange, Integer> {
}
