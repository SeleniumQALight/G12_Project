package org.bdd.stepDefinitions;

import io.cucumber.java.en.Then;
import org.assertj.core.api.SoftAssertions;
import org.data.TestData;


public class PbCompareRates {
    @Then("I compare both exchange rates for {}")
    public void iCompareBothExchangeRates(String currency) {
        Double apiBuy = TestData.ratesForCurrencyApi.get("buy");
        Double uiBuy = TestData.ratesForCurrencyUi.get("buy");
        Double apiSell = TestData.ratesForCurrencyApi.get("sale");
        Double uiSell = TestData.ratesForCurrencyUi.get("sale");
        SoftAssertions softAssertions = new SoftAssertions();
        if (apiBuy == null || uiBuy == null) {
            throw new AssertionError("Buy rate for " + currency + " is missing in API or UI");
        }
        softAssertions.assertThat(apiBuy)
                .isEqualTo(uiBuy)
                .as("Currency rates for buy are not equals");

        if (apiSell == null || uiSell == null) {
            throw new AssertionError("Buy rate for " + currency + " is missing in API or UI");
        }
        softAssertions.assertThat(apiSell)
                .isEqualTo(uiSell)
                .as("Currency rates for sale are not equals");

        softAssertions.assertAll();
    }
}