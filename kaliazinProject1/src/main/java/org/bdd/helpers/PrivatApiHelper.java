package org.bdd.helpers;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.api.dto.privatDto.PubInfoRateDto;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;

import static io.restassured.RestAssured.given;

public class PrivatApiHelper {
    private static final RequestSpecification spec = new RequestSpecBuilder()
            .setBaseUri("https://api.privatbank.ua")
            .setBasePath("/p24api")
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    private static final ResponseSpecification ok200 = new ResponseSpecBuilder()
            .log(LogDetail.ALL)
            .expectStatusCode(HttpStatus.SC_OK)
            .build();

    public PubInfoRateDto[] getAllRates() {
        return given().spec(spec)
                .when().get("/pubinfo?json&exchange&coursid=5")
                .then().spec(ok200)
                .extract().as(PubInfoRateDto[].class);
    }

    public Optional<PubInfoRateDto> getRateByCurrency(String currency) {
        return Arrays.stream(getAllRates())
                .filter(r -> r.getCurrency().equalsIgnoreCase(currency))
                .findFirst();
    }

    public static BigDecimal normalized(BigDecimal v) {
        return v == null ? null : v.setScale(2, BigDecimal.ROUND_HALF_UP);
    }
}
