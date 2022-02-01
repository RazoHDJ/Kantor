package com.example.kantor.service;

import com.example.kantor.dto.ExchangeDTO;
import com.example.kantor.models.Currency;
import com.example.kantor.models.Exchange;
import com.example.kantor.repository.CurrencyRepository;
import com.example.kantor.repository.ExchangeRepository;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
public class ExchangeService {


    final private ExchangeRepository exchangeRepository;
    final private CurrencyRepository currencyRepository;

    public ExchangeService(final ExchangeRepository exchangeRepository,
                           final CurrencyRepository currencyRepository) {
        this.exchangeRepository = exchangeRepository;
        this.currencyRepository = currencyRepository;
    }


    public List<Exchange> getAllExchanges() {
        return exchangeRepository.findAll();
    }

    public List<Exchange> getUserExchanges(Integer userID){
        return exchangeRepository.findAllByUserId(userID);
    }

    @Transactional
    public void addNewExchange(ExchangeDTO exchangeDTO) {

        Currency currencyFrom = exchangeDTO.getFromExchangeRate().getCurrency();
        currencyFrom.setAmount(currencyFrom.getAmount().add(exchangeDTO.getAmount()));
        currencyRepository.save(currencyFrom);

        Currency currencyTo = exchangeDTO.getToExchangeRate().getCurrency();
        currencyTo.setAmount(currencyTo.getAmount().subtract(exchangeDTO.getFullPrice()));
        currencyRepository.save(currencyTo);

        exchangeRepository.save(Exchange.builder()
                .exchangeDate(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()))
                .amount(exchangeDTO.getAmount())
                .fullPrice(exchangeDTO.getFullPrice())
                .employee(exchangeDTO.getEmployee())
                .exchangeFrom(exchangeDTO.getFromExchangeRate())
                .exchangeTo(exchangeDTO.getToExchangeRate())
                .user(exchangeDTO.getUser())
                .build());
    }
}
