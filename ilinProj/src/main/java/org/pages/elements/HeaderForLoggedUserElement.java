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
    private WebElement buttonCreatePost;

    @FindBy(xpath = "//a[@href='#']")
    private WebElement buttonSearch;

    @FindBy(xpath = "//span[@class='text-white mr-2 header-chat-icon']")
    private WebElement buttonChat;

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
        //Assert.assertTrue("Button Sign Out is not visible", isButtonSignOutVisible());
        checkIsElementDisplayed(buttonSignOut);
        return new HeaderForLoggedUserElement(webDriver);
    }

    @Step
    public CreateNewPostPage clickOnButtonCreatePost() {
        clickOnElement(buttonCreatePost);
        return new CreateNewPostPage(webDriver);
    }


    @Step
    public boolean isButtonSignOutVisible() {
        return isElementDisplayed(buttonSignOut);
    }

    @Step
    public LoginPage verifyButtonSignOutIsNotVisible() {
        checkIsElementIsNotDisplayed(buttonSignOut);
        return new LoginPage(webDriver);
    }

    @Step
    public HeaderForLoggedUserElement verifyButtonCreatePostIsVisible() {
        checkIsElementDisplayed(buttonCreatePost);
        return this;
    }

    @Step
    public HeaderForLoggedUserElement getHeaderForLoggedUserElement() {
        return new HeaderForLoggedUserElement(webDriver);
    }

    @Step
    public HeaderForLoggedUserElement checkAllHeaderElementsVisible(){
        checkIsElementDisplayed(buttonSearch);
        checkIsElementDisplayed(buttonChat);
        checkIsElementDisplayed(buttonMyProfile);
        checkIsElementDisplayed(buttonCreatePost);
        checkIsElementDisplayed(buttonSignOut);
        return this;
    }

    @Step
    public HeaderForLoggedUserElement clickOnButtonSignOut(){
        clickOnElement(buttonSignOut);
        return this;
    }

    @Step
    public LoginPage checkHomePageHeaderElementsIsNotVisible() {
        checkIsElementIsNotDisplayed(buttonSearch);
        checkIsElementIsNotDisplayed(buttonChat);
        checkIsElementIsNotDisplayed(buttonMyProfile);
        checkIsElementIsNotDisplayed(buttonCreatePost);
        checkIsElementIsNotDisplayed(buttonSignOut);
        return new LoginPage(webDriver);
    }
}

