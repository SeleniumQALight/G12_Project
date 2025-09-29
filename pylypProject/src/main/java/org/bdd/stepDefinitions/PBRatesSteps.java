package org.bdd.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.common.mapper.TypeRef;
import org.api.dto.PrivatRateDto;
import org.bdd.helpers.WebDriverHelper;
import org.data.TestDataPB;
import org.junit.Assert;
import org.pages.PrivatBankHomePage;

import java.util.List;

import static io.restassured.RestAssured.given;

public class PBRatesSteps extends MainStepDefs {
    private String ccy;

    public PBRatesSteps(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }

    @Given("I request PrivatBank public rates for {string} from API")
    public void requestApiRates(String ccy) {
        this.ccy = ccy;
        List<PrivatRateDto> all = given()
                .when().get("https://api.privatbank.ua/p24api/pubinfo?exchange&json&coursid=5")
                .then().statusCode(200)
                .extract().as(new TypeRef<List<PrivatRateDto>>() {});

        PrivatRateDto api = all.stream()
                .filter(r -> ccy.equalsIgnoreCase(r.getCcy()))
                .findFirst()
                .orElseThrow(() -> new AssertionError("No " + ccy + " in API"));

        TestDataPB.API_BUY  = toDouble(api.getBuy());
        TestDataPB.API_SALE = toDouble(api.getSale());
    }

    @When("I open PrivatBank main page")
    public void openMain() {
        new PrivatBankHomePage(webDriverHelper.getWebDriver()).open();
    }

    @When("I read UI buy and sale rates for {string}")
    public void readUiRates(String ccy) {
        this.ccy = ccy;
        PrivatRateDto ui = new PrivatBankHomePage(webDriverHelper.getWebDriver()).readRates(ccy);
        TestDataPB.UI_BUY  = toDouble(ui.getBuy());
        TestDataPB.UI_SALE = toDouble(ui.getSale());
    }

    @Then("API buy rate should equal UI buy rate within {double}")
    public void compareBuy(double delta) {
        Assert.assertEquals("BUY " + ccy, TestDataPB.API_BUY, TestDataPB.UI_BUY, delta);
    }

    @Then("API sale rate should equal UI sale rate within {double}")
    public void compareSale(double delta) {
        Assert.assertEquals("SALE " + ccy, TestDataPB.API_SALE, TestDataPB.UI_SALE, delta);
    }

    private static double toDouble(String s) {
        return Double.parseDouble(s.replace('\u00A0',' ')
                .replace(" ", "")
                .replace(',', '.')
                .trim());
    }
}