package org.taneevaExamProj.stepDefinitions;

import io.cucumber.java.en.Then;
import org.assertj.core.api.SoftAssertions;
import org.data.TestData;

import static org.assertj.core.api.Assertions.within;

public class PbCompareRates {

        @Then("I compare both exchange rates for {word}")
        public void iCompareBothExchangeRates(String currency) {
            Double apiBuy  = TestData.ratesForCurrencyApi.get("buy");
            Double apiSale = TestData.ratesForCurrencyApi.get("sale");
            Double uiBuy   = TestData.ratesForCurrencyUi.get("buy");
            Double uiSale  = TestData.ratesForCurrencyUi.get("sale");

            if (apiBuy == null || uiBuy == null) {
                throw new AssertionError("Buy rate for " + currency + " is missing in API or UI");
            }
            if (apiSale == null || uiSale == null) {
                throw new AssertionError("Sale rate for " + currency + " is missing in API or UI");
            }

            double tolerance = 0.05;

            SoftAssertions softly = new SoftAssertions();
            softly.assertThat(uiBuy)
                    .as("Currency BUY rates differ more than " + tolerance + " for " + currency)
                    .isCloseTo(apiBuy, within(tolerance));
            softly.assertThat(uiSale)
                    .as("Currency SALE rates differ more than " + tolerance + " for " + currency)
                    .isCloseTo(apiSale, within(tolerance));
            softly.assertAll();
        }
}
