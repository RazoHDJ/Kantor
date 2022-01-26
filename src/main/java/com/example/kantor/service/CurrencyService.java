package com.example.kantor.service;

import com.example.kantor.models.Currency;
import com.example.kantor.repository.CurrencyRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class CurrencyService {

    final private CurrencyRepository currencyRepository;

    public CurrencyService(final CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    public List<Currency> getAllCurrency() {
       return currencyRepository.findAll().stream()
               .sorted(Comparator.comparing(Currency::getShortName))
               .toList();
    }
}
