package org.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class HomePage extends ParentPage {
    private Logger logger = Logger.getLogger(getClass());

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public void checkSignOutButtonIsDisplayed() {
        Assert.assertTrue("Sign Out button is not displayed after Log In", isButtonSignOutVisible());
    }

    private boolean isButtonSignOutVisible() {
        try {
            boolean state = webDriver.findElement(By.xpath("//button[text()='Sign Out']")).isDisplayed();
            logger.info(state + " - is element visible");
            return state;
        } catch (Exception e) {
            logger.info("Element not found");
            return false;
        }
    }
}
