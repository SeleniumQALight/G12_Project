package org.example.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends ParentPage {
    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public void checkIsButtonSignOutVisible() {
        Assert.assertTrue("Button Sign Out is not visible", isButtonSignOutVisible());
    }

    public boolean isButtonSignOutVisible() {
        try {
            boolean state = webDriver.findElement(By.xpath("//button[text()='Sign Out']")).isDisplayed();
            logger.info("Element visible: " + state);
            return state;
        } catch (Exception e) {
            logger.error("Element is not found: " + e); // Log the exception message .getMessage()
            return false;
        }
    }

}
