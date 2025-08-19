package org.api.dtoPrivat.responsesDtoPrivat;

import java.math.BigDecimal;

public class ExchangeRateDto {
    private String baseCurrency;
    private String currency;
    private BigDecimal saleRateNB;
    private BigDecimal purchaseRateNB;
    private BigDecimal saleRate;
    private BigDecimal purchaseRate;

    public ExchangeRateDto(){

    }

    public ExchangeRateDto(String baseCurrency, String currency) {
        this.baseCurrency = baseCurrency;
        this.currency = currency;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getSaleRateNB() {
        return saleRateNB;
    }

    public void setSaleRateNB(BigDecimal saleRateNB) {
        this.saleRateNB = saleRateNB;
    }

    public BigDecimal getPurchaseRateNB() {
        return purchaseRateNB;
    }

    public void setPurchaseRateNB(BigDecimal purchaseRateNB) {
        this.purchaseRateNB = purchaseRateNB;
    }

    public BigDecimal getSaleRate() {
        return saleRate;
    }

    public void setSaleRate(BigDecimal saleRate) {
        this.saleRate = saleRate;
    }

    public BigDecimal getPurchaseRate() {
        return purchaseRate;
    }

    public void setPurchaseRate(BigDecimal purchaseRate) {
        this.purchaseRate = purchaseRate;
    }

    @Override
    public String toString() {
        return "ExchangeRateDto{" +
                "baseCurrency='" + baseCurrency + '\'' +
                ", currency='" + currency + '\'' +
                '}';
    }
}
