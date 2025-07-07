package org.pages.elements;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.*;

public class HeaderForLoggedUserElement extends CommonActionsWithElements {
    Logger logger = Logger.getLogger(getClass());
    @FindBy(xpath = "//img[@alt='My profile']")
    private WebElement buttonMyProfile;

    @FindBy(xpath = "//button[text()='Sign Out']")
    private WebElement buttonSignOut;

    @FindBy(xpath = "//a[@class='btn btn-sm btn-success mr-2']")
    private WebElement buttonCreatePost;

    public HeaderForLoggedUserElement(WebDriver webDriver) {
        super(webDriver);
    }

    public MyProfilePage clickOnButtonMyProfile() {
        clickOnElement(buttonMyProfile);
        return new MyProfilePage(webDriver);
    }

    public HomePage checkButtonSignOutVisible() {
        checkIsElementDisplayed(buttonSignOut);
        logger.info("Sign Out button is visible");
        return new HomePage(webDriver);
    }

    public LoginPage checkButtonSignOutNotVisible(){
        try {
            boolean isDisplayed = webDriver.findElement(By.xpath("//button[text()='Sign Out']")).isDisplayed();
            Assert.assertFalse("Sign Out button should NOT be visible", isDisplayed);
        } catch (Exception e) {
            logger.info("Sign Out button is not visible as expected");
        }
        return new LoginPage(webDriver);
    }

    public CreateNewPostPage clickOnButtonCreatePost() {
        clickOnElement(buttonCreatePost);
        return new CreateNewPostPage(webDriver);
    }

    public HomePage checkButtonCreatePostVisible() {
        checkIsElementDisplayed(buttonCreatePost);
        logger.info("Create Post button is visible");
        return new HomePage(webDriver);
    }
}
