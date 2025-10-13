package org.bdd.stepDefinitions;

import io.cucumber.java.en.Then;
import org.data.Rate;
import org.data.TestData;

import java.math.BigDecimal;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public class CompareRatesStepDefs {
    @Then("API and UI {string} rates are equal with tolerance {double}")
    public void compare_api_and_ui_rates(String currency, double tol) {
        String key = currency.toUpperCase();

        Rate api = (Rate) TestData.apiRates.get(key);
        Rate ui  = (Rate) TestData.uiRates.get(key);

        assertNotNull("Нет API курса для " + key, api);
        assertNotNull("Нет UI курса для " + key, ui);

        BigDecimal tolerance = BigDecimal.valueOf(tol);

        assertWithin("BUY", key, api.getBuy(), ui.getBuy(), tolerance);
        assertWithin("SALE", key, api.getSale(), ui.getSale(), tolerance);
    }

    private void assertWithin(String side, String currency,
                              BigDecimal a, BigDecimal b, BigDecimal tol) {
        BigDecimal diff = a.subtract(b).abs();
        if (diff.compareTo(tol) > 0) {
            fail(String.format(
                    "%s %s отличается больше чем %s: API=%s, UI=%s, DIFF=%s",
                    side, currency, tol, a, b, diff
            ));
        }
    }
}
