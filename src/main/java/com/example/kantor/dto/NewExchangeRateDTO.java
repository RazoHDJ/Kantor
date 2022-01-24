package com.example.kantor.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewExchangeRateDTO {
    private String amount;
    private String fullName;
    private String shortName;
    private String value;

}
