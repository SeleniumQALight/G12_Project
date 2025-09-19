package org.bdd.stepDefinitions;

import io.cucumber.java.en.Given;
import org.apiPrivatBank.ApiHelperPrivatBank;

public class PbApiStepdefs {
    private ApiHelperPrivatBank apiHelperPB = new ApiHelperPrivatBank();

    @Given("I get exchange rate for {} by API")
    public void iGetExchangeRateForCurrencyByAPI(String currency) {
        apiHelperPB.getCurrencyExchangeRatesApi(currency);
    }
}
