package org.bdd.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.bdd.helpers.WebDriverHelper;
import org.data.Rate;
import org.data.TestData;
import org.pages.PrivatBankHomePage;

public class PrivatUiStepDefs {
    private final WebDriverHelper webDriverHelper;
    private PrivatBankHomePage home;

    public PrivatUiStepDefs(WebDriverHelper webDriverHelper) {
        this.webDriverHelper = webDriverHelper;
    }

    @And("I open PrivatBank homepage")
    public void openHomepage() {
        home = new PrivatBankHomePage(webDriverHelper.getWebDriver());
        home.open();
    }

    @When("I read {string} rate from UI")
    public void readRateFromUi(String currency) {
        Rate ui = home.readRate(currency);
        TestData.uiRates.put(currency.toUpperCase(), ui);
    }
}
