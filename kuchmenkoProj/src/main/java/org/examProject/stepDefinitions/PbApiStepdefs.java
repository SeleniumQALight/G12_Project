package org.examProject.stepDefinitions;

import io.cucumber.java.en.Given;
import org.examProject.helpers.ApiHelperPrivatBank;

public class PbApiStepdefs {
    private final ApiHelperPrivatBank privatBankApi = new ApiHelperPrivatBank();

    @Given("I request exchange rate for {} using API")
    public void requestExchangeRateViaApi(String currencyCode) {
        privatBankApi.retrieveCurrencyRates(currencyCode);
    }
}
