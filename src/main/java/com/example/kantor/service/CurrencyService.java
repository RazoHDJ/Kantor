package com.example.kantor.service;

import com.example.kantor.repository.CurrencyRepository;
import org.springframework.stereotype.Service;

@Service
public class CurrencyService {

    final private CurrencyRepository currencyRepository;

    public CurrencyService(final CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }
}
