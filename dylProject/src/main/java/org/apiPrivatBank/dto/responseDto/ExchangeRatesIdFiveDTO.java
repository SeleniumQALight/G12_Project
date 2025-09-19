package org.apiPrivatBank.dto.responseDto;

import lombok.Data;

@Data
public class ExchangeRatesIdFiveDTO {
    String ccy;
    String base_ccy;
    Double buy;
    Double sale;
}
