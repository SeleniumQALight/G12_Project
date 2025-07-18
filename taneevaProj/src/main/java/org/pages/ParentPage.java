package org.pages;

import io.opentelemetry.sdk.autoconfigure.spi.ConfigProperties;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

abstract class ParentPage extends CommonActionsWithElements {
    static String environment = System.getProperty("evn", "aqa");
    protected String baseURL = "https://"+environment+"-complexapp.onrender.com";
    protected String baseURL = ConfigProperties.configProperties.base_url().replace("[env]");
    public ParentPage(WebDriver webDriver) {
        super(webDriver);
    }

    abstract protected String getRelatedURL();

    protected void checkURL() {
        Assert.assertEquals("URL is not expected"
                , baseURL + getRelatedURL()
                , webDriver.getCurrentUrl());
    }

    // Method to check if the current URL for patern is correct
    //https://aqa-complexapp.onrender.com/post/64d21e84903640003414c338
    // regex for 64d21e84903640003414c338
    // [a-zA-Z0-9]{24}


    protected void checkURLWithPattern() {
        Assert.assertTrue("URL is not expected \n" +
                        "Expected url: " + baseURL + getRelatedURL() +
                        "\n Actual url: " + webDriver.getCurrentUrl(),
                webDriver.getCurrentUrl().matches(baseURL + getRelatedURL()));
    }

    protected WebDriverWait webDriverWait10 = new WebDriverWait(webDriver, Duration.ofSeconds(10));

    protected void waitForUrlToMatch(String regex) {
        webDriverWait10.until(ExpectedConditions.urlMatches(baseURL + regex));
    }

}
