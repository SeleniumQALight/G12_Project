package org.pages.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.*;

public class HeaderForLoggedUserElement extends CommonActionsWithElements {
    @FindBy(xpath = "//img[@alt='My profile']")
    private WebElement buttonMyProfile;

    @FindBy(xpath = "//a[@class='btn btn-sm btn-success mr-2']")
    private WebElement buttonCreateNewPost;

    @FindBy(xpath = "//button[text()='Sign Out']")
    private WebElement buttonSignOut;

    @FindBy(xpath = "//a[@data-original-title='Search']")
    private WebElement buttonSearch;

    @FindBy(xpath = "//span[@data-original-title='Chat']")
    private WebElement buttonChatCall;


    public HeaderForLoggedUserElement(WebDriver webDriver) {
        super(webDriver);
    }

    public void checkButtonMyProfileIsVisible() {
        checkIsElementDisplayed(buttonMyProfile);
    }

    public void checkButtonMyProfileIsNotVisible() {
        checkIsElementNotDisplayed(buttonMyProfile);
    }

    public void checkButtonSignOutVisible() {
        checkIsElementDisplayed(buttonSignOut);
    }

    public void checkSingOutButtonIsNotVisible() {
        checkIsElementNotDisplayed(buttonSignOut);
    }

    public void checkButtonCreatePostVisible() {
        checkIsElementDisplayed(buttonCreateNewPost);
    }

    public void checkButtonCreatePostIsNotVisible() {
        checkIsElementNotDisplayed(buttonCreateNewPost);
    }

    public void checkSearchButtonIsVisible() {
        checkIsElementDisplayed(buttonSearch, "Search button");
    }

    public void checkSearchButtonIsNotVisible() {
        checkIsElementNotDisplayed(buttonSearch, "Search button");
    }

    public void checkChatButtonIsVisible() {
        checkIsElementDisplayed(buttonChatCall, "Chat button");
    }

    public void checkChatButtonIsNotVisible() {
        checkIsElementNotDisplayed(buttonChatCall, "Chat button");
    }

    public MyProfilePage clickOnButtonMyProfile() {
        clickOnElement(buttonMyProfile);
        return new MyProfilePage(webDriver);
    }

    public CreateNewPostPage clickOnButtonCreateNewPost() {
        clickOnElement(buttonCreateNewPost);
        return new CreateNewPostPage(webDriver);
    }

    public boolean isButtonSignOutVisible() {
        return isElementDisplayed(buttonSignOut);
    }

    public HeaderForLoggedUserElement checkIsElemenentsForLoggedUserIsDisplayedInHeader() {
        checkSearchButtonIsVisible();
        checkChatButtonIsVisible();
        checkButtonMyProfileIsVisible();
        checkButtonCreatePostVisible();
        checkButtonSignOutVisible();
        return this;
    }

    public HeaderForLoggedUserElement checkElementsInHeaderForLoggedUserIsNotVisible() {
        checkSearchButtonIsNotVisible();
        checkChatButtonIsNotVisible();
        checkButtonMyProfileIsNotVisible();
        checkButtonCreatePostIsNotVisible();
        checkSingOutButtonIsNotVisible();
        return this;
    }

    public LoginPage clickOnButtonSignOut() {
        clickOnElement(buttonSignOut);
        return new LoginPage(webDriver);
    }
}
