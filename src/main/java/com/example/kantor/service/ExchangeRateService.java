package com.example.kantor.service;

import com.example.kantor.models.ExchangeRate;
import com.example.kantor.repository.ExchangeRateRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExchangeRateService {


    final private ExchangeRateRepository exchangeRateRepository;

    public ExchangeRateService(final ExchangeRateRepository exchangeRateRepository) {
        this.exchangeRateRepository = exchangeRateRepository;
    }


    public List<ExchangeRate> getAllExchangeRate() {
        return exchangeRateRepository.findAll();
    }


}
