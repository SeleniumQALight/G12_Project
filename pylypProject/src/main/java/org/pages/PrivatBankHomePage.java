package org.pages;

import org.api.dto.PrivatRateDto;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PrivatBankHomePage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public PrivatBankHomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(8));
    }

    public PrivatBankHomePage open() {
        driver.get("https://privatbank.ua/");
        acceptCookiesIfPresent();
        return this;
    }

    public PrivatRateDto readRates(String ccy) {
        openRatesWidgetIfClosed();

        By rowBy = By.xpath("//div[@id='widgetContainer']//table//tr[td[1][contains(normalize-space(),'" + ccy + "')]]");
        WebElement row = wait.until(ExpectedConditions.visibilityOfElementLocated(rowBy));

        String buy  = row.findElement(By.xpath("./td[2]")).getText().trim();
        String sale = row.findElement(By.xpath("./td[3]")).getText().trim();

        PrivatRateDto dto = new PrivatRateDto();
        dto.setCcy(ccy);
        dto.setBuy(buy);
        dto.setSale(sale);
        return dto;
    }

    private void acceptCookiesIfPresent() {
        try {
            By ok = By.cssSelector(".cookies-content .btn-success, .cookies .btn-success");
            WebElement btn = new WebDriverWait(driver, Duration.ofSeconds(3))
                    .until(ExpectedConditions.elementToBeClickable(ok));
            btn.click();
        } catch (TimeoutException | NoSuchElementException ignored) { }
    }

    private void openRatesWidgetIfClosed() {
        if (isWidgetOpen()) return;

        By[] triggers = new By[] {
                By.cssSelector("[data-bs-target='#widgetContainer']"),
                By.cssSelector("a[href*='widgetContainer']"),
                By.cssSelector("header [class*='rate'], header [class*='course']") // запасний варіант
        };
        for (By t : triggers) {
            try {
                WebElement el = driver.findElement(t);
                el.click();
                if (isWidgetOpen()) return;
            } catch (NoSuchElementException ignored) { }
        }

        ((JavascriptExecutor)driver).executeScript(
                "var m=document.getElementById('widgetContainer');" +
                        "if(m){m.style.display='block'; m.classList.add('show');}"
        );

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("widgetContainer")));
    }

    private boolean isWidgetOpen() {
        try {
            WebElement m = driver.findElement(By.id("widgetContainer"));
            String cls = m.getAttribute("class");
            String style = m.getAttribute("style");
            return (cls != null && cls.contains("show")) || (style != null && style.contains("display: block"));
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}