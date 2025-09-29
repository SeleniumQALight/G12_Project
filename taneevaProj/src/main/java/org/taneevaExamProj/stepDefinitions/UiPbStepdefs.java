package org.taneevaExamProj.stepDefinitions;

import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.bdd.helpers.WebDriverHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.CommonActionsWithElements;

import static org.data.TestData.ratesForCurrencyUi;

public class UiPbStepdefs extends CommonActionsWithElements {
    private static final String PRIVAT_BANK_URL = "https://privatbank.ua/";
    private static final Logger LOGGER = Logger.getLogger(UiPbStepdefs.class);
    protected WebDriverHelper webDriverHelper;

    private static final String CURRENCY_BUY_LOCATOR  = "//td[@id='%s_buy']";
    private static final String CURRENCY_SELL_LOCATOR = "//td[@id='%s_sell']";

    @FindBy (xpath = "//li[@class='desctop exchangeRate']//button[@class='btn exchange-rate']")
    WebElement exchangeRatesButton;

    public UiPbStepdefs(WebDriverHelper webDriverHelper) {
        super(webDriverHelper.getWebDriver());
        this.webDriverHelper = webDriverHelper;
    }

    @When("I get exchange rate for {word} from the website")
    public void iGetExchangeRateForFromTheWebsite(String currency) {
        webDriver.get(PRIVAT_BANK_URL);
        clickOnElement(exchangeRatesButton);

        String buyText  = webDriver.findElement(By.xpath(String.format(CURRENCY_BUY_LOCATOR, currency))).getText();
        String sellText = webDriver.findElement(By.xpath(String.format(CURRENCY_SELL_LOCATOR, currency))).getText();

        double buy  = parseRate(buyText);
        double sell = parseRate(sellText);

        ratesForCurrencyUi.put("buy",  buy);
        ratesForCurrencyUi.put("sale", sell);

        LOGGER.info("UI exchange rates for " + currency + ": " + ratesForCurrencyUi);
    }

    private static double parseRate(String raw) {
        if (raw == null) throw new IllegalArgumentException("rate text is null");
        String norm = raw.trim()
                .replace(" ", "")
                .replace(",", ".")
                .replaceAll("[^0-9.]", "");
        return Double.parseDouble(norm);
    }
}
