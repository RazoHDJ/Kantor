package com.example.kantor.dto;

import com.example.kantor.models.Employee;
import com.example.kantor.models.ExchangeRate;
import com.example.kantor.models.User;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeDTO {
    private User user;
    private Employee employee;
    private ExchangeRate fromExchangeRate;
    private ExchangeRate toExchangeRate;
    private BigDecimal fullPrice;
    private BigDecimal amount;
}
