package org.apiPrivatBank;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.log4j.Logger;
import org.apiPrivatBank.dto.responseDto.ExchangeRatesIdFiveDTO;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.data.TestData.ratesForCurrencyApi;

public class ApiHelperPrivatBank {

    Logger logger = Logger.getLogger(getClass());

    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    ResponseSpecification responseSpecification = new ResponseSpecBuilder()
            .log(LogDetail.ALL)
            .expectStatusCode(HttpStatus.SC_OK)
            .build();

    public ExchangeRatesIdFiveDTO[] getCurrenciesExchangeRatesByApi() {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("json", null);
        queryParams.put("exchange", null);
        queryParams.put("coursid", "5");
        ExchangeRatesIdFiveDTO[] actualResponse = given()
                .spec(requestSpecification)
                .queryParams(queryParams)
                .when()
                .get("https://api.privatbank.ua/p24api/pubinfo")
                .then()
                .spec(responseSpecification)
                .extract().body().as(ExchangeRatesIdFiveDTO[].class);
        return actualResponse;
    }


    public void getCurrencyExchangeRatesApi(String currency) {
        ExchangeRatesIdFiveDTO[] currenciesRates = getCurrenciesExchangeRatesByApi();
        boolean currencyExists = false;
        for (ExchangeRatesIdFiveDTO rates : currenciesRates) {
            if (currency.equals(rates.getCcy())) {
                ratesForCurrencyApi.put("buy", rates.getBuy());
                ratesForCurrencyApi.put("sale", rates.getSale());
                currencyExists = true;
                break;
            }
        }
        if (!currencyExists) {
            ratesForCurrencyApi.put("buy", null);
            ratesForCurrencyApi.put("sale", null);
            logger.warn(currency + " currency not found in API response");
        }

        logger.info("Rates for " + currency + " by API: " + ratesForCurrencyApi);
    }
}
