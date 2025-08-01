package org.pages.elements;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.CommonActionsWithElements;
import org.pages.CreateNewPostPage;
import org.pages.LoginPage;
import org.pages.MyProfilePage;

public class HeaderForLoggedUserElement extends CommonActionsWithElements {
    @FindBy(xpath = "//img[@alt='My profile']")
    private WebElement buttonMyProfile;

    @FindBy(xpath = "//button[text()='Sign Out']")
    private WebElement buttonSignOut;

    @FindBy(xpath = "//a[@class='btn btn-sm btn-success mr-2']")
    private WebElement buttonCreateNewPost;


    // HW 4 LogOutTest
    @FindBy(xpath = "//*[name()='svg' and contains(@class,'fa-search')]")
    private WebElement buttonSearch;

    @FindBy(xpath = "//span[contains(@class, 'header-chat-icon')]")
    private WebElement chatButton;

    @FindBy(xpath = "//img[@alt='My profile']")
    private WebElement avatar;

    public HeaderForLoggedUserElement(WebDriver webDriver) {
        super(webDriver);
    }

    @Step
    public MyProfilePage clickOnButtonMyProfile() {
        clickOnElement(buttonMyProfile);
        return new MyProfilePage(webDriver);
    }

    @Step
    public void checkButtonSignOutVisible() {
        //  Assert.assertTrue("Button Sign Out is not visible", isButtonSignOutVisible());
        checkIsElementDisplayed(buttonSignOut);
    }

    @Step
    public void  checkButtonSignOutNotVisible() {
        try {
            boolean isDisplayed = buttonSignOut.isDisplayed();
            Assert.assertFalse("Sign Out button should NOT be visible", isDisplayed);
        } catch (Exception e) {
            logger.info("Sign Out button is not visible, as expected");
        }
    }

    @Step
    public CreateNewPostPage clickOnButtonCreateNewPost() {
        clickOnElement(buttonCreateNewPost);
        return new CreateNewPostPage(webDriver);
    }

    @Step
    public boolean isButtonSignOutVisible() {
        return isElementDisplayed(buttonSignOut);
    }

    //HW 4 LogOutTest
    public HeaderForLoggedUserElement checkButtonSearchVisible() {
        checkIsElementDisplayed(buttonSearch);
        return this;
    }

    public HeaderForLoggedUserElement checkButtonChatVisible() {
        checkIsElementDisplayed(chatButton);
        return this;
    }

    public HeaderForLoggedUserElement checkAvatarVisible() {
        checkIsElementDisplayed(avatar);
        return this;
    }

    public HeaderForLoggedUserElement checkButtonSearchNotVisible() {
        checkIsElementNotDisplayed(buttonSearch);
        return this;
    }

    public HeaderForLoggedUserElement checkButtonChatNotVisible() {
        checkIsElementNotDisplayed(chatButton);
        return this;
    }

    public HeaderForLoggedUserElement checkAvatarNotVisible() {
        checkIsElementNotDisplayed(avatar);
        return this;
    }

    public LoginPage clickOnButtonSignOut() {
        clickOnElement(buttonSignOut);
        return new LoginPage(webDriver);
    }

    public HeaderForLoggedUserElement checkButtonCreatePostNotVisible() {
        checkIsElementNotDisplayed(buttonCreateNewPost);
        return this;
    }


    public void checkButtonCreatePostVisible() {
        checkIsElementDisplayed(buttonCreateNewPost);
        logger.info("Create Post button is visible");
    }
}
