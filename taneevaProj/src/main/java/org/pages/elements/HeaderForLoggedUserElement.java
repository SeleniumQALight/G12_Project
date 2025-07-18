package org.pages.elements;

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

    public MyProfilePage clickOnButtonMyProfile() {
        clickOnElement(buttonMyProfile);
        return new MyProfilePage(webDriver);
    }

    public void checkButtonSignOutVisible() {
        //  Assert.assertTrue("Button Sign Out is not visible", isButtonSignOutVisible());
        checkIsElementDisplayed(buttonSignOut);
    }

    public void checkButtonSignOutNotVisible() {
        checkIsElementNotDisplayed(buttonSignOut);
    }

    public CreateNewPostPage clickOnButtonCreateNewPost() {
        clickOnElement(buttonCreateNewPost);
        return new CreateNewPostPage(webDriver);
    }

    public void checkButtonCreatePostVisible() {
        checkIsElementDisplayed(buttonCreateNewPost);
    }

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

}
