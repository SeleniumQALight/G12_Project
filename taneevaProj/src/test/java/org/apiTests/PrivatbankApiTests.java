package org.apiTests;

import io.restassured.RestAssured;
import org.apache.log4j.Logger;
import org.api.PrivatEndPoints;
import org.api.dtoPrivate.ExchangeRateByDateDto;
import org.api.dtoPrivate.ExchangeRateDto;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class PrivatbankApiTests {
    private static final String DATE = "22.03.2022";
    private Logger logger = Logger.getLogger(getClass());

    @Test
    public void getArcExchangeRatesAndCheckMainFields() {
        RestAssured.baseURI = "https://api.privatbank.ua/p24api";

        List<String> currencies = List.of("AUD","AZN","BYN","CAD","CHF","CNY","CZK","DKK","EUR","GBP",
                "GEL","HUF","ILS","JPY","KZT","MDL","NOK","PLN","SEK","SGD","TMT","TRY","UAH","USD","UZS");

        List<ExchangeRateDto> exchangeRateCurrencies = new ArrayList<>();
        for (String currency : currencies) {
            exchangeRateCurrencies.add(new ExchangeRateDto("UAH", currency));
        }

        ExchangeRateByDateDto actualResponse = given()
                .queryParam("date", DATE)
                .log().all()
                .when()
                .get(PrivatEndPoints.EXCHANGE_RATES)
                .then()
                .log().all()
                .extract().as(ExchangeRateByDateDto.class);

        ExchangeRateByDateDto expectedObject =
                new ExchangeRateByDateDto(DATE, "PB", 980, "UAH", exchangeRateCurrencies);

        SoftAssertions softly = new SoftAssertions();

        softly.assertThat(actualResponse)
                .usingRecursiveComparison()
                .ignoringFields(
                        "exchangeRate.saleRateNB",
                        "exchangeRate.purchaseRateNB",
                        "exchangeRate.saleRate",
                        "exchangeRate.purchaseRate"
                )
                .isEqualTo(expectedObject);
        softly.assertAll();
    }

    @Test
    public void getArcExchangeRatesAndCheckRates() {
        RestAssured.baseURI = "https://api.privatbank.ua/p24api";

        ExchangeRateByDateDto actualResponse = given()
                .queryParam("date", DATE)
                .log().all()
                .when()
                .get(PrivatEndPoints.EXCHANGE_RATES)
                .then()
                .log().all()
                .extract().as(ExchangeRateByDateDto.class);

        SoftAssertions softly = new SoftAssertions();

        for (ExchangeRateDto rate : actualResponse.getExchangeRate()) {
            if (rate.getSaleRateNB() != null) {
                softly.assertThat(rate.getSaleRateNB())
                        .as(rate.getCurrency() + " saleRateNB")
                        .isGreaterThan(0.0);
            }
            if (rate.getPurchaseRateNB() != null) {
                softly.assertThat(rate.getPurchaseRateNB())
                        .as(rate.getCurrency() + " purchaseRateNB")
                        .isGreaterThan(0.0);
            }
            if (rate.getSaleRate() != null) {
                softly.assertThat(rate.getSaleRate())
                        .as(rate.getCurrency() + " saleRate")
                        .isGreaterThan(0.0);
            }
            if (rate.getPurchaseRate() != null) {
                softly.assertThat(rate.getPurchaseRate())
                        .as(rate.getCurrency() + " purchaseRate")
                        .isGreaterThan(0.0);
            }
        }
        logger.info("Exchange rates for all currencies are more than 0");
        softly.assertAll();
    }
}
