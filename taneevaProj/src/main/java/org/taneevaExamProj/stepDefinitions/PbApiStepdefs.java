package org.taneevaExamProj.stepDefinitions;

import io.cucumber.java.en.Given;
import org.taneevaExamProj.helpers.ApiHelperPrivatBank;

public class PbApiStepdefs {

    private final ApiHelperPrivatBank api = new ApiHelperPrivatBank();

    @Given("I get exchange rate for {word} by API")
    public void iGetExchangeRateForCurrencyByAPI(String currency) {
        api.getCurrencyExchangeRatesApi(currency);
    }
}
