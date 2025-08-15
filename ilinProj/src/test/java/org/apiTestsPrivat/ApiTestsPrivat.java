package org.apiTestsPrivat;

import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.api.PrivatEndPoints;
import org.api.dtoPrivat.responsesDtoPrivat.ExchangeRateDto;
import org.api.dtoPrivat.responsesDtoPrivat.ExchangeRatesByDateDto;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class ApiTestsPrivat extends BaseApiTestPrivat {
    final String DATE = "22.03.2022";
    private Logger logger = Logger.getLogger(getClass());

    @Test
    public void getAllExchangeRates(){
        ExchangeRatesByDateDto actualResponse =
            given()
                .contentType(ContentType.JSON)
                .log().all()
            .when()
                .get(PrivatEndPoints.EXCHANGE_RATES, DATE)
            .then()
                .log().all()
                .statusCode(200)
            .body("date", equalTo(DATE))
            .body("bank", equalTo("PB"))
            .body("baseCurrency", equalTo(980))
            .body("baseCurrencyLit", equalTo("UAH"))

        .extract().body().as(ExchangeRatesByDateDto.class);

        logger.info("Requested date = " + actualResponse.getDate());
        logger.info("Bank = " + actualResponse.getBank());
        logger.info("Base Currency = " + actualResponse.getBaseCurrency());
        logger.info("Base Currency Lit = " + actualResponse.getBaseCurrencyLit());

        String[][] expectedPairs = {
                {"AUD"},
                {"AZN"},
                {"BYN"},
                {"CAD"},
                {"CHF"},
                {"CNY"},
                {"CZK"},
                {"DKK"},
                {"EUR"},
                {"GBP"},
                {"GEL"},
                {"HUF"},
                {"ILS"},
                {"JPY"},
                {"KZT"},
                {"MDL"},
                {"NOK"},
                {"PLN"},
                {"SEK"},
                {"SGD"},
                {"TMT"},
                {"TRY"},
                {"UAH"},
                {"USD"},
                {"UZS"}
        };

        ExchangeRateDto[] expectedExchangeRates = new ExchangeRateDto[expectedPairs.length];
        for (int i = 0; i < expectedPairs.length; i++) {
            expectedExchangeRates[i] = new ExchangeRateDto("UAH", expectedPairs[i][0]);
        }

        ExchangeRatesByDateDto expectedResult =
                new ExchangeRatesByDateDto("22.03.2022", "PB", 980, "UAH", expectedExchangeRates);

        SoftAssertions soft = new SoftAssertions();

        soft.assertThat(actualResponse)
                .usingRecursiveComparison()
                .ignoringFieldsMatchingRegexes(
                        "exchangeRates", "saleRateNB", "purchaseRateNB"," saleRate", "purchaseRate"
                )
                .isEqualTo(expectedResult);

        soft.assertAll();






    }


}
