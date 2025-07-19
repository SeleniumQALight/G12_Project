package org.pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

abstract public class ParentPage extends CommonActionsWithElements{
    protected String baseURL = "https://aqa-complexapp.onrender.com";
    public ParentPage(WebDriver webDriver) {
        super(webDriver);
    }

    abstract protected  String getRelativeURL();

    protected void checkUrl(){
        Assert.assertEquals("URL is not expected"
            , baseURL + getRelativeURL()
            , webDriver.getCurrentUrl()
        );
    }

    // метод по перевірці чи відкрита потрібна сторінка по патерну
    //https://aqa-complexapp.onrender.com/post/64d21e84903640003414c338
    // regex for 64d21e84903640003414c338
    // [a-zA-Z0-9]{24}
    protected void checkUrlWithPattern(){
        Assert.assertTrue("URL is not expected \n" +
            "Expected url: " + baseURL + getRelativeURL() +
            "\n Actual url: " + webDriver.getCurrentUrl(),
            webDriver.getCurrentUrl().matches(baseURL + getRelativeURL()));
    }




}
