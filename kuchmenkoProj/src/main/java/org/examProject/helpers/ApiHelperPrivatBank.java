package org.examProject.helpers;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.log4j.Logger;
import org.examProject.dto.ExchangeRatesIdFiveDTO;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.data.TestData.ratesForCurrencyApi;

public class ApiHelperPrivatBank {

    private static final Logger LOGGER = Logger.getLogger(ApiHelperPrivatBank.class);

    private final RequestSpecification requestSpec = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    private final ResponseSpecification responseSpec = new ResponseSpecBuilder()
            .log(LogDetail.ALL)
            .expectStatusCode(HttpStatus.SC_OK)
            .build();

    public ExchangeRatesIdFiveDTO[] fetchExchangeRates() {
        Map<String, String> params = new HashMap<>();
        params.put("json", null);
        params.put("exchange", null);
        params.put("coursid", "5");

        return given()
                .spec(requestSpec)
                .queryParams(params)
                .when()
                .get("https://api.privatbank.ua/p24api/pubinfo")
                .then()
                .spec(responseSpec)
                .extract().body().as(ExchangeRatesIdFiveDTO[].class);
    }

    public void retrieveCurrencyRates(String targetCurrency) {
        ExchangeRatesIdFiveDTO[] rateList = fetchExchangeRates();
        for (ExchangeRatesIdFiveDTO rate : rateList) {
            if (targetCurrency.equals(rate.getCcy())) {
                ratesForCurrencyApi.put("buy", rate.getBuy());
                ratesForCurrencyApi.put("sale", rate.getSale());
                LOGGER.info("API exchange rates for " + targetCurrency + ": " + ratesForCurrencyApi);
                return;
            }
        }
        LOGGER.error("Currency '" + targetCurrency + "' not detected in API results");
        throw new AssertionError("Currency '" + targetCurrency + "' not found in API response");
    }
}
