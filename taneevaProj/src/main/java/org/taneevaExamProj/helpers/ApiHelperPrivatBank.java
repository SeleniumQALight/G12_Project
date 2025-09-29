package org.taneevaExamProj.helpers;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.taneevaExamProj.dto.ExchangeRatesIdFiveDTO;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.data.TestData.ratesForCurrencyApi;

public class ApiHelperPrivatBank {
    private static final Logger LOGGER = Logger.getLogger(ApiHelperPrivatBank.class);

    private final RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("https://api.privatbank.ua")
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    private final ResponseSpecification responseSpec = new ResponseSpecBuilder()
            .log(LogDetail.ALL)
            .expectStatusCode(HttpStatus.SC_OK)
            .build();

    /** GET /p24api/pubinfo?json&exchange&coursid=5 */
    public ExchangeRatesIdFiveDTO[] fetchExchangeRates() {
        Map<String, String> params = new HashMap<>();
        params.put("json", "");
        params.put("exchange", "");
        params.put("coursid", "5");

        return given()
                .spec(requestSpec)
                .queryParams(params)
                .when()
                .get("/p24api/pubinfo")
                .then()
                .spec(responseSpec)
                .extract()
                .as(ExchangeRatesIdFiveDTO[].class);
    }

    /** Put buy/sale selected currency in TestData.ratesForCurrencyApi */
    public void getCurrencyExchangeRatesApi(String currency) {
        ExchangeRatesIdFiveDTO[] list = fetchExchangeRates();

        for (ExchangeRatesIdFiveDTO rate : list) {
            if (currency.equalsIgnoreCase(rate.getCcy())) {
                ratesForCurrencyApi.put("buy",  rate.getBuy());
                ratesForCurrencyApi.put("sale", rate.getSale());
                LOGGER.info("Rates for " + currency + " by API: " + ratesForCurrencyApi);
                return;
            }
        }

        String msg = "Currency '" + currency + "' not found in API response";
        LOGGER.error(msg);
        throw new AssertionError(msg);
    }
}
