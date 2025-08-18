package org.pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.utils.ConfigProvider;

abstract public class ParentPage extends CommonActionsWithElements {
    static String environment = System.getProperty("env", "aqa");
//    protected String baseURL = "https://"+environment+"-complexapp.onrender.com";
    public static String baseURL = ConfigProvider.configProperties.base_url().replace("[env]", environment);

    public ParentPage(WebDriver webDriver) {
        super(webDriver);
    }

    abstract protected String getRelativeUrl();

    protected void checkUrl() {
        Assert.assertEquals("Url is not expected"
                , baseURL + getRelativeUrl()
                , webDriver.getCurrentUrl()

        );
    }

    // метод по перевірці чи відкрита потрібна сторінка по патерну
//https://aqa-complexapp.onrender.com/post/64d21e84903640003414c338
// regex for 64d21e84903640003414c338
// [a-zA-Z0-9]{24}

    protected void checkUrlWithPattern(){
        Assert.assertTrue("URL is not expected \n" +
                        "Expected url: " + baseURL +getRelativeUrl() +
                        "\n Actual url: " + webDriver.getCurrentUrl(),
                webDriver.getCurrentUrl().matches(baseURL + getRelativeUrl ()));
    }

}
