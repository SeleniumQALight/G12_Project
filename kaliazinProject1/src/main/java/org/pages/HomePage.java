package org.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.logging.Logger;

public class HomePage extends ParentPage{
    Logger logger = Logger.getLogger(HomePage.class.getName());
    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public void checkButtonSignOutVisible() {
        Assert.assertTrue("Button Sign Out is not visible", isButtonSignOutVisible());
    }

    private boolean isButtonSignOutVisible() {
        try {

            boolean state = webDriver.findElement(By.xpath("//button[text()='Sign Out']")).isDisplayed();
            logger.info(state + "Button sign Out is displayed");
            return state;
        } catch (Exception e) {
            logger.info("Element is not found");
            return false;
        }
    }
}