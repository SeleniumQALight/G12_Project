package org.api.dtoPrivat.responsesDtoPrivat;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExchangeRatesByDateDto {
    private String date;
    private String bank;
    private int baseCurrency;
    private String baseCurrencyLit;
    @JsonProperty("exchangeRate")
    private ExchangeRateDto[] exchangeRates;



    public ExchangeRatesByDateDto(){

    }

    public ExchangeRatesByDateDto(String date, String bank, int baseCurrency, String baseCurrencyLit, ExchangeRateDto[] exchangeRateDto) {
        this.date = date;
        this.bank = bank;
        this.baseCurrency = baseCurrency;
        this.baseCurrencyLit = baseCurrencyLit;
        this.exchangeRates = exchangeRateDto;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public int getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(int baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public String getBaseCurrencyLit() {
        return baseCurrencyLit;
    }

    public void setBaseCurrencyLit(String baseCurrencyLit) {
        this.baseCurrencyLit = baseCurrencyLit;
    }

    public ExchangeRateDto[] getExchangeRates() {
        return exchangeRates;
    }

    public void setExchangeRates(ExchangeRateDto[] exchangeRates) {
        this.exchangeRates = exchangeRates;
    }

    @Override
    public String toString() {
        return "ExchangeRatesByDateDto{" +
                "date='" + date + '\'' +
                ", bank='" + bank + '\'' +
                ", baseCurrency=" + baseCurrency +
                ", baseCurrencyLit='" + baseCurrencyLit + '\'' +
                ", exchangeRates=" + exchangeRates +
                '}';
    }
}
