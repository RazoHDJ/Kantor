package com.example.kantor.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewExchangeRateDTO {
    @NotBlank
    private String amount;
    @NotBlank
    private String fullName;
    @NotBlank
    private String shortName;
    @NotBlank
    private String value;

}
