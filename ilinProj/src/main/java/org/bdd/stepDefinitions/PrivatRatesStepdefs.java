package org.bdd.stepDefinitions;

import io.cucumber.java.en.*;
import io.restassured.common.mapper.TypeRef;
import org.data.TestData;
import org.pages.PrivatPageProvider;

import java.math.BigDecimal;
import java.util.*;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class PrivatRatesStepdefs extends MainStepDefs {

    private String apiBase;
    private final PrivatPageProvider privatPages;

    public PrivatRatesStepdefs(org.bdd.helpers.WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
        this.privatPages = new org.pages.PrivatPageProvider(webDriverHelper.getWebDriver());
    }

    @Given("I set PrivatBank API base url {string}")
    public void iSetApiBase(String base) {
        this.apiBase = base;
    }

    @When("I fetch {string} {string} rate from Privat API coursid {string}")
    public void iFetchRateFromApi(String ccy, String side, String coursid) {
        List<Map<String, String>> rates = given()
                .baseUri(apiBase)
                .queryParam("json")
                .queryParam("exchange")
                .queryParam("coursid", coursid)
                .when()
                .get("/p24api/pubinfo")
                .then()
                .statusCode(200)
                .extract().as(new TypeRef<List<Map<String, String>>>() {});

        // знаходимо потрібну валюту
        Map<String, String> row = rates.stream()
                .filter(m -> ccy.equalsIgnoreCase(m.get("ccy")))
                .findFirst()
                .orElseThrow(() -> new AssertionError("Currency not found in API: " + ccy));

        String raw = row.get(side.toLowerCase()); // "buy" або "sale"
        // BigDecimal з нормалізацією (крапка як десятковий роздільник)
        BigDecimal apiValue = new BigDecimal(raw.replace(",", "."));

        TestData.apiRates.put(TestData.key(ccy, side), apiValue);
    }

    @When("I open PrivatBank home page")
    public void iOpenPrivatHome() {
        privatPages.getHomePage().openPrivatHome(); // реалізуємо нижче
    }

    @When("I read {string} {string} rate from UI")
    public void iReadRateFromUi(String ccy, String side) {
        BigDecimal uiValue = privatPages.getHomePage()
                .readRate(ccy, side); // реалізуємо в Page Object
        TestData.uiRates.put(TestData.key(ccy, side), uiValue);
    }

    @Then("API and UI rates for {string} {string} should be equal within {string}")
    public void iCompare(String ccy, String side, String toleranceStr) {
        BigDecimal apiV = TestData.apiRates.get(TestData.key(ccy, side));
        BigDecimal uiV  = TestData.uiRates.get(TestData.key(ccy, side));
        assertNotNull("API rate is null", apiV);
        assertNotNull("UI rate is null", uiV);

        BigDecimal tol = new BigDecimal(toleranceStr);
        BigDecimal diff = apiV.subtract(uiV).abs();

        assertTrue(String.format("Rates differ too much for %s %s: API=%s UI=%s (diff=%s, tol=%s)",
                        ccy, side, apiV, uiV, diff, tol),
                diff.compareTo(tol) <= 0);
    }
}
