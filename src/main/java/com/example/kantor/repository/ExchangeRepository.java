package com.example.kantor.repository;

import com.example.kantor.models.Exchange;
import com.example.kantor.models.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExchangeRepository extends JpaRepository<Exchange, Integer> {

    List<Exchange> findAllByUserId(Integer id);

}
