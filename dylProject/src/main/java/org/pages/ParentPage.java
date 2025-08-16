package org.pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.utils.ConfigProvider;

abstract public class ParentPage extends CommonActionsWithElements {
    static String environment = System.getProperty("env","aqa");
//    protected String baseUrl = "https://"+environment+"-complexapp.onrender.com";
    public static String baseUrl = ConfigProvider.configProperties.base_url()
        .replace("[env]", environment);

    public ParentPage(WebDriver webDriver) {
        super(webDriver);
    }

    abstract String getRelativeURL();


    protected void checkUrl() {
        Assert.assertEquals("URL is not expected "
                , baseUrl + getRelativeURL()
        ,webDriver.getCurrentUrl()
        );
    }

    // метод по перевірці чи відкрита потрібна сторінка по патерну
//https://aqa-complexapp.onrender.com/post/64d21e84903640003414c338
// regex for 64d21e84903640003414c338
// [a-zA-Z0-9]{24}

    protected void checkUrlWithPattern(){
        Assert.assertTrue("URL is not expected \n" +
                        "Expected url: " + baseUrl + getRelativeURL() +
                        "\n Actual url: " + webDriver.getCurrentUrl(),
                webDriver.getCurrentUrl().matches(baseUrl + getRelativeURL()));
    }

}
