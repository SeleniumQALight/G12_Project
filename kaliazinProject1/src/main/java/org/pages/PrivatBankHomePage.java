package org.pages;

import org.data.Rate;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Locale;

public class PrivatBankHomePage {
    private final WebDriver driver;
    private static final String URL = "https://privatbank.ua/";

    public PrivatBankHomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get(URL);
        acceptCookiesIfPresent();
        clickExchangeRatesButton();
    }

    private void acceptCookiesIfPresent() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            By cookieBtn = By.xpath("//a[contains(@class,'cookies-btn') and contains(@class,'secondary-btn')]");

            WebElement acceptButton = wait.until(ExpectedConditions.visibilityOfElementLocated(cookieBtn));
            wait.until(ExpectedConditions.elementToBeClickable(acceptButton));

            // JS click — надёжный способ обойти перекрытия
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", acceptButton);

            // ждём, пока баннер реально исчезнет
            wait.until(ExpectedConditions.invisibilityOfElementLocated(cookieBtn));
            System.out.println("Cookie banner closed via JS click");
        } catch (TimeoutException e) {
            System.out.println("Cookie banner not displayed");
        } catch (Exception e) {
            System.out.println("Unexpected issue closing cookie banner: " + e.getMessage());
        }
    }

    private void clickExchangeRatesButton() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement ratesButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector("button.btn.exchange-rate")
            ));

            // безопасный клик (через JS на случай перекрытия)
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", ratesButton);

            // ждём, пока таблица с курсами появится
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("USD_buy")));
            System.out.println("Exchange rates table opened successfully");
        } catch (Exception e) {
            System.out.println(" Error clicking exchange rates button: " + e.getMessage());
        }
    }

    public Rate readRate(String currency) {
        String cur = currency.toUpperCase(Locale.ROOT);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            WebElement buyEl = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//td[@id='" + cur + "_buy']")));
            WebElement sellEl = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//td[@id='" + cur + "_sell']")));

            BigDecimal buy = parseRateValue(buyEl.getText());
            BigDecimal sell = parseRateValue(sellEl.getText());

            System.out.printf("%s из UI: покупка=%s, продажа=%s%n", cur, buy, sell);
            return new Rate(buy, sell);
        } catch (TimeoutException e) {
            throw new RuntimeException(" Не найдена строка с валютой: " + cur);
        }
    }

    private BigDecimal parseRateValue(String text) {
        String clean = text.replace(",", ".").replaceAll("[^0-9.]", "").trim();
        return new BigDecimal(clean);
    }
}
