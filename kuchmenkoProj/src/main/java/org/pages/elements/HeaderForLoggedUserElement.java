package org.pages.elements;

import io.qameta.allure.Step;
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

    public HeaderForLoggedUserElement(WebDriver webDriver) {
        super(webDriver);
    }

    @Step
    public MyProfilePage clickOnButtonMyProfile() {
        clickOnElement(buttonMyProfile);
        return new MyProfilePage(webDriver);
    }

    @Step
    public HeaderForLoggedUserElement checkButtonSignOutVisible() {
        checkIsElementDisplayed(buttonSignOut);
        return new HeaderForLoggedUserElement(webDriver);
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

    public LoginPage verifyButtonSignOutIsNotVisible() {
        checkIsElementDisplayed(buttonSignOut);
        return new LoginPage(webDriver);
    }

    public HeaderForLoggedUserElement verifyButtonCreatePostIsVisible() {
        checkIsElementDisplayed(buttonCreateNewPost);
        return this;
    }
}
