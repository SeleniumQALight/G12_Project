package org.apiTests;

import io.restassured.RestAssured;
import org.apache.log4j.Logger;
import org.apiPrivatBank.EndPointsPrivatBank;
import org.apiPrivatBank.dto.responseDto.ExchangeRateByDateDto;
import org.apiPrivatBank.dto.responseDto.ExchangeRateDto;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class PrivatbankApiTests {
    private Logger logger = Logger.getLogger(getClass());

    @Test
    public void getArcExchangeRatesAndCheckMainFields() {
        RestAssured.baseURI = "https://api.privatbank.ua/p24api/";
        List<String> currencies = List.of("AUD", "AZN", "BYN", "CAD", "CHF", "CNY", "CZK", "DKK", "EUR", "GBP",
                "GEL", "HUF", "ILS", "JPY", "KZT", "MDL", "NOK", "PLN", "SEK", "SGD",
                "TMT", "TRY", "UAH", "USD", "UZS");

        List<ExchangeRateDto> exchangeRateCurrencies = new ArrayList<>();
        for (String currency : currencies) {
            exchangeRateCurrencies.add(new ExchangeRateDto("UAH", currency));
        }

        ExchangeRateByDateDto actualResponse = given()
                .queryParam("date", "22.03.2022")
                .log().all()
                .when()
                .get(EndPointsPrivatBank.EXCHANGE_RATES)
                .then()
                .log().all()
                .extract().body().as(ExchangeRateByDateDto.class);


        ExchangeRateByDateDto expectedObject =
                new ExchangeRateByDateDto(
                        "22.03.2022", "PB", 980, "UAH",
                        exchangeRateCurrencies);

        SoftAssertions softAssertions = new SoftAssertions();

            softAssertions.assertThat(actualResponse)
                    .usingRecursiveComparison()
                            .ignoringFields(
                            "exchangeRate.saleRateNB",
                            "exchangeRate.purchaseRateNB",
                            "exchangeRate.saleRate",
                            "exchangeRate.purchaseRate"
                    )
                    .isEqualTo(expectedObject);
        softAssertions.assertAll();
    }

    @Test
    public void getArcExchangeRatesAndCheckRates() {
        RestAssured.baseURI = "https://api.privatbank.ua/p24api/";
        ExchangeRateByDateDto actualResponse = given()
                .queryParam("date", "22.03.2022")
                .log().all()
                .when()
                .get(EndPointsPrivatBank.EXCHANGE_RATES)
                .then()
                .log().all()
                .extract().body().as(ExchangeRateByDateDto.class);

        SoftAssertions softAssertions = new SoftAssertions();

        for (ExchangeRateDto rate : actualResponse.getExchangeRate()) {
            if (rate.getSaleRateNB() != null) {
                softAssertions
                        .assertThat(rate.getSaleRateNB())
                        .as(rate.getCurrency() + " saleRateNB")
                        .isGreaterThan(0);
            }
            if (rate.getPurchaseRateNB() != null) {
                softAssertions
                        .assertThat(rate.getPurchaseRateNB())
                        .as(rate.getCurrency() + " purchaseRateNB")
                        .isGreaterThan(0);
            }
            if (rate.getSaleRate() != null) {
                softAssertions
                        .assertThat(rate.getSaleRate())
                        .as(rate.getCurrency() + " purchaseRate")
                        .isGreaterThan(0);
            }
            if (rate.getPurchaseRate() != null) {
                softAssertions
                        .assertThat(rate.getPurchaseRate())
                        .as(rate.getCurrency() + " saleRate")
                        .isGreaterThan(0);
            }

        }
        logger.info("Exchange rates for all currencies are more than 0");
        softAssertions.assertAll();
    }
}


