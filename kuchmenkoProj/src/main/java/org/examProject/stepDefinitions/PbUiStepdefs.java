package org.examProject.stepDefinitions;

import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.bdd.helpers.WebDriverHelper;
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

    @FindBy(xpath = "//td[@id='EUR_buy']")
    WebElement eurBuyCell;
    @FindBy(xpath = "//td[@id='EUR_sell']")
    WebElement eurSellCell;

    @FindBy(xpath = "//td[@id='USD_buy']")
    WebElement usdBuyCell;
    @FindBy(xpath = "//td[@id='USD_sell']")
    WebElement usdSellCell;

    public PbUiStepdefs(WebDriverHelper webDriverHelper) {
        super(webDriverHelper.getWebDriver());
        this.webDriverHelper = webDriverHelper;
    }

    @When("I fetch exchange rate for {} from the UI")
    public void fetchUiExchangeRate(String currencyCode) {
        webDriver.get(BANK_HOME_URL);
        clickOnElement(exchangeRatesButton);

        double eurBuyRate = Double.parseDouble(eurBuyCell.getText().trim());
        double eurSellRate = Double.parseDouble(eurSellCell.getText().trim());
        double usdBuyRate = Double.parseDouble(usdBuyCell.getText().trim());
        double usdSellRate = Double.parseDouble(usdSellCell.getText().trim());

        switch (currencyCode) {
            case "EUR":
                ratesForCurrencyUi.put("buy", eurBuyRate);
                ratesForCurrencyUi.put("sale", eurSellRate);
                break;
            case "USD":
                ratesForCurrencyUi.put("buy", usdBuyRate);
                ratesForCurrencyUi.put("sale", usdSellRate);
                break;
            default:
                ratesForCurrencyUi.put("buy", null);
                ratesForCurrencyUi.put("sale", null);
        }
        LOGGER.info("UI exchange rates for " + currencyCode + ": " + ratesForCurrencyUi);
    }
}
