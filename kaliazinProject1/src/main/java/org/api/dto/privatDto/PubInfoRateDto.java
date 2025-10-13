package org.api.dto.privatDto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PubInfoRateDto {

    @JsonProperty("ccy")
    private String currency;

    @JsonProperty("base_ccy")
    private String baseCurrency;

    @JsonProperty("buy")
    private BigDecimal buy;

    @JsonProperty("sale")
    private BigDecimal sale;

    public PubInfoRateDto() {}

    public String getCurrency() { return currency; }
    public String getBaseCurrency() { return baseCurrency; }
    public BigDecimal getBuy() { return buy; }
    public BigDecimal getSale() { return sale; }

    @Override
    public String toString() {
        return "PubInfoRateDto{" +
                "currency='" + currency + '\'' +
                ", baseCurrency='" + baseCurrency + '\'' +
                ", buy=" + buy +
                ", sale=" + sale +
                '}';
    }
}
