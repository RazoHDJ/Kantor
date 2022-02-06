package com.example.kantor.models;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "exchange")
public class Exchange {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "exchange_to_rate_id")
    private ExchangeRate exchangeTo; //kurs wymienienia

    @ManyToOne
    @JoinColumn(name = "exchange_from_rate_id")
    private ExchangeRate exchangeFrom; //kurs otrzymania

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    private Date exchangeDate;

    @NotNull
    @DecimalMin(value = "1")
    private BigDecimal amount;

    @NotNull
    @DecimalMin(value = "0.01")
    private BigDecimal fullPrice;



}
