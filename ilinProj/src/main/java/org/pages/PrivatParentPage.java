package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.utils.ConfigProvider;

import java.time.Duration;

public class PrivatParentPage {
    protected WebDriver webDriver;
    protected WebDriverWait webDriverWait10;


    public static final String privatBaseURL = ConfigProvider.configProperties.privat_base_url();

    public PrivatParentPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.webDriverWait10 = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        PageFactory.initElements(webDriver, this);
    }

    /**
     * Відкрити конкретну сторінку
     */
    protected void openPage(String relativeUrl) {
        webDriver.get(privatBaseURL + relativeUrl);
    }

    /**
     * Перевірка чи URL містить потрібну частину
     */
    protected boolean isUrlContains(String text) {
        return webDriver.getCurrentUrl().contains(text);
    }
}
