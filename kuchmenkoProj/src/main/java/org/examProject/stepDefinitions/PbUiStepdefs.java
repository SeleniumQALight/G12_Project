package org.examProject.stepDefinitions;

import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.bdd.helpers.WebDriverHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.CommonActionsWithElements;

import static org.data.TestData.ratesForCurrencyUi;

public class PbUiStepdefs extends CommonActionsWithElements {
    private static final String BANK_HOME_URL = "https://privatbank.ua/";
    protected WebDriverHelper webDriverHelper;
    private static final Logger LOGGER = Logger.getLogger(PbUiStepdefs.class);

    @FindBy(xpath = "//li[@class='desctop exchangeRate']//button[@class='btn exchange-rate']")
    WebElement exchangeRatesButton;

    public PbUiStepdefs(WebDriverHelper webDriverHelper) {
        super(webDriverHelper.getWebDriver());
        this.webDriverHelper = webDriverHelper;
    }

    private WebElement getRateCell(WebDriver driver, String currency, String type) {
        String xpath = String.format("//td[@id='%s_%s']", currency, type);
        return driver.findElement(By.xpath(xpath));
    }

    @When("I fetch exchange rate for {} from the UI")
    public void fetchUiExchangeRate(String currencyCode) {
        webDriver.get(BANK_HOME_URL);
        clickOnElement(exchangeRatesButton);

        double buyRate = Double.parseDouble(getRateCell(webDriver, currencyCode, "buy").getText().trim());
        double sellRate = Double.parseDouble(getRateCell(webDriver, currencyCode, "sell").getText().trim());

        ratesForCurrencyUi.put("buy", buyRate);
        ratesForCurrencyUi.put("sale", sellRate);

        LOGGER.info("UI exchange rates for " + currencyCode + ": " + ratesForCurrencyUi);
    }
}