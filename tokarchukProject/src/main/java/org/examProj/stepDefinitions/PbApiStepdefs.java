package org.examProj.stepDefinitions;

import io.cucumber.java.en.Given;
import org.examProj.helpers.ApiHelperPrivatBank;

public class PbApiStepdefs {
    private final ApiHelperPrivatBank privatBankApi = new ApiHelperPrivatBank();

    @Given("I request exchange rate for {} using API")
    public void requestExchangeRateViaApi(String currencyCode) {
        privatBankApi.retrieveCurrencyRates(currencyCode);
    }
}


