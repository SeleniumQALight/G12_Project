package org.pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.elements.HeaderForLoggedUserElement;

public class HomePage extends ParentPage {
    Logger logger = Logger.getLogger(getClass());

//    @FindBy(xpath = "//button[text()='Sign Out']")
//    private WebElement buttonSignOut;
//
//    @FindBy(xpath = "//a[@class='btn btn-sm btn-success mr-2']")
//    private WebElement buttonCreatePost;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public HomePage checkButtonSignOutVisible() {
        //       Assert.assertTrue("Button Sign Out is not visible", isButtunSignOutVisible());
        checkIsElementDisplayed(buttonSignOut);
        logger.info("Sign Out button is visible");
        return this;
    }

    //    private boolean isButtunSignOutVisible() {
//        try {
//            boolean state = webDriver.findElement(By.xpath("//button[text()='Sign Out']")).isDisplayed();
//            logger.info(" Element visible - " + state);
//            return state;
//        } catch (Exception e) {
//            logger.info("Element is not found");
//            return false;
//        }
//    }
    public HomePage checkButtonSignOutNotVisible() {
        try {
            boolean isDisplayed = buttonSignOut.isDisplayed();
            Assert.assertFalse("Sign Out button should NOT be visible", isDisplayed);
        } catch (Exception e) {
            logger.info("Sign Out button is not visible as expected");
        }
        return this;
    }

    public HomePage checkButtonCreatePostVisible() {
        checkIsElementDisplayed(buttonCreatePost);
        logger.info("Create Post button is visible");
        return this;
    }

    public HomePage checkIsRedirectedToHomePage() {
        //TODO check URL
       getHeaderForLoggedUserElement().checkButtonSignOutVisible();
        return this;
    }

//    public void checkButtonSignOutVisible() {
//        //       Assert.assertTrue("Button Sign Out is not visible", isButtunSignOutVisible());
//        checkIsElementDisplayed(buttonSignOut);
//    }
//
//    public CreateNewPostPage clickOnButtonCreatePost() {
//        clickOnElement(buttonCreatePost);
//        return new CreateNewPostPage(webDriver);
//    }
}
