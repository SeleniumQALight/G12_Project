package org.example.pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends ParentPage {
    @FindBy(xpath = "//button[text()='Sign Out']")
    private WebElement buttonSignOut;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public void checkIsButtonSignOutVisible() {
            checkIsElementDisplayed(buttonSignOut);
//        Assert.assertTrue("Button Sign Out is not visible", isButtonSignOutVisible());
    }

//    public boolean isButtonSignOutVisible() {
//        try {
//            boolean state = buttonSignOut.isDisplayed();
//            logger.info("Element visible: " + state);
//            return state;
//        } catch (Exception e) {
//            logger.error("Element is not found: " + e); // Log the exception message .getMessage()
//            return false;
//        }
//    }

}
