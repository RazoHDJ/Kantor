package com.example.kantor.models;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "currency")
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String fullName;
    private String shortName;
    private BigDecimal amount;

    @OneToMany(mappedBy = "currency")
    private Set<ExchangeRate> rateSet;

}
