package com.example.kantor.service;

import com.example.kantor.dto.NewExchangeRateDTO;
import com.example.kantor.exceptions.ExchangeRateNotFound;
import com.example.kantor.models.Currency;
import com.example.kantor.models.ExchangeRate;
import com.example.kantor.repository.CurrencyRepository;
import com.example.kantor.repository.ExchangeRateRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class ExchangeRateService {


    final private ExchangeRateRepository exchangeRateRepository;
    final private CurrencyRepository currencyRepository;

    public ExchangeRateService(final ExchangeRateRepository exchangeRateRepository, final CurrencyRepository currencyRepository) {
        this.exchangeRateRepository = exchangeRateRepository;
        this.currencyRepository = currencyRepository;
    }


    public List<ExchangeRate> getAllExchangeRate() {
        return exchangeRateRepository.findAll().stream()
                .sorted(Comparator.comparing(a -> a.getCurrency().getShortName()))
                .toList();
    }


    public Optional<ExchangeRate> getExchangeRate(Integer id) {
        return exchangeRateRepository.findById(id);
    }

    public void addNewExchange(NewExchangeRateDTO newExchange) {
        exchangeRateRepository.save(
                ExchangeRate.builder()
                        .date(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()))
                        .value(new BigDecimal(newExchange.getValue()
                                .replace(",", "."))
                                .setScale(4, RoundingMode.HALF_UP))
                        .currency(currencyRepository.save(Currency.builder()
                                .amount(new BigDecimal(newExchange
                                        .getAmount().replace(",", "."))
                                        .setScale(2, RoundingMode.HALF_UP))
                                .fullName(newExchange.getFullName())
                                .shortName(newExchange.getShortName())
                                .build()))
                        .build());
    }

    public void delete(Integer exchangeID) {
        ExchangeRate exchangeRate = exchangeRateRepository.findById(exchangeID)
                .orElseThrow(() -> new ExchangeRateNotFound(exchangeID));
        currencyRepository.delete(exchangeRate.getCurrency());
        exchangeRateRepository.delete(exchangeRate);

    }

    public void updateExchange(Integer id, NewExchangeRateDTO newExchange) {
        ExchangeRate toUpdateExchange = exchangeRateRepository.findById(id)
                .orElseThrow(() -> new ExchangeRateNotFound(id));
        //update exchange
        toUpdateExchange.setDate(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
        toUpdateExchange.setValue(new BigDecimal(newExchange.getValue()
                .replace(",", "."))
                .setScale(4, RoundingMode.HALF_UP));
        //update currency
        Currency currencyToUpdate = toUpdateExchange.getCurrency();
        currencyToUpdate.setAmount(new BigDecimal(newExchange.getAmount()
                .replace(",", "."))
                .setScale(2, RoundingMode.HALF_UP));
        currencyToUpdate.setFullName(newExchange.getFullName());
        currencyToUpdate.setShortName(newExchange.getShortName());
        toUpdateExchange.setCurrency(currencyToUpdate);
        exchangeRateRepository.save(toUpdateExchange);

    }
}
