package org.bdd.stepDefinitions;

import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.bdd.helpers.WebDriverHelper;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.CommonActionsWithElements;
import static org.data.TestData.ratesForCurrencyUi;

public class PbUiStepdefs extends CommonActionsWithElements {
    private final static String PRIVAT_BANK_URL = "https://privatbank.ua/";
    protected WebDriverHelper webDriverHelper;
    Logger logger = Logger.getLogger(getClass());

    @FindBy(xpath = "//li[@class='desctop exchangeRate']//button[@class='btn exchange-rate']")
    WebElement buttonExchangeRates;

    @FindBy(xpath = "//td[@id='EUR_buy']")
    WebElement eurBuy;
    @FindBy(xpath = "//td[@id='EUR_sell']")
    WebElement eurSale;

    @FindBy(xpath = "//td[@id='USD_buy']")
    WebElement usdBuy;
    @FindBy(xpath = "//td[@id='USD_sell']")
    WebElement usdSale;

    public PbUiStepdefs(WebDriverHelper webDriverHelper) {
        super(webDriverHelper.getWebDriver());
        this.webDriverHelper = webDriverHelper;
    }


    @When("I get exchange rate for {} from the website")
    public void iGetExchangeRateForFromTheWebsite(String currency) {
        webDriver.get(PRIVAT_BANK_URL);
        clickOnElement(buttonExchangeRates);
        double eurBuyDouble = Double.parseDouble(eurBuy.getText().trim());
        double eurSellDouble = Double.parseDouble(eurSale.getText().trim());
        double usdBuyDouble = Double.parseDouble(usdBuy.getText().trim());
        double usdSellDouble = Double.parseDouble(usdSale.getText().trim());
        switch (currency) {
            case "EUR":
                ratesForCurrencyUi.put("buy", eurBuyDouble);
                ratesForCurrencyUi.put("sale", eurSellDouble);
                break;
            case "USD":
                ratesForCurrencyUi.put("buy", usdBuyDouble);
                ratesForCurrencyUi.put("sale", usdSellDouble);
                break;
            default:
                ratesForCurrencyUi.put("buy", null);
                ratesForCurrencyUi.put("sale", null);
        }
        logger.info("Rates for " + currency + " by UI: " + ratesForCurrencyUi);
    }
}

