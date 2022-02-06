package com.example.kantor.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "exchange_rate")
public class ExchangeRate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @DecimalMin(value = "0.0001")
    @Column(name = "value", precision = 17, scale = 4)
    private BigDecimal value;
    private Date date;

    @ManyToOne
    @JoinColumn(name = "currency_id", nullable = false)
    private Currency currency;

    @OneToMany(mappedBy = "exchangeFrom")
    @ToString.Exclude
    private Set<Exchange> exchangeFromList; //kurs wymienienia

    @OneToMany(mappedBy = "exchangeTo")
    @ToString.Exclude
    private Set<Exchange> exchangeToList; //kurs otrzymania

}
