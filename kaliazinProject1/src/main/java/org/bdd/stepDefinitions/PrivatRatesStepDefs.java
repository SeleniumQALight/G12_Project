package org.bdd.stepDefinitions;

import io.cucumber.java.en.Given;
import org.api.dto.privatDto.PubInfoRateDto;
import org.bdd.helpers.PrivatApiHelper;
import org.data.Rate;
import org.data.TestData;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

import static org.junit.Assert.assertTrue;

public class PrivatRatesStepDefs {
    private final PrivatApiHelper api = new PrivatApiHelper();

    @Given("I get {string} rate from PrivatBank API")
    public void i_get_rate_from_api(String currency) {
        Optional<PubInfoRateDto> rateOpt = api.getRateByCurrency(currency);
        assertTrue("Валюта не найдена в API: " + currency, rateOpt.isPresent());

        PubInfoRateDto r = rateOpt.get();
        Rate rate = new Rate(normalize(r.getBuy()), normalize(r.getSale()));
        TestData.apiRates.put(currency.toUpperCase(), rate);
    }

    private BigDecimal normalize(BigDecimal v) {
        return v == null ? null : v.setScale(2, RoundingMode.HALF_UP);
    }
}
