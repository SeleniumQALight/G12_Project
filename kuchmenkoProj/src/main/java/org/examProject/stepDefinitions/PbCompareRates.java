package org.examProject.stepDefinitions;

import io.cucumber.java.en.Then;
import org.assertj.core.api.SoftAssertions;
import org.data.TestData;

public class PbCompareRates {
    @Then("I validate API and UI exchange rates for {}")
    public void validateApiVsUiRates(String currencyCode) {
        Double apiBuy = TestData.ratesForCurrencyApi.get("buy");
        Double uiBuy = TestData.ratesForCurrencyUi.get("buy");
        Double apiSale = TestData.ratesForCurrencyApi.get("sale");
        Double uiSale = TestData.ratesForCurrencyUi.get("sale");

        SoftAssertions softly = new SoftAssertions();

        if (apiBuy == null || uiBuy == null) {
            throw new AssertionError("Buy rate not available for currency: " + currencyCode);
        }
        softly.assertThat(apiBuy)
                .as("Mismatch in buy rates for " + currencyCode)
                .isEqualTo(uiBuy);

        if (apiSale == null || uiSale == null) {
            throw new AssertionError("Sale rate not available for currency: " + currencyCode);
        }
        softly.assertThat(apiSale)
                .as("Mismatch in sale rates for " + currencyCode)
                .isEqualTo(uiSale);

        softly.assertAll();
    }
}
