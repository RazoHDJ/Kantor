package com.example.kantor.models;

import lombok.*;
import org.springframework.context.annotation.Configuration;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "exchange_rate")
public class ExchangeRate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(precision = 4)
    private BigDecimal value;
    private Date date;

    @ManyToOne
    @JoinColumn(name = "currency_id", nullable = false)
    private Currency currency;

    @OneToMany(mappedBy = "exchangeRate")
    private Set<Exchange> exchangeRateList; //kurs wymienienia

    @OneToMany(mappedBy = "receiptRate")
    private Set<Exchange> receiptRateList; //kurs otrzymania

}
