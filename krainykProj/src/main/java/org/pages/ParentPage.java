package org.pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.utils.ConfigProvider;

abstract public class ParentPage extends CommonActionsWithElements {
    static String environment = System.getProperty("env", "aqa");
//    protected String baseUrl = "https://" + environment + "-complexapp.onrender.com";
    protected String baseUrl = ConfigProvider.configProperties.base_url().replace("[env]", environment);
    public ParentPage(WebDriver webDriver) {
        super(webDriver);
    }

    abstract protected String getRelativeUrl();

    protected void checkUrl() {
        Assert.assertEquals("URL is not expected"
        ,baseUrl + getRelativeUrl()
        , webDriver.getCurrentUrl()
        );
    }

    // метод по перевірці чи відкрита потрібна сторінка по патерну
    //https://aqa-complexapp.onrender.com/post/64d21e84903640003414c338
    // regex for 64d21e84903640003414c338
    // [a-zA-Z0-9]{24}

    protected void checkUrlWithPattern(){
        String expectedPattern = baseUrl + getRelativeUrl();
        webDriverWait10.until(ExpectedConditions.urlMatches(expectedPattern));

        Assert.assertTrue("URL is not expected\n" +
                        "Expected url: " + expectedPattern +
                        "\nActual url: " + webDriver.getCurrentUrl(),
                webDriver.getCurrentUrl().matches(expectedPattern));
    }
}
