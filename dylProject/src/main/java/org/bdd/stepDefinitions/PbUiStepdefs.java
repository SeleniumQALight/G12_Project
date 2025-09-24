package org.bdd.stepDefinitions;

import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.bdd.helpers.WebDriverHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.CommonActionsWithElements;
import static org.data.TestData.ratesForCurrencyUi;

public class PbUiStepdefs extends CommonActionsWithElements {
    private final static String PRIVAT_BANK_URL = "https://privatbank.ua/";
    protected WebDriverHelper webDriverHelper;
    Logger logger = Logger.getLogger(getClass());
    private static final String CURRENCY_BUY_LOCATOR = "//td[@id='%s_buy']";
    private static final String CURRENCY_SELL_LOCATOR = "//td[@id='%s_sell']";

    @FindBy(xpath = "//li[@class='desctop exchangeRate']//button[@class='btn exchange-rate']")
    WebElement buttonExchangeRates;


    public PbUiStepdefs(WebDriverHelper webDriverHelper) {
        super(webDriverHelper.getWebDriver());
        this.webDriverHelper = webDriverHelper;
    }


    @When("I get exchange rate for {} from the website")
    public void iGetExchangeRateForFromTheWebsite(String currency) {
        webDriver.get(PRIVAT_BANK_URL);
        clickOnElement(buttonExchangeRates);
        String currencyBuy = webDriver.findElement(By.xpath(String.format(CURRENCY_BUY_LOCATOR,currency)))
                .getText().trim();
        String currencySell = webDriver.findElement(By.xpath(String.format(CURRENCY_SELL_LOCATOR,currency)))
                .getText().trim();
        double currencyBuyDouble = Double.parseDouble(currencyBuy);
        double currencySellDouble = Double.parseDouble(currencySell);

                ratesForCurrencyUi.put("buy", currencyBuyDouble);
                ratesForCurrencyUi.put("sale", currencySellDouble);
        logger.info("Rates for " + currency + " by UI: " + ratesForCurrencyUi);
    }
}

