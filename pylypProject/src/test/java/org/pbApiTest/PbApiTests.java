package org.pbApiTest;

import io.restassured.http.ContentType;
import org.apiPB.PbEndPoints;
import org.apiPB.dto.responseDto.ExchangeRatesResponseDto;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class PbApiTests extends BasePbApiTest {

    @Test
    public void getExchangeRates_checkMainFields() {
        String date = "22.03.2022"; // дані тесту

        ExchangeRatesResponseDto actualResponse =
                given()
                        .contentType(ContentType.JSON)
                        .log().all()
                        .queryParam("date", date)
                        .when()
                        .get(PbEndPoints.EXCHANGE_RATES)
                        .then()
                        .log().all()
                        .statusCode(200)
                        .extract().as(ExchangeRatesResponseDto.class);

        //  SoftAssertions
        SoftAssertions softAssertions = new SoftAssertions();

        // перевірки основних полів
        softAssertions.assertThat(actualResponse.getDate())
                .as("Check date")
                .isEqualTo("22.03.2022");

        softAssertions.assertThat(actualResponse.getBank())
                .as("Check bank")
                .isEqualTo("PB");

        softAssertions.assertThat(actualResponse.getBaseCurrency())
                .as("Check baseCurrency")
                .isEqualTo(980);

        softAssertions.assertThat(actualResponse.getBaseCurrencyLit())
                .as("Check baseCurrencyLit")
                .isEqualTo("UAH");

        // Валюти, які мають бути у відповіді на 22.03.2022
        String[] expectedCurrencies = {
                "AUD","AZN","BYN","CAD","CHF","CNY","CZK","DKK","EUR","GBP","GEL",
                "HUF","ILS","JPY","KZT","MDL","NOK","PLN","SEK","SGD","TMT","TRY","UAH","USD","UZS"
        };

// фактичні коди валют з відповіді
        String[] actualCurrencies = actualResponse.getExchangeRate()
                .stream()
                .map(r -> r.getCurrency())
                .toArray(String[]::new);

        actualResponse.getExchangeRate().forEach(rate ->
                softAssertions.assertThat(rate.getBaseCurrency())
                        .as("baseCurrency for " + rate.getCurrency())
                        .isEqualTo("UAH")
        );

        softAssertions.assertThat(actualCurrencies)
                .as("Check list of currencies on 22.03.2022")
                .containsExactlyInAnyOrder(expectedCurrencies);

        softAssertions.assertAll();
    }
}