package org.examProj.dto;


import lombok.Data;

@Data
public class ExchangeRatesIdFiveDTO {
    private String ccy;       // "USD", "EUR", "PLN"
    private String base_ccy;  // "UAH"
    private Double buy;       // 40.92
    private Double sale;      // 41.52
}